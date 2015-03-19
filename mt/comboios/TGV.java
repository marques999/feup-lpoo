package comboios;

public class TGV extends Comboio
{
	public TGV(String nome)
	{
		super(nome, "TGV", new ServicoPrioritario());
	}
}