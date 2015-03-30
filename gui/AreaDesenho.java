package lpoo.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.*;
import lpoo.logic.Maze;
import lpoo.logic.RandomMaze;

public class AreaDesenho extends JPanel
{
	private BufferedImage spriteHero;
	private BufferedImage spriteHeroShield;
	private BufferedImage spriteHeroSword;
	private BufferedImage spriteHeroUnarmed;
	private BufferedImage spriteDragon;
	private BufferedImage spriteDoorOpened;
	private BufferedImage spriteDoorClosed;
	private BufferedImage spriteDart;
	private BufferedImage spriteFireball;
	private BufferedImage spriteFloor;
	private BufferedImage spriteShield;
	private BufferedImage spriteSword;
	private BufferedImage spriteTombstone;
	private BufferedImage spriteWall;

	private BufferedImage resizedHero;
	private BufferedImage resizedHeroShield;
	private BufferedImage resizedHeroSword;
	private BufferedImage resizedHeroUnarmed;
	private BufferedImage resizedDragon;
	private BufferedImage resizedDart;
	private BufferedImage resizedDoorOpened;
	private BufferedImage resizedDoorClosed;
	private BufferedImage resizedFireball;
	private BufferedImage resizedFloor;
	private BufferedImage resizedShield;
	private BufferedImage resizedSword;
	private BufferedImage resizedTombstone;
	private BufferedImage resizedWall;

	protected Maze maze;
	protected int mazeWidth;
	protected int mazeHeight;
	protected int mazeCells;
	protected int spriteWidth;
	protected int spriteHeight;

	protected boolean backgroundGenerated = false;
	protected BufferedImage backgroundImage;
	protected Graphics backgroundLayer;
	protected BufferedImage foregroundImage;
	protected Graphics foregroundLayer;

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

	private BufferedImage resizeImage(BufferedImage img, int newWidth, int newHeight)
	{
		BufferedImage resized = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = resized.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, 0, 0, newWidth, newHeight, 0, 0, img.getWidth(), img.getHeight(), null);
		g.dispose();

		return resized;
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

		if (mazeWidth != 0 && mazeHeight != 0)
		{
			backgroundImage = new BufferedImage(windowSize.width, windowSize.height, BufferedImage.TYPE_INT_ARGB);
			backgroundLayer = backgroundImage.getGraphics();
			foregroundImage = new BufferedImage(windowSize.width, windowSize.height, BufferedImage.TYPE_INT_ARGB_PRE);
			foregroundLayer = foregroundImage.getGraphics();
			backgroundGenerated = true;
		}

		setPreferredSize(windowSize);
		revalidate();
	}

	protected void generateMaze()
	{
		initializeMaze(new RandomMaze(mazeWidth, mazeHeight));
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
		try
		{

			spriteDart = ImageIO.read(this.getClass().getResource("/lpoo/res/dart-64x64.png"));
			spriteDragon = ImageIO.read(this.getClass().getResource("/lpoo/res/dragon-64x64.png"));
			spriteDoorOpened = ImageIO.read(this.getClass().getResource("/lpoo/res/door2-64x64.png"));
			spriteDoorClosed = ImageIO.read(this.getClass().getResource("/lpoo/res/door1-64x64.png"));
			spriteFireball = ImageIO.read(this.getClass().getResource("/lpoo/res/fireball-64x64.png"));
			spriteFloor = ImageIO.read(this.getClass().getResource("/lpoo/res/floor-64x64.png"));
			spriteHero = ImageIO.read(this.getClass().getResource("/lpoo/res/hero-64x64.png"));
			spriteHeroShield = ImageIO.read(this.getClass().getResource("/lpoo/res/hero-shield-64x64.png"));
			spriteHeroSword = ImageIO.read(getClass().getResource("/lpoo/res/hero-sword-64x64.png"));
			spriteHeroUnarmed = ImageIO.read(getClass().getResource("/lpoo/res/hero-unarmed-64x64.png"));
			spriteShield = ImageIO.read(getClass().getResource("/lpoo/res/shield-64x64.png"));
			spriteSword = ImageIO.read(getClass().getResource("/lpoo/res/sword-64x64.png"));
			spriteTombstone = ImageIO.read(getClass().getResource("/lpoo/res/tombstone-64x64.png"));
			spriteWall = ImageIO.read(getClass().getResource("/lpoo/res/wall-64x64.png"));
		}
		catch (IOException ex)
		{

		}
	}

	protected void scaleSprites(int w, int h)
	{
		spriteWidth = w;
		spriteHeight = h;
		
		resizedDart = resizeImage(spriteDart, spriteWidth, spriteHeight);
		resizedDragon = resizeImage(spriteDragon, spriteWidth, spriteHeight);
		resizedDoorOpened = resizeImage(spriteDoorOpened, spriteWidth, spriteHeight);
		resizedDoorClosed = resizeImage(spriteDoorClosed, spriteWidth, spriteHeight);
		resizedFireball = resizeImage(spriteFireball, spriteWidth, spriteHeight);
		resizedFloor = resizeImage(spriteFloor, spriteWidth, spriteHeight);
		resizedHero = resizeImage(spriteHero, spriteWidth, spriteHeight);
		resizedHeroShield = resizeImage(spriteHeroShield, spriteWidth, spriteHeight);
		resizedHeroSword = resizeImage(spriteHeroSword, spriteWidth, spriteHeight);
		resizedHeroUnarmed = resizeImage(spriteHeroUnarmed, spriteWidth, spriteHeight);
		resizedShield = resizeImage(spriteShield, spriteWidth, spriteHeight);
		resizedSword = resizeImage(spriteSword, spriteWidth, spriteHeight);
		resizedTombstone = resizeImage(spriteTombstone, spriteWidth, spriteHeight);
		resizedWall = resizeImage(spriteWall, spriteWidth, spriteHeight);

		updateWindow();
	}

	protected void readMaze(ObjectInputStream s) throws IOException, ClassNotFoundException
	{

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

	private void generateTiles()
	{
		if (mazeHeight == 0 || mazeWidth == 0)
		{
			return;
		}

		int y = 0;

		for (int i = 0; i < mazeHeight; i++)
		{
			int x = 0;

			for (int j = 0; j < mazeWidth; j++)
			{
				if (maze.symbolAt(j, i) == 'X')
				{
					backgroundLayer.drawImage(resizedWall, x, y, null);
				}
				else
				{
					backgroundLayer.drawImage(resizedFloor, x, y, null);
				}

				x += spriteWidth;
			}

			y += spriteHeight;
		}

		backgroundGenerated = false;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, getSize().width, getSize().height);

		int y = 0;

		for (int i = 0; i < mazeHeight; i++)
		{
			int x = 0;

			for (int j = 0; j < mazeWidth; j++)
			{
				foregroundLayer.drawImage(resizedFloor, x, y, null);

				switch (maze.symbolAt(j, i))
				{
				case 'h':
					foregroundLayer.drawImage(resizedHeroUnarmed, x, y, null);
					break;
				case 'H':
					foregroundLayer.drawImage(resizedHeroShield, x, y, null);
					break;
				case 'a':
					foregroundLayer.drawImage(resizedHeroSword, x, y, null);
					break;
				case 'A':
					foregroundLayer.drawImage(resizedHero, x, y, null);
					break;
				case 'X':
					foregroundLayer.drawImage(resizedWall, x, y, null);
					break;
				case 'D': case 'd':
					foregroundLayer.drawImage(resizedDragon, x, y, null);
					break;
				case 'S':
					foregroundLayer.drawImage(resizedDoorClosed, x, y, null);
					break;
				case 's':
					foregroundLayer.drawImage(resizedDoorOpened, x, y, null);
					break;
				case 'x':
					foregroundLayer.drawImage(resizedTombstone, x, y, null);
					break;
				case 'O':
					foregroundLayer.drawImage(resizedFireball, x, y, null);
					break;
				case 'E':
					foregroundLayer.drawImage(resizedSword, x, y, null);
					break;
				case '*':
					foregroundLayer.drawImage(resizedDart, x, y, null);
					break;
				case 'V':
					foregroundLayer.drawImage(resizedShield, x, y, null);
					break;
				}

				x += spriteWidth;
			}

			y += spriteHeight;
		}

		Toolkit.getDefaultToolkit().sync();
		g.drawImage(foregroundImage, 0, 0, null);

	}
}
