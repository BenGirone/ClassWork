//Ben Girone CSC 403 12/5/17
//This file defines the time quantum class. 

package schedule;

public class bmgTimeQuantum
{
	//data members
	private int interval;
	private int lastInterrupt;
	
	//constructor
	public bmgTimeQuantum(int interval)
	{
		this.interval = interval;
		this.lastInterrupt = bmgSimulationTimer.getTimer().getValue();
	}
	
	/**
	 * Checks if the time quantum should interrupt process execution.
	 * @return True if the time has sufficiently increased since the last interrupt, false otherwise.
	 */
	public boolean shouldInterrupt()
	{
		if ((lastInterrupt + interval) <= bmgSimulationTimer.getTimer().getValue())
		{
			reset();
			return true;
		}
		
		return false;
	}

	/**
	 * Resets the time quantum.
	 */
	public void reset()
	{
		lastInterrupt = bmgSimulationTimer.getTimer().getValue();
	}
}
