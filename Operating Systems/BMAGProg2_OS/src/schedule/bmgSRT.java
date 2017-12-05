package schedule;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class bmgSRT extends bmgAlgorithm
{
	//queue of ready processes (shorter time remaining prioritized)
	private PriorityQueue<bmgProcess> readyQueue = new PriorityQueue<bmgProcess>(new ProcessComparator_SRT());

	//constructor
	public bmgSRT(Queue<bmgProcess> processes)
	{
		super(processes);
	}

	@Override
	public void run()
	{
		System.out.print("SRT: ");
		
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
				//check if the current process is not null
				if (currentProcess != null)
				{
					//check if the next process in the process queue has less time until it is finished than the current process
					if (processes.peek().getServiceTime() < currentProcess.getRemainingTime())
					{
						//check if the current process is not finished
						if (!currentProcess.isFinished())
						{
							//reinsert the current process into the ready queue
							currentProcess.incrmentTimesPreempted();
							readyQueue.add(currentProcess);
							
							System.out.print("| ");
						}
						
						//set the current process to the newly arrived process
						currentProcess = processes.poll();
					}
					else
					{
						//move the next process in the process queue to the ready queue
						readyQueue.add(processes.poll()); 
					}
				}
				else
				{
					//move the next process in the process queue to the ready queue
					readyQueue.add(processes.poll()); 
				}
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

//a simple comparator to check if a process has a longer remaining time than another process
class ProcessComparator_SRT implements Comparator<bmgProcess>
{
	@Override
	public int compare(bmgProcess p1, bmgProcess p2)
	{
		if (p1.getRemainingTime() < p2.getRemainingTime())
			return -1;
		
		if (p1.getRemainingTime() > p2.getRemainingTime())
			return 1;
		
		return 0;
	}
}