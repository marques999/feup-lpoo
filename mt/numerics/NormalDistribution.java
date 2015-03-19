package numerics;

public class NormalDistribution extends ProbabilityDistribution
{
	protected NormalDistribution(String id, double mean, double stddev)
	{
		super(id, 0, 1);
	}

	protected NormalDistribution(double mean, double stddev)
	{
		super(mean, stddev);
	}

	protected NormalDistribution()
	{
		super(0, 1);
	}

	public double probabilityDensityFunction(double x)
	{
		double coeff = 1.0 / (getStddev() * Math.sqrt(2 * Math.PI));
		double exponent = ((x - getMean()) * (x - getMean())) / (2 * getStddev() * getStddev());

		return coeff * Math.exp(-exponent);
	}

	public double calcRangeProbability(double xmin, double xmax)
	{
		return SimpsonMethod.calcIntegral((x)->probabilityDensityFunction(x), xmin, xmax, 5E-7);
	}

	public double calcLeftProbability(double xmax)
	{
		return SimpsonMethod.calcIntegral((x)->probabilityDensityFunction(x), -9999, xmax, 5E-7);
	}

	public String toString()
	{
		return "N(" + getMean() + ", " + getStddev() + ")";
	}
}