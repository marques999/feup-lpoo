package geometria;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.TreeSet;

public class TestGeometria
{
	@SuppressWarnings("static-method")
	@Test
	public void testPonto()
	{
		Ponto p = new Ponto(1, 5);

		assertEquals(1, p.getX());
		assertEquals(5, p.getY());
	}

	@SuppressWarnings("static-method")
	@Test
	public void testCirculo()
	{
		Ponto p = new Ponto(1, 5);
		Circulo c = new Circulo(p, 3);

		assertEquals(3, c.getRaio());
		assertEquals(p, c.getCentro());
	}

	@SuppressWarnings("static-method")
	@Test(expected = IllegalArgumentException.class)
	public void testExcepcoes()
	{
		new Circulo(new Ponto(1, 5), -1);
	}

	@SuppressWarnings("static-method")
	@Test
	public void testFigura()
	{
		Figura f = new Circulo(new Ponto(0, 0), 5);

		assertEquals(Math.PI * 5 * 5, f.getArea(), 1E-10);
	}

	@SuppressWarnings("static-method")
	@Test
	public void testRectangulo()
	{
		int x1 = 1;
		int x2 = 2;
		int y1 = 1;
		int y2 = 2;

		Figura f = new Rectangulo(x1, y1, x2, y2);

		assertEquals(4.0, f.getPerimetro(), 1E-10);
	}

	@SuppressWarnings("static-method")
	@Test
	public void testFiguraComposta()
	{
		Circulo c = new Circulo(new Ponto(2, 2), 1);
		Rectangulo r = new Rectangulo(0, 0, 1, 1);

		Figura[] figuras = new Figura[] { c, r };
		Figura figuraComposta = new FiguraComposta(figuras);

		assertEquals(1 + Math.PI, figuraComposta.getArea(), 1E-10);
	}

	@SuppressWarnings("static-method")
	@Test
	public void testCountable()
	{
		Circulo c = new Circulo(new Ponto(2, 2), 1);
		Rectangulo r = new Rectangulo(0, 0, 1, 1);
		Figura[] figuras = new Figura[] { c, r };

		Countable figuraComposta = new FiguraComposta(figuras);

		assertEquals(2, figuraComposta.count());
	}

	@SuppressWarnings("static-method")
	@Test
	public void testAsText()
	{
		Ponto p = new Ponto(1, 5);

		assertEquals("(1, 5)", "" + p);
	}

	@SuppressWarnings("static-method")
	@Test
	public void testEquals()
	{
		Ponto p1 = new Ponto(1, 5);
		Ponto p2 = new Ponto(1, 5);
		Ponto p3 = new Ponto(5, 1);

		assertEquals(p1, p2);
		assertFalse(p1.equals(p3));
	}

	@SuppressWarnings("static-method")
	@Test
	public void testOrdenacaoPontos()
	{
		Ponto p1 = new Ponto(0, 0);
		Ponto p2 = new Ponto(0, 1);
		Ponto p3 = new Ponto(1, 0);

		TreeSet<Ponto> pontos = new TreeSet<Ponto>();

		pontos.add(p1);
		pontos.add(p2);
		pontos.add(p3);

		assertSame(p1, pontos.toArray()[0]);
		assertSame(p2, pontos.toArray()[1]);
		assertSame(p3, pontos.toArray()[2]);
	}
}