package fs;

public class File extends Node
{
	protected File(Node parent, String name) throws DuplicateNameException
	{
		super(parent, name, 0);
	}

	protected File(Node parent, String name, int size) throws DuplicateNameException
	{
		super(parent, name, size);
	}

	protected Node getChildByName(String name)
	{
		return null;
	}

	protected final int getSize()
	{
		return this.n_size;
	}

	protected File clone(Node newParent, String newName) throws DuplicateNameException
	{
		File newNode = new File(newParent, newName);

		return newNode;
	}

	protected void addChild(Node newChild)
	{
		// DO NOTHING
	}
}