//Ben Girone CSC 403 12/5/17
//This file defines the FCFS algorithm class. 

package schedule;

import java.util.LinkedList;
import java.util.Queue;

public class bmgFCFS extends bmgAlgorithm
{
	//queue of ready processes
	private Queue<bmgProcess> readyQueue = new LinkedList<bmgProcess>();

	//constructor
	public bmgFCFS(Queue<bmgProcess> processes)
	{
		super(processes);
	}

	@Override
	public void run()
	{
		System.out.print("FCFS: ");
		
		//scheduling loop
		while (!processes.isEmpty() || !readyQueue.isEmpty() || !currentProcess.isFinished())
		{
			//check if new processes have arrived
			updateQueues();
			
			//check if the current process is not null
			if (currentProcess != null)
			{
				//check if the current process is still running
				if (!currentProcess.isFinished())
				{
					//continue to execute the process
					executeNextBurst();
				}
				else //current process is finished
				{
					//change the current process to the next process in the ready queue
					getNextProcess();
				}
			}
			else //current process is null
			{
				//change the current process to the next process in the ready queue
				getNextProcess();
			}
		}
		
		//reset the simulation clock
		bmgSimulationTimer.getTimer().reset();
		isDone = true;
		System.out.println();
	}

	@Override
	protected void updateQueues()
	{
		//check if there are any more processes in the process queue
		if (!processes.isEmpty())
		{
			//check if the next process in the process queue has reached its arrival time
			if (processes.peek().getArrivalTime() <= bmgSimulationTimer.getTimer().getValue())
			{
				//move the next process in the process queue to the ready queue
				readyQueue.add(processes.poll());
			}
		}
	}
	
	@Override
	protected void executeNextBurst()
	{
		//execute the current process for 1 time unit
		System.out.print(currentProcess.getProcessName() + " ");
		currentProcess.burst(1);
	}
	
	@Override
	protected void getNextProcess()
	{
		//check if the ready queue has any processes
		if (!readyQueue.isEmpty())
		{
			//set the current process to the next process in the ready queue
			currentProcess = readyQueue.poll();
		}
		else //ready queue is empty
		{
			//move the simulation timer forward
			bmgSimulationTimer.getTimer().forward(1);
		}
	}
}