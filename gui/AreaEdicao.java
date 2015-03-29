package lpoo.gui;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import lpoo.logic.Point;

public class AreaEdicao extends AreaDesenho implements MouseListener, MouseMotionListener
{
	private char m_symbol;

	private Stack<LastAction> undoStack;
	private Stack<LastAction> redoStack;

	private int maxDragons;
	private int maxDarts;
	private int maxDoors;
	private int maxPlayers;
	private int maxShields;
	private int maxSwords;

	private int numDragons;
	private int numDarts;
	private int numDoors;
	private int numPlayers;
	private int numShields;
	private int numSwords;

	public AreaEdicao()
	{
		this(11, 11);
	}

	public AreaEdicao(int w, int h)
	{
		super(w, h);

		m_symbol = ' ';
		undoStack = new Stack<>();
		redoStack = new Stack<>();

		addMouseListener(this);
		addMouseMotionListener(this);
		initializeCounters();
		resetCounters();
		revalidate();
		repaint();
	}

	private void initializeCounters()
	{
		maxDragons = mazeCells / 60;
		maxDarts = maxDragons + 1;
		maxDoors = 1;
		maxShields = 1;
		maxSwords = 1;
		maxPlayers = 1;
	}

	private void resetCounters()
	{
		numDragons = 0;
		numDarts = 0;
		numDoors = 0;
		numShields = 0;
		numSwords = 0;
		numPlayers = 0;
	}

	private void updateCounters()
	{
		resetCounters();

		for (int i = 0; i < mazeHeight; i++)
		{
			for (int j = 0; j < mazeWidth; j++)
			{
				switch (maze.symbolAt(j, i))
				{
				case 'H':
					++numPlayers;
					break;
				case 'D':
					++numDragons;
					break;
				case 'S':
					++numDoors;
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

	@Override
	protected void initializeMaze(int w, int h)
	{
		super.initializeMaze(w, h);

		initializeCounters();
		resetCounters();
	}

	@Override
	protected void erase()
	{
		super.erase();
		undoStack.clear();
		redoStack.clear();
	}

	protected void undo()
	{
		if (!undoStack.empty())
		{
			LastAction la = undoStack.pop();
			maze.placeSymbol(la.position.y, la.position.x, la.oldSymbol);
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
			repaint();
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

	private void placeDart(int x, int y)
	{
		if (numDarts < maxDarts)
		{
			if (maze.isWall(x, y))
			{
				JOptionPane.showMessageDialog(getParent(), "Darts must not be placed on maze borders!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				writeSymbol(x, y, '*');
			}
		}
		else
		{
			JOptionPane.showMessageDialog(getParent(), "Number of darts must not be greater than " + maxDarts + "!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void placeDoor(int x, int y)
	{
		if (numDoors < maxDoors)
		{
			if (maze.isWall(x, y))
			{
				if (maze.isCorner(x, y))
				{
					JOptionPane.showMessageDialog(getParent(), "Doors must not be placed in corners!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					writeSymbol(x, y, 'S');
				}
			}
			else
			{
				JOptionPane.showMessageDialog(getParent(), "Doors must be placed on maze borders!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(getParent(), "Number of doors must not be greater than 1.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void placeDragon(int x, int y)
	{
		if (numDragons < maxDragons)
		{
			if (maze.isWall(x, y))
			{
				JOptionPane.showMessageDialog(getParent(), "Dragons must not be placed on maze borders!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				writeSymbol(x, y, 'D');
			}
		}
		else
		{
			JOptionPane.showMessageDialog(getParent(), "Number of dragons must not be greater than " + maxDragons + "!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void placeHero(int x, int y)
	{
		if (numPlayers >= maxPlayers)
		{
			JOptionPane.showMessageDialog(getParent(), "Number of players must not be greater than 1!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			writeSymbol(x, y, 'H');
		}
	}

	private void placeSword(int x, int y)
	{
		if (numSwords < maxSwords)
		{
			if (maze.isWall(x, y))
			{
				JOptionPane.showMessageDialog(getParent(), "Swords must not be placed on maze borders!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				writeSymbol(x, y, 'E');
			}
		}
		else
		{
			JOptionPane.showMessageDialog(getParent(), "Number of swords must not be greater than 1!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void placeShield(int x, int y)
	{
		if (numShields < maxShields)
		{
			if (maze.isWall(x, y))
			{
				JOptionPane.showMessageDialog(getParent(), "Shields must not be placed on maze borders!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				writeSymbol(x, y, 'V');
			}
		}
		else
		{
			JOptionPane.showMessageDialog(getParent(), "Number of shields must not be greater than 1!", "Error", JOptionPane.ERROR_MESSAGE);
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
		undoStack.add(new LastAction(maze.symbolAt(x, y), s, new Point(x, y)));
		maze.placeSymbol(x, y, s);
	}

	protected void placeSymbol(int x, int y, char s)
	{
		if (x < 0 || x >= mazeWidth || y < 0 || y >= mazeHeight)
		{
			return;
		}

		if (maze.symbolAt(x, y) == s)
		{
			return;
		}

		updateCounters();

		switch (s)
		{
		case 'H':
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
		case 'S':
			placeDoor(x, y);
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
	}

	public int[] getValues()
	{
		int[] values = new int[8];

		values[0] = mazeWidth;
		values[1] = mazeHeight;
		values[2] = numPlayers;
		values[3] = numDragons;
		values[4] = numSwords;
		values[5] = numDarts;
		values[6] = numShields;

		return values;
	}

	protected boolean validateMaze()
	{
		updateCounters();

		if (numPlayers < 1)
		{
			JOptionPane.showMessageDialog(getParent(), "You must place the player first!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (numDoors < 1)
		{
			JOptionPane.showMessageDialog(getParent(), "You must place the exit first!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (numDragons < 1)
		{
			JOptionPane.showMessageDialog(getParent(), "You must place at least one dragon.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (numShields < 1)
		{
			JOptionPane.showMessageDialog(getParent(), "You must place the shield first!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (numSwords < 1 || numDarts < numDragons)
		{
			JOptionPane.showMessageDialog(getParent(), "You must place at least one weapon (sword or darts)!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(getParent(), "Maze successfully validated.", "Information", JOptionPane.OK_OPTION);

			return true;
		}

		return false;
	}

	public void pushRedo(char newSymbol, int x, int y)
	{
		redoStack.push(new LastAction(maze.symbolAt(x, y), newSymbol, new Point(x, y)));
	}

	@Override
	public void mouseClicked(MouseEvent me)
	{
	}

	@Override
	public void mouseDragged(MouseEvent me)
	{
		placeSymbol(me.getX() / spriteWidth, me.getY() / spriteHeight, m_symbol);

		if (!redoStack.empty())
		{
			redoStack.clear();
		}

		repaint();
	}

	@Override
	public void mousePressed(MouseEvent me)
	{
		placeSymbol(me.getX() / spriteWidth, me.getY() / spriteHeight, m_symbol);

		if (!redoStack.empty())
		{
			redoStack.clear();
		}

		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent me)
	{
		repaint();
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