package fs;

public class FileSystem
{
	private String type;
	private Folder root;

	protected static NameFormatter nf;

	public FileSystem(String type) throws DuplicateNameException
	{
		this.type = type;
		this.root = new Folder(null, "root");
	}

	public final String getType()
	{
		return this.type;
	}

	public static void setNameFormatter(NameFormatter nameFormatter)
	{
		nf = nameFormatter;
	}

	public final Folder getRoot()
	{
		return this.root;
	}
}