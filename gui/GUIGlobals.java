package lpoo.gui;

import static java.awt.event.KeyEvent.*;
import java.io.*;
import javax.swing.*;
import lpoo.logic.GameState;

public class GUIGlobals
{
	protected static transient final int keyUp = 0;
	protected static transient final int keyDown = 1;
	protected static transient final int keyLeft = 2;
	protected static transient final int keyRight = 3;
	protected static transient final int keyAction = 4;
	protected static transient final int[] defaultKeys = { VK_W, VK_S, VK_A, VK_D, VK_SPACE };

	protected static int dragonDifficulty = GameState.DRAGON_STATIC_NOSLEEP;
	protected static int userDifficulty = 1;
	protected static int interfaceZoom = 0;
	protected static int mazeWidth = 7;
	protected static int mazeHeight = 7;
	protected static int editorWidth = 11;
	protected static int editorHeight = 11;
	protected static int numberDragons = 1;
	protected static int[] currentKeys = defaultKeys.clone();

	/**
	 * reads user preferences from a stream
	 * @param aInputStream objectInputStream to read from
	 * @throws IOException if stream not found or invalid
	 * @throws ClassNotFoundException
	 */
	protected static void read(ObjectInputStream aInputStream) throws IOException, ClassNotFoundException
	{
		dragonDifficulty = aInputStream.readInt();
		userDifficulty = aInputStream.readInt();
		numberDragons = aInputStream.readInt();
		interfaceZoom = aInputStream.readInt();
		mazeWidth = aInputStream.readInt();
		mazeHeight = aInputStream.readInt();
		currentKeys = (int[]) aInputStream.readObject();
	}

	/**
	 * writes user preferences to a stream
	 * @param aOutputStream objectOutputStream to write to
	 * @throws IOException if stream invalid, insufficient permissions or file not found
	 */
	protected static void write(ObjectOutputStream aOutputStream) throws IOException
	{
		aOutputStream.writeInt(dragonDifficulty);
		aOutputStream.writeInt(userDifficulty);
		aOutputStream.writeInt(numberDragons);
		aOutputStream.writeInt(interfaceZoom);
		aOutputStream.writeInt(mazeWidth);
		aOutputStream.writeInt(mazeHeight);
		aOutputStream.writeObject(currentKeys);
	}

	protected static void abort(Exception ex, JFrame parent)
	{
		JOptionPane.showMessageDialog(parent, "Exception thrown:\n" + ex.getLocalizedMessage() + ".\nThe program will now exit...", "Error", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
}