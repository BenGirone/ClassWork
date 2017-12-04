package schedule;

import java.util.LinkedList;
import java.util.Queue;

public class bmgFCFS extends bmgAlgorithm
{
	private Queue<bmgProcess> readyQueue = new LinkedList<bmgProcess>();

	public bmgFCFS(Queue<bmgProcess> processes)
	{
		super(processes);
	}

	@Override
	public void run()
	{
		while (!processes.isEmpty() || !readyQueue.isEmpty() || !currentProcess.isFinished())
		{
			updateQueues();
			
			if (currentProcess != null)
			{
				if (!currentProcess.isFinished())
				{
					executeNextBurst();
				}
				else
				{
					currentProcess.printInfo();
					getNextProcess();
				}
			}
			else
			{
				getNextProcess();
			}
		}
		
		currentProcess.printInfo();
	}

	@Override
	protected void updateQueues()
	{
		if (!processes.isEmpty())
		{
			if (processes.peek().getArrivalTime() <= bmgSimulationTimer.getTimer().getValue())
			{
				processes.peek().printInfo();
				readyQueue.add(processes.poll()); 
			}
		}
	}
	
	@Override
	protected void executeNextBurst()
	{
		currentProcess.burst(1);
	}
	
	protected void getNextProcess()
	{
		if (!readyQueue.isEmpty())
		{
			currentProcess = readyQueue.poll();
		}
		else
		{
			bmgSimulationTimer.getTimer().forward(1);
		}
	}
}