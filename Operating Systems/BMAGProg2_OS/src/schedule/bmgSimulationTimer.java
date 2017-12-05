package schedule;

public class bmgSimulationTimer
{
	private static bmgSimulationTimer instance = null;
	
	private int value = 0;
	
	public int getValue()
	{
		return value;
	}

	public void forward(int time)
	{
		value += time;
	}
	
	public static bmgSimulationTimer getTimer() 
	{
		if(instance == null) 
		{
			instance = new bmgSimulationTimer();
		}
		
		return instance;
	}
	
	public void reset()
	{
		value = 0;
	}
}
