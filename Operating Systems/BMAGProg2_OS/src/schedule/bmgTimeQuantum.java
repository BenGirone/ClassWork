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
		return ((lastInterrupt + interval) <= bmgSimulationTimer.getTimer().getValue());
	}
}
