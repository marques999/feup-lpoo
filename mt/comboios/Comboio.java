package comboios;

import java.util.LinkedList;

public class Comboio
{
	private int numPassageiros;
	
	private String nome;
	private String modelo;
	private ServicoABordo servico;
	private LinkedList<Carruagem> carruagens = new LinkedList<Carruagem>();

	public Comboio(String nome)
	{
		this(nome, "Comboio", new ServicoRegular());
	}

	public Comboio(String name, String modelo, ServicoABordo servico)
	{
		this.nome = name;
		this.modelo = modelo;
		this.numPassageiros = 0;
		this.servico = servico;
	}

	public final String getNome()
	{
		return this.nome;
	}

	public final ServicoABordo getServicoABordo()
	{
		return this.servico;
	}

	public void setServicoABordo(ServicoABordo servico)
	{
		this.servico = servico;
	}

	public final String getDescricaoServico()
	{
		return this.servico.getDescricaoServico();
	}

	public final String getModelo()
	{
		return this.modelo;
	}

	public final int getNumCarruagens()
	{
		return carruagens.size();
	}

	public final int getNumPassageiros()
	{
		return numPassageiros;
	}

	public final int getLugaresLivres()
	{
		return getNumLugares() - numPassageiros;
	}

	public void addCarruagem(Carruagem c)
	{
		carruagens.addLast(c);
	}

	public void removeAllCarruagens(int n)
	{
		carruagens.removeIf((c) -> (c.getNumLugares() == n));
	}

	public void addPassageiros(int n) throws PassengerCapacityExceeded
	{
		if (getNumLugares() < n)
		{
			throw new PassengerCapacityExceeded();
		}

		numPassageiros += n;
	}

	public void removePassageiros()
	{
		numPassageiros = 0;
	}

	public void removePassageiros(int n)
	{
		numPassageiros -= n;

		if (numPassageiros < 0)
		{
			numPassageiros = 0;
		}
	}

	public final int getNumLugares()
	{
		int numeroLugares = 0;

		for (Carruagem c : carruagens)
		{
			numeroLugares += c.getNumLugares();
		}

		return numeroLugares;
	}

	public final Carruagem getCarruagemByOrder(int index)
	{
		if (index < carruagens.size())
		{
			return carruagens.get(index);
		}

		return null;
	}

	public String toString()
	{
		String descLugares = getNumLugares() == 1 ? " lugar, " : " lugares, ";
		String descCarruagens = getNumCarruagens() == 1 ? " carruagem, " : " carruagens, ";
		String descPassageiros = getNumPassageiros() == 1 ? " passageiro" : " passageiros";

		return getModelo() + " " + nome + ", " + getNumCarruagens()
				+ descCarruagens + getNumLugares() + descLugares
				+ getNumPassageiros() + descPassageiros;
	}

	public boolean equals(Object o)
	{
		if (!(o instanceof Comboio) || o == null)
		{
			return false;
		}
		
		if (getNumCarruagens() != ((Comboio) o).getNumCarruagens())
		{
			return false;
		}
		
		for (int i = 0; i < carruagens.size(); i++)
		{
			int numLugaresCarruagemA = carruagens.get(i).getNumLugares();
			int numLugaresCarruagemB = ((Comboio) o).getCarruagemByOrder(i).getNumLugares();
			
			if (numLugaresCarruagemA != numLugaresCarruagemB)
			{
				return false;
			}
		}
		
		return true;
	}
}