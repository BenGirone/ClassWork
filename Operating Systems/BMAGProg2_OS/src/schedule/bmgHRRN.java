package schedule;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class bmgHRRN extends bmgAlgorithm
{
	//queue of ready processes (response ratio prioritized)
	private PriorityQueue<bmgProcess> readyQueue = new PriorityQueue<bmgProcess>(new ProcessComparator_HRRN());

	//constructor
	public bmgHRRN(Queue<bmgProcess> processes)
	{
		super(processes);
	}

	@Override
	public void run()
	{
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
					//output process info
					currentProcess.printInfo();
					
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
		
		//output info of the final process
		currentProcess.printInfo();
		
		//reset the simulation clock
		bmgSimulationTimer.getTimer().reset();
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
				//print the info of the next process in the process queue
				processes.peek().printInfo();
				
				//move the next process in the process queue to the ready queue
				readyQueue.add(processes.poll()); 
			}
		}
	}
	
	@Override
	protected void executeNextBurst()
	{
		//execute the current process for 1 time unit
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

//a simple comparator to check if a process has a longer service time than another process
class ProcessComparator_HRRN implements Comparator<bmgProcess>
{
	@Override
	public int compare(bmgProcess p1, bmgProcess p2)
	{
		int w1 = bmgSimulationTimer.getTimer().getValue() - p1.getArrivalTime();
		int w2 = bmgSimulationTimer.getTimer().getValue() - p2.getArrivalTime();
		
		double R1 = (w1 + p1.getServiceTime())/p1.getServiceTime();
		double R2 = (w2 + p2.getServiceTime())/p2.getServiceTime();
		
		if (R1 > R2)
			return -1;
		
		if (R1 < R2)
			return 1;
		
		return 0;
	}
	
}
