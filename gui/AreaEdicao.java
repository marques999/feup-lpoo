package lpoo.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Stack;
import javax.swing.*;
import lpoo.logic.Point;
import lpoo.logic.RandomMaze;

public class AreaEdicao extends JPanel implements MouseListener, MouseMotionListener 
{
	private ImageIcon s_hero;
	private ImageIcon s_dragon;
	private ImageIcon s_dart;
	private ImageIcon s_shield;
	private ImageIcon s_sword;

	private Image s_hero_resized;
	private Image s_dragon_resized;
	private Image s_dart_resized;
	private Image s_shield_resized;
	private Image s_sword_resized;

	private char[][] m_maze;
	private int m_width;
	private int m_height;
	private int s_width;
	private char m_symbol;
	private int m_cells;
	private int s_height;

	private Dimension w_size;
	private Stack<LastAction> undoStack;
	private Stack<LastAction> redoStack;

	private int maxDragons;
	protected int numDragons;
	protected int numDarts;
	protected int numShields;
	protected int numSwords;
	protected int numPlayers;

	public AreaEdicao() 
	{
		this(11, 11);
	}

	public AreaEdicao(int w, int h) 
	{
		m_width = w;
		m_height = h;
		m_symbol = ' ';
		undoStack = new Stack<>();
		redoStack = new Stack<>();

		addMouseListener(this);
		addMouseMotionListener(this);

		initializeMaze();
		initializeCounters();
		initializeSprites();

		this.maxDragons = m_cells / 60;

		setSize(w_size);
		revalidate();
		repaint();
	}

	private void initializeMaze() 
	{
		this.s_width = 32;
		this.s_height = 32;
		this.m_cells = m_width * m_height;
		this.m_maze = new char[this.m_height][this.m_width];
		this.w_size = new Dimension(m_width * s_width, m_height * s_height);
	}

	private void initializeCounters() 
	{
		numDragons = 0;
		numDarts = 0;
		numShields = 0;
		numSwords = 0;
		numPlayers = 0;
	}

	private void initializeSprites() 
	{
		this.s_dart = new ImageIcon(getClass().getResource("/lpoo/res/dart-64x64.png"));
		this.s_dragon = new ImageIcon(getClass().getResource("/lpoo/res/dragon-64x64.png"));
		this.s_hero = new ImageIcon(getClass().getResource("/lpoo/res/unarmedhero.png"));
		this.s_shield = new ImageIcon(getClass().getResource("/lpoo/res/shield-64x64.png"));
		this.s_sword = new ImageIcon(getClass().getResource("/lpoo/res/sword-64x64.png"));

		this.s_dart_resized = s_dart.getImage().getScaledInstance(s_width, s_height, Image.SCALE_DEFAULT);
		this.s_dragon_resized = s_dragon.getImage().getScaledInstance(s_width, s_height, Image.SCALE_DEFAULT);
		this.s_hero_resized = s_hero.getImage().getScaledInstance(s_width, s_height, java.awt.Image.SCALE_DEFAULT);
		this.s_shield_resized = s_shield.getImage().getScaledInstance(s_width, s_height, Image.SCALE_DEFAULT);
		this.s_sword_resized = s_sword.getImage().getScaledInstance(s_width, s_height, Image.SCALE_DEFAULT);
	}

	protected void generateMaze() 
	{
		RandomMaze m = new RandomMaze(m_width);
		m_maze = m.getMatrix();
		repaint();
	}

	protected void setMazeSize(int w, int h) 
	{
		m_width = w;
		m_height = h;

		initializeMaze();
	}

	protected void erase() 
	{
		for (char[] r : m_maze) 
		{
			Arrays.fill(r, ' ');
		}

		undoStack.clear();
		redoStack.clear();
		repaint();
	}

	protected void undo() 
	{
		if (!undoStack.empty()) 
		{
			LastAction la = undoStack.pop();
			m_maze[la.position.y][la.position.x] = la.oldSymbol;
			pushRedo(la.newSymbol, la.position.x, la.position.y);
			repaint();
		}
	}

	protected void redo() 
	{
		if (!redoStack.empty()) 
		{
			LastAction la = redoStack.pop();
			placeSymbol(la.position.x, la.position.y, la.newSymbol);
		}
	}

	private class LastAction 
	{
		protected Point position;
		protected char oldSymbol;
		protected char newSymbol;

		LastAction(char oldSymbol, char newSymbol, Point position) 
		{
			this.oldSymbol = oldSymbol;
			this.newSymbol = newSymbol;
			this.position = position;
		}
	}

	public void setSymbol(char s) 
	{
		this.m_symbol = s;
	}

	private void updateCounters() 
	{
		initializeCounters();

		for (int i = 0; i < m_height; i++) 
		{
			for (int j = 0; j < m_width; j++) 
			{
				switch (m_maze[i][j]) 
				{
				case 'h': case 'H': case 'a': case 'A':
					++numPlayers;
					break;
				case 'd': case 'D':
					++numDragons;
					break;
				case 'V':
					++numShields;
					break;
				case 'E':
					++numSwords;
					break;
				case '*':
					++numDarts;
					break;
				}
			}
		}
	}

	private void placeHero(int x, int y) 
	{
		if (numPlayers >= 1) 
		{
			JOptionPane.showMessageDialog(getParent(), "Number of players must not be greater than 1!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else 
		{
			writeSymbol(x, y, 'h');
		}
	}

	private void placeDragon(int x, int y) 
	{
		if (numDragons >= maxDragons) 
		{
			JOptionPane.showMessageDialog(getParent(), "Number of dragons must not be greater than " + maxDragons + "!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else 
		{
			writeSymbol(x, y, 'D');
		}
	}

	private void placeSword(int x, int y) 
	{
		if (numSwords >= 1) 
		{
			JOptionPane.showMessageDialog(getParent(), "Number of swords must not be greater than 1!", "Error",	JOptionPane.ERROR_MESSAGE);
		} 
		else 
		{
			writeSymbol(x, y, 'E');
		}
	}

	private void placeShield(int x, int y) 
	{
		if (numShields >= 1) 
		{
			JOptionPane.showMessageDialog(getParent(), "Number of shields must not be greater than 1!", "Error", JOptionPane.ERROR_MESSAGE);
		} 
		else 
		{
			writeSymbol(x, y, 'V');
		}
	}

	private void placeDart(int x, int y) 
	{
		if (numDarts >= maxDragons) 
		{
			JOptionPane.showMessageDialog(getParent(), "Number of darts must not be greater than " + maxDragons + "!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else 
		{
			writeSymbol(x, y, '*');
		}
	}

	private void placeWall(int x, int y) 
	{
		writeSymbol(x, y, 'X');
	}

	private void placeBlank(int x, int y) 
	{
		writeSymbol(x, y, ' ');
	}

	private void writeSymbol(int x, int y, char s) 
	{
		undoStack.add(new LastAction(m_maze[y][x], s, new Point(x, y)));
		m_maze[y][x] = s;
	}

	protected void placeSymbol(int x, int y, char s) 
	{
		if (x < 0 || x >= m_width || y < 0 || y >= m_height) 
		{
			return;
		}

		if (m_maze[y][x] == s) 
		{
			return;
		}

		updateCounters();

		switch (s) 
		{
		case 'h':
			placeHero(x, y);
			break;
		case 'D':
			placeDragon(x, y);
			break;
		case 'X':
			placeWall(x, y);
			break;
		case ' ':
			placeBlank(x, y);
			break;
		case 'E':
			placeSword(x, y);
			break;
		case 'V':
			placeShield(x, y);
			break;
		case '*':
			placeDart(x, y);
			break;
		}

		repaint();
	}

	public int[] getValues() 
	{
		int[] values = new int[8];

		values[0] = m_width;
		values[1] = m_height;
		values[2] = numPlayers;
		values[3] = numDragons;
		values[4] = numSwords;
		values[5] = numDarts;
		values[6] = numShields;

		return values;
	}

	public void pushRedo(char newSymbol, int x, int y) 
	{
		redoStack.push(new LastAction(m_maze[y][x], newSymbol, new Point(x, y)));
	}

	@Override
	public void paintComponent(Graphics g) 
	{
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, getSize().width, getSize().height);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w_size.width, w_size.height);

		int y = 0;

		for (int i = 0; i < m_height; i++) 
		{
			int x = 0;

			for (int j = 0; j < m_width; j++) 
			{
				switch (m_maze[i][j]) 
				{
				case 'h': case 'a': case 'H': case 'A':
					g.drawImage(s_hero_resized, x, y, null);
					break;
				case 'D': case 'd':
					g.drawImage(s_dragon_resized, x, y, null);
					break;
				case 'X':
					g.setColor(Color.CYAN);
					g.fillRect(x, y, s_width, s_height);
					break;
				case 'E':
					g.drawImage(s_sword_resized, x, y, null);
					break;
				case '*':
					g.drawImage(s_dart_resized, x, y, null);
					break;
				case 'V':
					g.drawImage(s_shield_resized, x, y, null);
					break;
				}

				x += s_width;
			}

			y += s_height;
		}
	}

	@Override
	public void mouseClicked(MouseEvent me) 
	{
	}

	@Override
	public void mouseDragged(MouseEvent me) 
	{
		placeSymbol(me.getX() / s_width, me.getY() / s_height, m_symbol);

		if (!redoStack.empty()) 
		{
			redoStack.clear();
		}
	}

	@Override
	public void mousePressed(MouseEvent me) 
	{
		placeSymbol(me.getX() / s_width, me.getY() / s_height, m_symbol);

		if (!redoStack.empty()) 
		{
			redoStack.clear();
		}
	}

	@Override
	public void mouseReleased(MouseEvent me) 
	{
	}

	@Override
	public void mouseMoved(MouseEvent me) 
	{
	}

	@Override
	public void mouseEntered(MouseEvent me) 
	{
	}

	@Override
	public void mouseExited(MouseEvent me) 
	{
	}
}