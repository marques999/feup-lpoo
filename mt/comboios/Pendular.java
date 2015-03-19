package comboios;

public class Pendular extends Comboio
{
	public Pendular(String nome)
	{
		super(nome, "Pendular", new ServicoSemEnjoos());
	}
}