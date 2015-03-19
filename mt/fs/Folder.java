package fs;
public class Folder extends Node
{
	private Node[] f_child;
	private int f_cid;

	protected Folder(Node parent, String name) throws DuplicateNameException
	{
		super(parent, name, 0);

		this.f_cid = 0;
		this.f_child = new Node[0];
	}

	protected final Node[] getChild()
	{
		return this.f_child;
	}

	protected void addChild(Node newChild)
	{
		Node[] tempArray = new Node[f_cid + 1];

		int i;
		for (i = 0; i < f_cid; i++)
		{
			tempArray[i] = f_child[i];
		}

		tempArray[i] = newChild;
		f_child = tempArray;
		f_cid++;
	}

	protected final int getSize()
	{
		int totalSize = 0;

		for (Node node : f_child)
		{
			totalSize += node.getSize();
		}

		return totalSize;
	}

	protected Folder clone(Node newParent, String newName) throws DuplicateNameException
	{
		Folder newNode = new Folder(newParent, newName);

		for (Node node : f_child)
		{
			node.clone(newParent, node.getName());
		}

		newNode.f_cid = this.f_cid;

		return newNode;
	}

	protected Node getChildByName(String name)
	{
		for (Node node : f_child)
		{
			if (name.equals(node.getName()))
			{
				return node;
			}
		}

		return null;
	}
}
