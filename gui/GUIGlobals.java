package lpoo.gui;

import static java.awt.event.KeyEvent.*;
import java.io.*;

public class GUIGlobals implements Serializable
{
	protected static transient final int keyUp = 0;
	protected static transient final int keyDown = 1;
	protected static transient final int keyLeft = 2;
	protected static transient final int keyRight = 3;
	protected static transient final int keyAction = 4;
	protected static transient final int[] defaultKeys = { VK_W, VK_S, VK_A, VK_D, VK_SPACE };

	protected static int dragonDifficulty = 2;
	protected static int userDifficulty = 1;
	protected static int mazeWidth = 7;
	protected static int mazeHeight = 7;
	protected static int numberDragons = 1;
	protected static int[] currentKeys = defaultKeys.clone();

	/**
	 * @brief reads user's preferences from stream
	 * @param aInputStream file/object input stream to read from
	 * @throws IOException if stream not found or invalid
	 * @throws ClassNotFoundException
	 */
	private static void readObject(ObjectInputStream aInputStream) throws IOException, ClassNotFoundException
	{
		dragonDifficulty = aInputStream.readInt();
		mazeWidth = aInputStream.readInt();
		mazeHeight = aInputStream.readInt();
		numberDragons = aInputStream.readInt();
		currentKeys = (int[]) aInputStream.readObject();
	}

	/**
	 * @brief writes user's preferences to a stream
	 * @param aOutputStream file/object output stream to write to
	 * @throws IOException if stream not found or invalid
	 */
	private static void writeObject(ObjectOutputStream aOutputStream) throws IOException
	{
		aOutputStream.writeInt(dragonDifficulty);
		aOutputStream.writeInt(mazeWidth);
		aOutputStream.writeInt(mazeHeight);
		aOutputStream.writeInt(numberDragons);
		aOutputStream.writeObject(currentKeys);
	}
}