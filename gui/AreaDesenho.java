package lpoo.gui;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import javax.swing.*;
import lpoo.logic.Maze;
import lpoo.logic.RandomMaze;

public class AreaDesenho extends JPanel
{
	private ImageIcon spriteHero;
	private ImageIcon spriteDragon;
	private ImageIcon spriteDoorOpened;
	private ImageIcon spriteDoorClosed;
	private ImageIcon spriteDart;
	private ImageIcon spriteShield;
	private ImageIcon spriteSword;

	private Image resizedHero;
	private Image resizedDragon;
	private Image resizedDart;
	private Image resizedDoorOpened;
	private Image resizedDoorClosed;
	private Image resizedShield;
	private Image resizedSword;

	protected Maze maze;
	protected int mazeWidth;
	protected int mazeHeight;
	protected int mazeCells;
	protected int spriteWidth;
	protected int spriteHeight;

	private Dimension windowSize;

	public AreaDesenho()
	{
		this(11, 11);
	}

	protected AreaDesenho(int w, int h)
	{
		initializeSprites();
                scaleSprites(32, 32);
		initializeMaze(w, h);
	}

	protected void setMaze(Maze m)
	{
		maze = m;
	}
        
        protected Dimension getWindowSize()
        {
            return windowSize;
        }

	protected void initializeMaze(int w, int h)
	{
		mazeWidth = w;
		mazeHeight = h;
		mazeCells = mazeWidth * mazeHeight;
		maze = new Maze(mazeWidth, mazeHeight);

                updateWindow();
	}
        
        protected void initializeMaze(Maze m)
        {
            maze = m;
            mazeWidth = m.getWidth();
            mazeHeight = m.getHeight();
            mazeCells = mazeWidth * mazeHeight;
            
            updateWindow();
        }
        
        private void updateWindow()
        {
            windowSize = new Dimension(mazeWidth * spriteWidth, mazeHeight * spriteHeight);
             
            setPreferredSize(windowSize);
            revalidate();
        }
        
	protected void generateMaze()
	{
            initializeMaze(new RandomMaze(mazeWidth, mazeHeight));
            repaint();
	}

	protected void erase()
	{
            char[][] newMatrix = new char[mazeHeight][mazeWidth];

		for (char[] r : newMatrix)
		{
			Arrays.fill(r, ' ');
		}

		maze.setMatrix(newMatrix);
		repaint();
	}

	private void initializeSprites()
	{
		spriteDart = new ImageIcon(getClass().getResource("/lpoo/res/dart-64x64.png"));
		spriteDragon = new ImageIcon(getClass().getResource("/lpoo/res/dragon-64x64.png"));
		spriteDoorOpened = new ImageIcon(getClass().getResource("/lpoo/res/door2-64x64.png"));
		spriteDoorClosed = new ImageIcon(getClass().getResource("/lpoo/res/door1-64x64.png"));
		spriteHero = new ImageIcon(getClass().getResource("/lpoo/res/unarmedhero.png"));
		spriteShield = new ImageIcon(getClass().getResource("/lpoo/res/shield-64x64.png"));
		spriteSword = new ImageIcon(getClass().getResource("/lpoo/res/sword-64x64.png"));
	}
        
        protected void scaleSprites(int w, int h)
        {
            	spriteWidth = w;
		spriteHeight = h;
                
                resizedDart = spriteDart.getImage().getScaledInstance(spriteWidth, spriteHeight, Image.SCALE_SMOOTH);
		resizedDragon = spriteDragon.getImage().getScaledInstance(spriteWidth, spriteHeight, Image.SCALE_SMOOTH);
		resizedDoorOpened = spriteDoorOpened.getImage().getScaledInstance(spriteWidth, spriteHeight, Image.SCALE_SMOOTH);
		resizedDoorClosed = spriteDoorClosed.getImage().getScaledInstance(spriteWidth, spriteHeight, Image.SCALE_SMOOTH);
		resizedHero = spriteHero.getImage().getScaledInstance(spriteWidth, spriteHeight, java.awt.Image.SCALE_SMOOTH);
		resizedShield = spriteShield.getImage().getScaledInstance(spriteWidth, spriteHeight, Image.SCALE_SMOOTH);
		resizedSword = spriteSword.getImage().getScaledInstance(spriteWidth, spriteHeight, Image.SCALE_SMOOTH);
            
                updateWindow();
        }
        
        protected void readMaze(ObjectInputStream s) throws IOException, ClassNotFoundException
        {
                initializeMaze((Maze) s.readObject());
        }
        
        protected void writeMaze(ObjectOutputStream s) throws IOException
        {
            s.writeObject(maze);
        }
        
        protected void readState(ObjectInputStream s) throws IOException, ClassNotFoundException
        {
            readMaze(s);
        }

        protected void writeState(ObjectOutputStream s) throws IOException
        {
            writeMaze(s);
        }
        
	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, getSize().width, getSize().height);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, windowSize.width, windowSize.height);

		int y = 0;

		for (int i = 0; i < mazeHeight; i++)
		{
			int x = 0;

			for (int j = 0; j < mazeWidth; j++)
			{
				switch (maze.symbolAt(j, i))
				{
				case 'h':
				case 'a':
				case 'H':
				case 'A':
					g.drawImage(resizedHero, x, y, null);
					break;
				case 'D':
				case 'd':
					g.drawImage(resizedDragon, x, y, null);
					break;
				case 'X':
					g.setColor(Color.CYAN);
					g.fillRect(x, y, spriteWidth, spriteHeight);
					break;
				case 'S':
					g.drawImage(resizedDoorClosed, x, y, null);
					break;
				case 's':
					g.drawImage(resizedDoorOpened, x, y, null);
					break;
				case 'E':
					g.drawImage(resizedSword, x, y, null);
					break;
				case '*':
					g.drawImage(resizedDart, x, y, null);
					break;
				case 'V':
					g.drawImage(resizedShield, x, y, null);
					break;
				}

				x += spriteWidth;
			}

			y += spriteHeight;
		}
	}
}