package schedule;

public class bmgTimeQuantum
{
	private int interval;
	private int lastInterrupt;
	
	public bmgTimeQuantum(int interval)
	{
		this.interval = interval;
		this.lastInterrupt = bmgSimulationTimer.getTimer().getValue();
	}
	
	public boolean shouldInterrupt()
	{
		if ((lastInterrupt + interval) <= bmgSimulationTimer.getTimer().getValue())
		{
			reset();
			return true;
		}
		
		return false;
	}

	public void reset()
	{
		lastInterrupt = bmgSimulationTimer.getTimer().getValue();
	}
}
