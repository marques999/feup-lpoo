package lpoo.gui;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import lpoo.logic.Point;

public class AreaEdicao extends AreaDesenho implements MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = -785925844156498848L;

	private char mazeSymbol;
	private Stack<LastAction> undoStack;
	private Stack<LastAction> redoStack;

	private int maxDragons;
	private int maxDarts;
	private int maxDoors;
	private int maxPlayers;
	private int maxShields;
	private int maxSwords;
	protected int numDragons;
	protected int numDarts;
	protected int numDoors;
	protected int numPlayers;
	protected int numShields;
	protected int numSwords;

	public AreaEdicao()
	{
		this(11, 11);
	}

	public AreaEdicao(int w, int h)
	{
		super(w, h);

		mazeSymbol = ' ';
		undoStack = new Stack<>();
		redoStack = new Stack<>();

		initializeCounters();
		resetCounters();
		updateCounters();
		revalidate();
		repaint();
		addMouseListener(this);
		addMouseMotionListener(this);
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
				case 'h':
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
			maze.placeSymbol(la.position.x, la.position.y, la.oldSymbol);
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

	private void pushRedo(char newSymbol, int x, int y)
	{
		redoStack.push(new LastAction(maze.symbolAt(x, y), newSymbol, new Point(x, y)));
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

	protected void setSymbol(char s)
	{
		this.mazeSymbol = s;
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
					maze.setExitPosition(new Point(x, y));
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
			writeSymbol(x, y, 'h');
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
		repaint();
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

	protected boolean validateMaze()
	{
		updateCounters();

		boolean validationSuccessful = true;
		String dialogMessage = "";

		if (numPlayers < 1)
		{
			dialogMessage += "You must place the player first.\n";
			validationSuccessful = false;
		}

		if (numDoors < 1)
		{
			dialogMessage += "You must place an exit.\n";
			validationSuccessful = false;
		}

		if (numDragons < 1)
		{
			dialogMessage += "You must place at least one dragon.\n";
			validationSuccessful = false;
		}

		if (numShields < 1)
		{
			dialogMessage += "You must place the shield.\n";
			validationSuccessful = false;
		}

		if (numSwords < 1)
		{
			dialogMessage += "You must place the sword (darts are optional).\n";
			validationSuccessful = false;
		}

		if (!GUIValidation.checkBoundaries(maze))
		{
			dialogMessage += "Maze boundaries must have exactly one exit and everything else walls.\n";
			validationSuccessful = false;
		}

		if (validationSuccessful)
		{
			JOptionPane.showMessageDialog(getParent(), "Maze successfully validated!", "Validation results", JOptionPane.OK_OPTION);
		}
		else
		{
			JOptionPane.showMessageDialog(getParent(), dialogMessage, "Validation results", JOptionPane.ERROR_MESSAGE);

		}

		return validationSuccessful;
	}

	protected int getDifficulty()
	{
		int mazeDifficulty;

		if (mazeCells < 13 * 13)
		{
			mazeDifficulty = 1;
		}
		else if (mazeCells < 17 * 17)
		{
			mazeDifficulty = 2;
		}
		else if (mazeCells < 23 * 23)
		{
			mazeDifficulty = 3;
		}
		else
		{
			mazeDifficulty = 4;
		}

		mazeDifficulty *= 0.5;

		int dragonDifficulty = numDragons / maxDragons;
		int weaponDifficulty = 1 - (numDarts / maxDarts);

		return Math.round(mazeDifficulty + weaponDifficulty + dragonDifficulty);
	}

	@Override
	public void mouseDragged(MouseEvent me)
	{
		placeSymbol(me.getX() / spriteWidth, me.getY() / spriteHeight, mazeSymbol);

		if (!redoStack.empty())
		{
			redoStack.clear();
		}
	}

	@Override
	public void mousePressed(MouseEvent me)
	{
		placeSymbol(me.getX() / spriteWidth, me.getY() / spriteHeight, mazeSymbol);

		if (!redoStack.empty())
		{
			redoStack.clear();
		}
	}

	@Override
	public void mouseClicked(MouseEvent me)
	{
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