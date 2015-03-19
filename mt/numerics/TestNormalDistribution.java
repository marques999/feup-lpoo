package numerics;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestNormalDistribution 
{
	private static final double prob2sigma = 0.477250; 	
	private static final double precision  = 5E-7;

	@SuppressWarnings("static-method")
	@Test
	public void testConstructor() 
	{
		NormalDistribution n = new NormalDistribution(1.0, 3.0);
		
		assertEquals(1.0, n.getMean(), 0.0);
		assertEquals(3.0, n.getStddev(), 0.0);
	}

	@SuppressWarnings("static-method")
	@Test
	public void testDefaultConstructor() 
	{
		NormalDistribution n = new NormalDistribution();
		
		assertEquals(0.0, n.getMean(), 0.0);
		assertEquals(1.0, n.getStddev(), 0.0);
	}
	
	@SuppressWarnings("static-method")
	@Test(expected = IllegalArgumentException.class)
	public void testInvallidStddev() 
	{
		new NormalDistribution(0, 0);
	}

	@SuppressWarnings("static-method")
	@Test
	public void testProbabilityDistribution() 
	{
		ProbabilityDistribution d = new NormalDistribution(0,1);
		
		assertEquals(0.0, d.getMean(), 0.0);
		assertEquals(1.0, d.getStddev(), 0.0);		
	}

	@SuppressWarnings("static-method")
	@Test
	public void testProbabilityDensityFunction() 
	{
		ProbabilityDistribution d = new NormalDistribution(0.0, 1.0);
		
		assertEquals(0.3989423, d.probabilityDensityFunction(0.0), precision);
		assertEquals(0.2419707, d.probabilityDensityFunction(1.0), precision);
		assertEquals(0.2419707, d.probabilityDensityFunction(-1.0), precision);
	}

	@SuppressWarnings("static-method")
	@Test
	public void testCalcRangeProbability() 
	{
		ProbabilityDistribution n = new NormalDistribution(0, 1);
		
		assertEquals(prob2sigma, n.calcRangeProbability(0, 2), precision);
		assertEquals(prob2sigma, n.calcRangeProbability(-2, 0), precision);
		assertEquals(2*prob2sigma, n.calcRangeProbability(-2, 2), precision);
	}

	@SuppressWarnings("static-method")
	@Test
	public void testCalcLeftProbability() 
	{
		ProbabilityDistribution n = new NormalDistribution(1, 1);
		
		assertEquals(0.5, n.calcLeftProbability(1.0), precision);
		assertEquals(0.5 + prob2sigma, n.calcLeftProbability(3.0), precision);
		assertEquals(0.5 - prob2sigma, n.calcLeftProbability(-1.0), precision);
	}

	@SuppressWarnings("static-method")
	@Test
	public void testEquals() 
	{
		NormalDistribution d1 = new NormalDistribution(0, 1);
		NormalDistribution d2 = new NormalDistribution(0, 1);
		
		assertNotSame(d1, d2);
		assertEquals(d1, d2);
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void testAsString() 
	{
		NormalDistribution d1 = new NormalDistribution(2.0, 5.0);
		
		assertEquals("N(2.0, 5.0)", d1 + "");
	}
	
	@SuppressWarnings({ "static-method", "unused" })
	@Test
	public void testFindDistribution() 
	{
		NormalDistribution X = new NormalDistribution("X", 2.0, 5.0);
		NormalDistribution Y = new NormalDistribution("Y", 2.0, 5.0);
		
		assertSame(X, ProbabilityDistribution.find("X"));
		assertNull(ProbabilityDistribution.find("Z"));
	}
}
