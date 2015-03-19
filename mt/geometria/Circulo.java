package geometria;

public class Circulo extends Figura
{
	private Ponto centro;
	private int raio;

	protected Circulo(Ponto centro, int raio)
	{
		if (raio <= 0)
		{
			throw new IllegalArgumentException();
		}

		this.centro = centro;
		this.raio = raio;
	}

	protected final Ponto getCentro()
	{
		return this.centro;
	}

	protected final int getRaio()
	{
		return this.raio;
	}

	protected final double getArea()
	{
		return Math.PI * raio * raio;
	}

	protected final double getPerimetro()
	{
		return 2 * Math.PI * raio;
	}
}
