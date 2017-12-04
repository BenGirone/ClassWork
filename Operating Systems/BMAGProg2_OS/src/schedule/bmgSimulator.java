package schedule;

public class bmgSimulator
{
	public bmgSimulationTimer timer = new bmgSimulationTimer();
	
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
