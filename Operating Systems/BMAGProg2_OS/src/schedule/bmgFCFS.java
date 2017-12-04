package schedule;

import java.util.LinkedList;
import java.util.Queue;

public class bmgFCFS extends bmgAlgorithm
{
	Queue<bmgProcess> readyQueue = new LinkedList<bmgProcess>();

	public bmgFCFS(Queue<bmgProcess> processes)
	{
		super(processes);
	}

	@Override
	public void run()
	{
		while (!processes.isEmpty() || !readyQueue.isEmpty() || !currentProcess.isFinished())
		{
			if (!currentProcess.isFinished())
				executeNextBurst();
			else
				if (!readyQueue.isEmpty())
					currentProcess = readyQueue.poll();
				else
					timer.forward(1);
		}
	}

	@Override
	public void updateQueues()
	{
		if (!processes.isEmpty())
		{
			if (processes.peek().getArrivalTime() <= timer.getValue())
			{
				readyQueue.add(processes.poll());
			}
		}
	}

	@Override
	public void executeNextBurst()
	{
		currentProcess.burst(currentProcess.getServiceTime());
	}
}