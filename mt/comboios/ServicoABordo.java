package comboios;

public class ServicoABordo
{
	String description;
	
	public ServicoABordo(String description)
	{
		this.description = description;
	}
	
	public final String getDescricaoServico()
	{
		return this.description;
	}
}

class ServicoRegular extends ServicoABordo
{
	public ServicoRegular()
	{
		super("Servico regular.");
	}
}

class ServicoPrioritario extends ServicoABordo
{
	public ServicoPrioritario()
	{
		super("Servico prioritario.");
	}
}

class ServicoSemEnjoos extends ServicoABordo
{
	public ServicoSemEnjoos()
	{
		super("Servico sem enjoos.");
	}
}