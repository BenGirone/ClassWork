package schedule;

import java.util.Queue;

public abstract class bmgAlgorithm
{
	protected Queue<bmgProcess> processes;
	protected bmgProcess currentProcess = null;
	protected boolean isDone = false;
	
	public bmgAlgorithm(Queue<bmgProcess> processes)
	{
		this.processes = processes;
	}
	
	public Queue<bmgProcess> getProcesses()
	{
		return processes;
	}
	
	public abstract void run();
	
	protected abstract void updateQueues();
	
	protected abstract void executeNextBurst();
	
	protected abstract void getNextProcess();
	
	public boolean isDone()
	{
		return isDone;
	}
}
