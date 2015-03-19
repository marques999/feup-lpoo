package fs;

abstract class NameFormatter
{
	private char nf_separator;

	protected final char getSeparator()
	{
		return this.nf_separator;
	}

	protected void setSeparator(char nf_separator)
	{
		this.nf_separator = nf_separator;
	}
}

class DOSFormatter extends NameFormatter
{
	public DOSFormatter()
	{
		setSeparator('\\');
	}
}

class UnixFormatter extends NameFormatter
{
	public UnixFormatter()
	{
		setSeparator('/');
	}
}