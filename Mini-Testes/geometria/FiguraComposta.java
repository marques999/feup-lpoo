package geometria;

public class FiguraComposta extends Figura implements Countable
{
	Figura[] figuras;

	public FiguraComposta(Figura[] figuras)
	{
		this.figuras = figuras;
	}

	public double getArea()
	{
		double areaTotal = 0.0;

		for (Figura figura : figuras)
		{
			areaTotal += figura.getArea();
		}

		return areaTotal;
	}

	public double getPerimetro()
	{
		double perimetroTotal = 0.0;

		for (Figura figura : figuras)
		{
			perimetroTotal += figura.getPerimetro();
		}

		return perimetroTotal;
	}

	public int count()
	{
		return figuras.length;
	}
}
