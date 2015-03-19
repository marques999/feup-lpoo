package numerics;

import java.util.ArrayList;

public abstract class ProbabilityDistribution
{
	private double mean;
	private double stddev;
	private String id;
	
	private static ArrayList<ProbabilityDistribution> dists = new ArrayList<ProbabilityDistribution>();
	
	public static ProbabilityDistribution find(String id)
	{
		for (ProbabilityDistribution dist : dists)
		{
			if (dist.getID() == id)
			{
				return dist;
			}
		}
		
		return null;
	}
	
	protected ProbabilityDistribution(String id, double mean, double stddev)
	{
		if (Math.abs(stddev) < 5E-7)
		{
			throw new IllegalArgumentException();
		}
		
		this.mean = mean;
		this.stddev = stddev;
		this.id = id;
		
		dists.add(this);
	}
	
	protected ProbabilityDistribution(double mean, double stddev)
	{
		this("", mean, stddev);
	}
	
	protected ProbabilityDistribution()
	{
		this("", 0, 1);
	}
	
	protected final double getMean()
	{
		return this.mean;
	}
	
	protected final double getStddev()
	{
		return this.stddev;
	}
	
	protected final String getID()
	{
		return this.id;
	}
	
	public abstract String toString();
	
	@Override
	public boolean equals(Object o)
	{
		return mean == ((ProbabilityDistribution) o).mean && stddev == ((ProbabilityDistribution) o).stddev;
	}
	
	protected abstract double probabilityDensityFunction(double x);
	protected abstract double calcRangeProbability(double xmin, double xmax);
	protected abstract double calcLeftProbability(double x);
}
