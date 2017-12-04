package schedule;

public class bmgSimulator
{
	private bmgAlgorithm algorithm;
	
	private double averageTT;
	
	private double averageTToverTS;
	
	public bmgSimulator(bmgAlgorithm algorithm)
	{
		this.algorithm = algorithm;
	}
	
	public void start()
	{
		algorithm.run();
	}
}
