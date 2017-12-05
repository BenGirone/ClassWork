package schedule;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class bmgFBq1 extends bmgAlgorithm
{
	//queue of ready processes (time executing prioritized)
	private PriorityQueue<bmgProcess> readyQueue = new PriorityQueue<bmgProcess>(new ProcessComparator_FB());
	
	private bmgTimeQuantum q = new bmgTimeQuantum(1);
	
	//constructor
	public bmgFBq1(Queue<bmgProcess> processes)
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
					//check if the clock should interrupt execution
					if (q.shouldInterrupt())
					{
						//output process info
						currentProcess.printInfo();
						
						//change the current process to the next process in the ready queue
						getNextProcess();
						
						//continue to execute the process
						executeNextBurst();
					}
					else //no interrupt needed
					{
						//continue to execute the process
						executeNextBurst();
					}
				}
				else //current process is finished
				{
					//output process info
					currentProcess.printInfo();
					
					//reset the interrupt clock if necessary
					q.reset();
					
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
		//output process info
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
			//check if the next process in the process queue has arrived
			if (processes.peek().getArrivalTime() <= bmgSimulationTimer.getTimer().getValue())
			{
				//output the info of the next process in the process queue
				//processes.peek().printInfo();
				
				//move the next process in the process queue to the ready queue
				readyQueue.add(processes.poll()); 
			}
		}
		
		if (currentProcess != null) {System.out.print(currentProcess.getProcessName() + " ");};
		for (Iterator<bmgProcess> iterator = readyQueue.iterator(); iterator.hasNext();)
		{
			bmgProcess process =iterator.next();
			
			System.out.print(process.getProcessName() + " ");
		}
		System.out.println();
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
			//check if the currentProcess should be reinserted into the ready queue
			if (currentProcess != null && !currentProcess.isFinished())
			{
				if(currentProcess.getTimesPreempted() >= readyQueue.peek().getTimesPreempted())
				{
					//reinsert the the current process into the ready queue
					currentProcess.incrmentTimesPreempted();
					readyQueue.add(currentProcess);
					currentProcess = readyQueue.poll();
				}
			}
			else
			{
				//set the current process to the next process in the ready queue
				currentProcess = readyQueue.poll();
			}
		}
		else //ready queue is empty
		{
			//move the timer forward if no process is running
			if (currentProcess == null || currentProcess.isFinished())
				bmgSimulationTimer.getTimer().forward(1);
		}
	}
}

//a simple comparator to check if a process has a been preempted more than another process
class ProcessComparator_FB implements Comparator<bmgProcess>
{
	@Override
	public int compare(bmgProcess p1, bmgProcess p2)
	{
		if (p1.getTimesPreempted() >= 3 && p2.getTimesPreempted() >= 3)
			return 0;
		
		if (p1.getTimesPreempted() < p2.getTimesPreempted())
			return -1;
		
		if (p1.getTimesPreempted() > p2.getTimesPreempted())
			return 1;
		
		return 0;
	}
}
