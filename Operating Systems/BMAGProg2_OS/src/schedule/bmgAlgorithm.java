package schedule;

import java.util.Queue;

public abstract class bmgAlgorithm
{
	protected bmgSimulationTimer timer = new bmgSimulationTimer();
	protected Queue<bmgProcess> processes;
	protected bmgProcess currentProcess;
	
	public bmgAlgorithm(Queue<bmgProcess> processes)
	{
		this.processes = processes;
	}
	
	public abstract void run();
	
	public abstract void updateQueues();
	
	public abstract void executeNextBurst();
}
