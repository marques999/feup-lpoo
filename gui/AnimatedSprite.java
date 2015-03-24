/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpoo.gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class AnimatedSprite extends Sprite 
{
    public int frameNumber;
    public int frameWidth;
    public int frameHeight;
    public int frameX, frameY;
    public int totalWidth;
    public int totalHeight;
    public int totalFrames;
    //holds the animation strip
    private Image animimage;
    /*a delay which has to be overcome for the frame of this
    animated strip to be painted, used to untie animation rate from fps set in main loop */
    public int frameDelay;
    public int frameTicker;


    //temporary image passed to parent draw method
    BufferedImage tempImage;
    Graphics2D tempSurface;

    public AnimatedSprite(int totalFrames, int frameWidth, int frameHeight, int totalWidth, int totalHeight, BufferedImage doubleBuffer) 
    {
        super(totalWidth, totalHeight, doubleBuffer);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameX = 0;
        this.frameY = 0;
        this.frameNumber = 0;
        this.totalFrames = totalFrames;
        this.totalWidth = totalWidth;
        this.totalHeight = totalHeight;
        /*If the delay in the Main class is 50ms, and frameDelay is 5, logically,
         a new frame will be shown every 50ms*5 = 250ms, every quarter of a second.
         See updateAnimation() method*/
        this.frameDelay = 5;
    }

    /** Loads the specified image from the Sprites folder of the project. The method
     *  uses Toolkit's createImage() method, and hence can only load .GIF .JPG or .PNG
     *  image types.
     *
     * @param name the full name of the image, including the extension.
     * @return
     */
    @Override
    public void loadImage(String name) 
    {
        animimage = Toolkit.getDefaultToolkit().getImage("src/Sprites/" + name);

        //frame image is passed to parent class for drawing
        tempImage = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_ARGB);
        tempSurface = tempImage.createGraphics();
        super.setImage(tempImage);

    }

    public void updateAnimation() {
        if (this.getFrameTicker() >= this.getFrameDelay()) {
            this.setFrameNumber(this.getFrameNumber() + 1);
            if (this.getFrameNumber() > this.getTotalFrames() - 1) {
                this.setFrameNumber(0); //Update the animation frame to 0 if the count is above the last frame
            } else if (this.getFrameNumber() < 0) {
                this.setFrameNumber(this.getTotalFrames() - 1); //to the last frame, as first frame number = 0, by convention
            }
            this.setFrameTicker(0);
        } else {
            this.setFrameTicker(this.getFrameTicker()+1);
        }

    }

    /** Draw this Sprite, based on its image object and affine transform for
     *  location/transformation operations that have been done to it on the
     *  backbuffer.
     *
     */
    @Override
    public void draw() 
    {
        if (tempImage == null) 
        {
            tempImage = new BufferedImage(this.getFrameWidth(), this.getFrameHeight(), BufferedImage.TYPE_INT_ARGB);
            tempSurface = tempImage.createGraphics();
        }
        //calculate the current frame's X and Y position
        this.setFrameX(this.getFrameNumber() * this.getFrameWidth());
        this.setFrameY(0);

        //copy the frame onto the temp image
        //simply copies from destination to the source image
        //so from animimage to tempSurface
        tempSurface.drawImage(animimage, 0, 0, this.getFrameWidth() - 1, this.getFrameHeight() - 1,
                this.getFrameX(), this.getFrameY(), this.getFrameX() + this.getFrameWidth(), this.getFrameY() + this.getFrameHeight(), null);

        //pass the image on to the parent class and draw it
        super.setImage(tempImage);
        super.transform();
        super.draw();

        //destroy these so next image doesn't write over the previous one
        tempSurface.dispose();
        tempImage = null;


    }

    public int getFrameHeight() 
    {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) 
    {
        this.frameHeight = frameHeight;
    }

    public int getFrameNumber() 
    {
        return frameNumber;
    }

    public void setFrameNumber(int frameNumber) 
    {
        this.frameNumber = frameNumber;
    }

    public int getFrameWidth() 
    {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) 
    {
        this.frameWidth = frameWidth;
    }

    public int getFrameX() 
    {
        return frameX;
    }

    public void setFrameX(int frameX) 
    {
        this.frameX = frameX;
    }

    public int getFrameY() 
    {
        return frameY;
    }

    public void setFrameY(int frameY) 
    {
        this.frameY = frameY;
    }

    public int getTotalFrames() 
    {
        return totalFrames;
    }

    public void setTotalFrames(int totalFrames) 
    {
        this.totalFrames = totalFrames;
    }

    public int getFrameDelay() 
    {
        return frameDelay;
    }

    public void setFrameDelay(int frameDelay) 
    {
        this.frameDelay = frameDelay;
    }

    public int getFrameTicker() 
    {
        return frameTicker;
    }

    public void setFrameTicker(int frameTicker) 
    {
        this.frameTicker = frameTicker;
    }
}