package schedule;

import java.util.LinkedList;
import java.util.Queue;

public class bmgRRq1 extends bmgAlgorithm
{
	private Queue<bmgProcess> readyQueue = new LinkedList<bmgProcess>();
	private bmgTimeQuantum q = new bmgTimeQuantum(1);
	
	public bmgRRq1(Queue<bmgProcess> processes)
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
					if (q.shouldInterrupt())
					{
						currentProcess.printInfo();
						getNextProcess();
						executeNextBurst();
					}
					else
					{
						executeNextBurst();
					}
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

	@Override
	protected void getNextProcess()
	{
		if (!readyQueue.isEmpty())
		{
			if (currentProcess != null && !currentProcess.isFinished())
			{
				readyQueue.add(currentProcess);
			}
			
			currentProcess = readyQueue.poll();
		}
		else
		{
			if (currentProcess == null || currentProcess.isFinished())
				bmgSimulationTimer.getTimer().forward(1);
		}
	}

}
