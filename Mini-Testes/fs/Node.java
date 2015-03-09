package fs;

public abstract class Node
{
	private int n_id;
	protected int n_size;
	private String n_name;
	protected Node n_parent;

	private static int numberNodes;

	protected Node(Node parent, String name, int size)
			throws DuplicateNameException
	{
		this.n_name = name;
		this.n_parent = parent;

		if (this.n_parent != null && this.n_parent.getChildByName(name) != null)
		{
			throw new DuplicateNameException();
		}

		numberNodes++;

		this.n_id = numberNodes;
		this.n_size = size;

		if (this.n_parent != null)
		{
			this.n_parent.addChild(this);
		}
	}

	protected final String getName()
	{
		return this.n_name;
	}

	protected final int getNumber()
	{
		return this.n_id;
	}

	protected abstract int getSize();

	protected final Node getParent()
	{
		return this.n_parent;
	}

	protected String getPath()
	{
		if (n_parent != null)
		{
			return n_parent.getPath() + FileSystem.nf.getSeparator() + n_name;
		}

		return "";
	}

	protected abstract Node clone(Node newParent, String newName) throws DuplicateNameException;

	protected static void resetNumbering(int newNumber)
	{
		numberNodes = newNumber;
	}

	protected abstract Node getChildByName(String name);

	protected abstract void addChild(Node newChild);
}
