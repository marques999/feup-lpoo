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
	private static final long serialVersionUID = 1652644943686379111L;

	private BufferedImage spriteHero;
	private BufferedImage spriteHeroShield;
	private BufferedImage spriteHeroSword;
	private BufferedImage spriteHeroUnarmed;
	private BufferedImage spriteDragon;
	private BufferedImage spriteDragonSleeping;
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
	private BufferedImage resizedDragonSleeping;
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

	private BufferedImage graphics2d;
	private Graphics graphicsBuffer;
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

	protected Dimension getWindowSize()
	{
		return windowSize;
	}

	private BufferedImage resizeImage(BufferedImage img, int newWidth, int newHeight)
	{
		final BufferedImage resized = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_4BYTE_ABGR);
		final Graphics2D g = resized.createGraphics();

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, 0, 0, newWidth, newHeight, 0, 0, img.getWidth(), img.getHeight(), null);
		g.dispose();

		return resized;
	}

	protected void initializeMaze(int newWidth, int newHeight)
	{
		mazeWidth = newWidth;
		mazeHeight = newHeight;
		mazeCells = mazeWidth * mazeHeight;
		maze = new Maze(mazeWidth, mazeHeight);

		updateWindow();
	}

	protected final void initializeMaze(Maze m)
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
			graphics2d = new BufferedImage(windowSize.width, windowSize.height, BufferedImage.TYPE_INT_ARGB_PRE);
			graphicsBuffer = graphics2d.getGraphics();
		}

		setPreferredSize(windowSize);
		revalidate();
		repaint();
	}

	protected void generateMaze()
	{
		initializeMaze(new RandomMaze(mazeWidth, mazeHeight));
	}

	protected void erase()
	{
		final char[][] newMatrix = new char[mazeHeight][mazeWidth];

		for (final char[] row : newMatrix)
		{
			Arrays.fill(row, ' ');
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
			spriteDragonSleeping = ImageIO.read(this.getClass().getResource("/lpoo/res/dragon-sleep-64x64.png"));
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
		catch (final IOException ex)
		{
			GUIGlobals.abort(ex, null);
		}
	}

	protected final void scaleSprites(int w, int h)
	{
		spriteWidth = w;
		spriteHeight = h;

		resizedDart = resizeImage(spriteDart, spriteWidth, spriteHeight);
		resizedDragon = resizeImage(spriteDragon, spriteWidth, spriteHeight);
		resizedDragonSleeping = resizeImage(spriteDragonSleeping, spriteWidth, spriteHeight);
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

	protected void writeMaze(ObjectOutputStream s) throws IOException
	{
		s.writeObject(maze);
	}

	@Override
	public final void paintComponent(Graphics g)
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
				graphicsBuffer.drawImage(resizedFloor, x, y, null);

				switch (maze.symbolAt(j, i))
				{
				case 'h':
					graphicsBuffer.drawImage(resizedHeroUnarmed, x, y, null);
					break;
				case 'H':
					graphicsBuffer.drawImage(resizedHeroShield, x, y, null);
					break;
				case 'a':
					graphicsBuffer.drawImage(resizedHeroSword, x, y, null);
					break;
				case 'A':
					graphicsBuffer.drawImage(resizedHero, x, y, null);
					break;
				case 'X':
					graphicsBuffer.drawImage(resizedWall, x, y, null);
					break;
				case 'D': case 'F':
					graphicsBuffer.drawImage(resizedDragon, x, y, null);
					break;
				case 'd':
					graphicsBuffer.drawImage(resizedDragonSleeping, x, y, null);
					break;
				case 'S':
					graphicsBuffer.drawImage(resizedDoorClosed, x, y, null);
					break;
				case 's':
					graphicsBuffer.drawImage(resizedDoorOpened, x, y, null);
					break;
				case 'x':
					graphicsBuffer.drawImage(resizedTombstone, x, y, null);
					break;
				case 'O':
					graphicsBuffer.drawImage(resizedFireball, x, y, null);
					break;
				case 'E':
					graphicsBuffer.drawImage(resizedSword, x, y, null);
					break;
				case '*':
					graphicsBuffer.drawImage(resizedDart, x, y, null);
					break;
				case 'V':
					graphicsBuffer.drawImage(resizedShield, x, y, null);
					break;
				}

				x += spriteWidth;
			}

			y += spriteHeight;
		}

		Toolkit.getDefaultToolkit().sync();
		g.drawImage(graphics2d, 0, 0, null);
	}
}