//Ben Girone CSC 403 12/5/17
//This file defines the simulation timer class. 

package schedule;

public class bmgSimulationTimer
{
	//data members
	private static bmgSimulationTimer instance = null;
	private int value = 0;
	
	/**
	 * Gets the value of the timer.
	 * @return An integer representation of the current simulation time.
	 */
	public int getValue()
	{
		return value;
	}

	/**
	 * Moves the simulation time forward by the specified time.
	 * @param time The amount of time units to move the simulation.
	 */
	public void forward(int time)
	{
		value += time;
	}
	
	/**
	 * Gets the singleton instance of the timer.
	 * @return The singleton instance of this class.
	 */
	public static bmgSimulationTimer getTimer() 
	{
		if(instance == null) 
		{
			instance = new bmgSimulationTimer();
		}
		
		return instance;
	}
	
	/**
	 * Resets the simulation timer.
	 */
	public void reset()
	{
		value = 0;
	}
}
