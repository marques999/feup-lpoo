package geometria;

public class FiguraComposta extends Figura implements Countable
{
	Figura[] figuras;

	protected FiguraComposta(Figura[] figuras)
	{
		this.figuras = figuras;
	}

	protected final double getArea()
	{
		double areaTotal = 0.0;

		for (Figura figura : figuras)
		{
			areaTotal += figura.getArea();
		}

		return areaTotal;
	}

	protected final double getPerimetro()
	{
		double perimetroTotal = 0.0;

		for (Figura figura : figuras)
		{
			perimetroTotal += figura.getPerimetro();
		}

		return perimetroTotal;
	}

	public final int count()
	{
		return figuras.length;
	}
}
