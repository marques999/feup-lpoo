package geometria;

public class Circulo extends Figura
{
	private Ponto centro;
	private int raio;

	public Circulo(Ponto centro, int raio) throws IllegalArgumentException
	{
		if (raio <= 0)
		{
			throw new IllegalArgumentException();
		}

		this.centro = centro;
		this.raio = raio;
	}

	public final Ponto getCentro()
	{
		return this.centro;
	}

	public final int getRaio()
	{
		return this.raio;
	}

	public double getArea()
	{
		return Math.PI * raio * raio;
	}

	public double getPerimetro()
	{
		return 2 * Math.PI * raio;
	}
}
