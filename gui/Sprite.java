package lpoo.gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import lpoo.logic.Point;

public class Sprite 
{
    private Image spriteImage;
    private Graphics2D spriteImageG2D;
    
    private Point pos;
    private int height;
    private int width;

    /** The matrix transform responsible for geometric transformations to the sprite */
    private AffineTransform spriteTransform;
    
    /**The double buffer onto which this Sprite object should draw itself */
    BufferedImage spriteDoubleBuffer;
    /**The double buffer's graphics context */
    Graphics2D spriteDoubleBufferG2D;


    /**Construct a sprite template, and initialize its instance variables later. */

    /**Construct a complete sprite */
    public Sprite(int width, int height, BufferedImage doubleBuffer)
    {
        this.pos = new Point(0, 0);
        this.width = width;
        this.height = height;
        this.spriteDoubleBufferG2D = (Graphics2D) doubleBuffer.getGraphics();
        this.spriteTransform = new AffineTransform();
        //this.spriteImageG2D = (Graphics2D)this.spriteImage.getGraphics();
    }

    public Graphics2D getSpriteDoubleBufferG2D() 
    {
        return spriteDoubleBufferG2D;
    }

    /** Translate the underlying matrix for this sprite, based on the sprite's
     * set x and y coordinates.
     * 
     */
    public void transform()
    {
    	spriteTransform.setToIdentity();
        spriteTransform.translate(pos.x, pos.y);
    }

    /** Draw this Sprite, based on its image object and affinetrasnform for
     *  location/transformation operations that have been done to it on the
     *  backbuffer.
     *
     */
    public void draw()
    {
        transform();
        spriteDoubleBufferG2D.drawImage(spriteImage, spriteTransform, null);
    }

    /** Loads the specified image from the Sprites folder of the project. The method
     *  uses Toolkit's createImage() method, and hence can only load .GIF .JPG or .PNG
     *  image types.
     *
     * @param name the full name of the image, including the extension.
     */
    public void loadImage(String name) 
    {
    	setImage(Toolkit.getDefaultToolkit().getImage("/lpoo/res/" + name).getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    public Image getImage() 
    {
        return this.spriteImage;
    }

    public void setImage(Image spriteImage) 
    {
        this.spriteImage = spriteImage;
    }

    public Graphics2D getSpriteImageG2D() 
    {
        return spriteImageG2D;
    }

    public void setSpriteImageG2D(Graphics2D spriteImageG2D) 
    {
        this.spriteImageG2D = spriteImageG2D;
    }

    public int getHeight() 
    {
        return height;
    }

    public void setHeight(int height) 
    {
        this.height = height;
    }

    public int getWidth() 
    {
        return width;
    }

    public void setWidth(int width) 
    {
        this.width = width;
    }

    public double getX() 
    {
        return pos.x;
    }

    public void setX(int x) 
    {
       this.pos.x = x;
    }

    public double getY() 
    {
        return pos.y;
    }

    public void setY(int y) 
    {
        this.pos.y = y;
    }
}
