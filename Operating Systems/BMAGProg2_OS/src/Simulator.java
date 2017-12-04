import java.lang.Thread.State;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Simulator 
{
	Queue<Process> processQueue;
	
	Clock clock;
	
	Simulator(Queue<Process> processQueue)
	{
		this.processQueue = processQueue;
	}
	
	private void getAnalysis()
	{
		System.out.println("\n");
		
		for (Process process : processQueue)
		{	
			System.out.println("Process: " + process.getProcessName() + 
							   "\n    Arrived at: " + 
							   process.getArrivalTime() +
							   "\n    Exited at: " +
							   process.getExitingTime()/1000 +
							   "\n    TAT: " +
							   process.getTAT()
							   );
			
			System.out.println("\n");
		}
	}

	public void runFCFS()
	{
		//variable declaration
		Queue<Process> readyQueue = new LinkedList<Process>();
		Queue<Process> processQueue = new LinkedList<Process>(this.processQueue);
		Process currentlyRunning = new Process("Scheduler_Startup", 0, 0);
		
		//simulator clock
		clock = new Clock();
		
		//simulator loop
		while (!processQueue.isEmpty() || !readyQueue.isEmpty() || currentlyRunning != null)
		{	
			//check if the process queue has more processes
			if (!processQueue.isEmpty())
			{
				//check if any new processes have arrived
				if (processQueue.peek().getArrivalTime() <= clock.getElapsedTime()/1000)
				{
					//add newly arrived process to the ready queue
					readyQueue.add(processQueue.poll());
				}
			}
			
			//check if the current process is done running
			if (!currentlyRunning.isAlive())
			{
				//output the duration of process execution
				System.out.println("Process: " + currentlyRunning.getProcessName() + " ran for " + (double)currentlyRunning.getLastBurst()/1000.0 + " seconds.");
				
				//set the simulator time at which the process exited
				currentlyRunning.setExitingTime(clock.getElapsedTime());
				
				//inform the simulator that no process is running
				currentlyRunning = null;
				
				//check if the ready queue has any processes
				if (!readyQueue.isEmpty())
				{
					//set the currently running process to the next process in the queue
					currentlyRunning = readyQueue.poll();
					
					//start the next process
					currentlyRunning.start();
				}
			}
		}
		
		//output an analysis of the simulation
		getAnalysis();
	}

	public void runRR_q1()
	{
		//variable declaration
		Queue<Process> readyQueue = new LinkedList<Process>();
		Queue<Process> processQueue = new LinkedList<Process>(this.processQueue);
		Process currentlyRunning = new Process("Scheduler_Startup", 0, 0);
		
		//simulator clock
		clock = new Clock();
		
		//time quantum
		Clock q = new Clock();
		
		//simulator loop
		while (!processQueue.isEmpty() || !readyQueue.isEmpty() || currentlyRunning != null)
		{
			//check if the process queue has more processes
			if (!processQueue.isEmpty())
			{
				//check if any new processes have arrived
				if (processQueue.peek().getArrivalTime() <= clock.getElapsedTime()/1000)
				{
					//add newly arrived process to the ready queue
					readyQueue.add(processQueue.poll());
				}
			}
			
			//check the time quantum or if the current process is done running
			if (q.getElapsedTime() >= 1000 || !currentlyRunning.isAlive())
			{
				//restart the time quantum
				q = new Clock();
				
				//check if the ready queue has any processes
				if (!readyQueue.isEmpty())
				{
					//check if the current process is still running
					if (currentlyRunning.isAlive())
					{
						//pause the current process
						currentlyRunning.stopProcess();
						
						//replace the current process in the ready queue
						readyQueue.add(currentlyRunning);
					}
					else //current process is not running
					{
						//set the simulator time at which the process exited
						currentlyRunning.setExitingTime(clock.getElapsedTime());
					}
					
					//output the duration of the last process burst
					System.out.println("Process: " + currentlyRunning.getProcessName() + " ran for " + (double)currentlyRunning.getLastBurst()/1000.0 + " seconds.");
					
					//set the currently running process to the next process in the queue
					currentlyRunning = readyQueue.poll();
					
					//check if the current process is a new process or a paused process
					if (currentlyRunning.getState() == State.NEW)
					{
						//start process
						currentlyRunning.start();
					}
					else
					{
						//resume process
						currentlyRunning.resumeProcess();
					}
				}
				else //ready queue is empty
				{
					//check if the current process is done running
					if (!currentlyRunning.isAlive())
					{
						//output the duration of the last process burst
						System.out.println("Process: " + currentlyRunning.getProcessName() + " ran for " + (double)currentlyRunning.getLastBurst()/1000.0 + " seconds.");
						
						//inform the simulator that no process is running
						currentlyRunning = null;
					}
				}
			}
		}
		
		//output an analysis of the simulation
		getAnalysis();
	}
	
	public void runRR_q4()
	{
		//variable declaration
		Queue<Process> readyQueue = new LinkedList<Process>();
		Queue<Process> processQueue = new LinkedList<Process>(this.processQueue);
		Process currentlyRunning = new Process("Scheduler_Startup", 0, 0);
		
		//simulator clock
		clock = new Clock();
		
		//time quantum
		Clock q = new Clock();
		
		//simulator loop
		while (!processQueue.isEmpty() || !readyQueue.isEmpty() || currentlyRunning != null)
		{
			//check if the process queue has more processes
			if (!processQueue.isEmpty())
			{
				//check if any new processes have arrived
				if (processQueue.peek().getArrivalTime() <= clock.getElapsedTime()/1000)
				{
					//add newly arrived process to the ready queue
					readyQueue.add(processQueue.poll());
				}
			}
			
			//check the time quantum or if the current process is done running
			if (q.getElapsedTime() >= 4000 || !currentlyRunning.isAlive())
			{
				//restart the time quantum
				q = new Clock();
				
				//check if the ready queue has any processes
				if (!readyQueue.isEmpty())
				{
					//check if the current process is still running
					if (currentlyRunning.isAlive())
					{
						//pause the current process
						currentlyRunning.stopProcess();
						
						//replace the current process in the ready queue
						readyQueue.add(currentlyRunning);
					}
					else //current process is not running
					{
						//set the simulator time at which the process exited
						currentlyRunning.setExitingTime(clock.getElapsedTime());
					}
					
					//output the duration of the last process burst
					System.out.println("Process: " + currentlyRunning.getProcessName() + " ran for " + (double)currentlyRunning.getLastBurst()/1000.0 + " seconds.");
					
					//set the currently running process to the next process in the queue
					currentlyRunning = readyQueue.poll();
					
					//check if the current process is a new process or a paused process
					if (currentlyRunning.getState() == State.NEW)
					{
						//start process
						currentlyRunning.start();
					}
					else
					{
						//resume process
						currentlyRunning.resumeProcess();
					}
				}
				else //ready queue is empty
				{
					//check if the current process is done running
					if (!currentlyRunning.isAlive())
					{
						//output the duration of the last process burst
						System.out.println("Process: " + currentlyRunning.getProcessName() + " ran for " + (double)currentlyRunning.getLastBurst()/1000.0 + " seconds.");
						
						//inform the simulator that no process is running
						currentlyRunning = null;
					}
				}
			}
		}
		
		//output an analysis of the simulation
		getAnalysis();
	}
	
	public void runFeedback_q1()
	{
		
	}

	public void runSRT()
	{
		//variable declaration
		PriorityQueue<Process> readyQueue = new PriorityQueue<Process>(new ProcessComparator_SRT());
		Queue<Process> processQueue = new LinkedList<Process>(this.processQueue);
		Process currentlyRunning = new Process("Scheduler_Startup", 0, 0);
		
		//simulator clock
		clock = new Clock();
		
		//simulator loop
		while (!processQueue.isEmpty() || !readyQueue.isEmpty() || currentlyRunning != null)
		{	
			//check if the process queue has more processes
			if (!processQueue.isEmpty())
			{
				//check if any new processes have arrived
				if (processQueue.peek().getArrivalTime() <= clock.getElapsedTime()/1000)
				{	
					//check if the ready process, with shortest time remaining, has a shorter service time remaining than the current running process 
					if ((processQueue.peek().getServiceTime() - processQueue.peek().getCurrentTime()/1000) < (currentlyRunning.getServiceTime() - currentlyRunning.getCurrentTime()/1000))
					{
						//replace the current process in the ready queue
						readyQueue.add(currentlyRunning);
						
						//pause the current process
						currentlyRunning.stopProcess();
						
						//output the duration of the last process burst
						System.out.println("Process: " + currentlyRunning.getProcessName() + " ran for " + (double)currentlyRunning.getLastBurst()/1000.0 + " seconds.");
						
						//set the currently running process to the next element in the ready queue
						currentlyRunning = processQueue.poll();
						
						currentlyRunning.start();
					}
					else
					{
						//add newly arrived process to the ready queue
						readyQueue.add(processQueue.poll());
					}
				}
			}
			
			//check if the current process is done execution
			if (!currentlyRunning.isAlive())
			{
				//output the duration of the last process burst
				System.out.println("Process: " + currentlyRunning.getProcessName() + " ran for " + (double)currentlyRunning.getLastBurst()/1000.0 + " seconds.");
				
				//set the simulator time at which the process exited
				currentlyRunning.setExitingTime(clock.getElapsedTime());
				
				//inform the simulator that no process is running
				currentlyRunning = null;
				
				//check if the ready queue contains any processes
				if (!readyQueue.isEmpty())
				{
					//set the currently running process to the next process in the ready queue
					currentlyRunning = readyQueue.poll();
					
					//check if the current process is new
					if (currentlyRunning.getState() == State.NEW)
					{
						//start the current process
						currentlyRunning.start();
					}
					else //process is paused
					{
						//resume the process
						currentlyRunning.resumeProcess();
					}
				}
			}
		}
		
		//output an analysis of the simulation
		getAnalysis();
	}
	
	public void runSPN()
	{
		//variable declaration
		PriorityQueue<Process> readyQueue = new PriorityQueue<Process>(new ProcessComparator_SPN());
		Queue<Process> processQueue = new LinkedList<Process>(this.processQueue);
		Process currentlyRunning = new Process("Scheduler_Startup", 0, 0);
		
		//simulator clock
		clock = new Clock();
		
		//simulator loop
		while (!processQueue.isEmpty() || !readyQueue.isEmpty() || currentlyRunning != null)
		{	
			//check if the process queue has more processes
			if (!processQueue.isEmpty())
			{
				//check if any new processes have arrived
				if (processQueue.peek().getArrivalTime() <= clock.getElapsedTime()/1000)
				{
					//add newly arrived process to the ready queue
					readyQueue.add(processQueue.poll());
				}
			}
			
			//check if the current process is done execution
			if (!currentlyRunning.isAlive())
			{
				//output the duration of the last process burst
				System.out.println("Process: " + currentlyRunning.getProcessName() + " ran for " + (double)currentlyRunning.getLastBurst()/1000.0 + " seconds.");
				
				//set the simulator time at which the process exited
				currentlyRunning.setExitingTime(clock.getElapsedTime());
				
				//inform the simulator that no process is running
				currentlyRunning = null;
				
				//check if the ready queue has any processes
				if (!readyQueue.isEmpty())
				{
					//set the current running process to the next process in the ready queue
					currentlyRunning = readyQueue.poll();
					
					//start the current process
					currentlyRunning.start();
				}
			}
		}
		
		//output an analysis of the simulation
		getAnalysis();
	}
	
	public void runHRRN_q2()
	{
		
	}
	
	public void runFeedback_q2()
	{
		
	}
}

class ProcessComparator_SPN implements Comparator<Process>
{

	@Override
	public int compare(Process p1, Process p2)
	{
		if (p1.getServiceTime() < p2.getServiceTime())
			return -1;
		
		if (p1.getServiceTime() > p2.getServiceTime())
			return 1;
		
		return 0;
	}
	
}

class ProcessComparator_SRT implements Comparator<Process>
{

	@Override
	public int compare(Process p1, Process p2)
	{
		if ((p1.getServiceTime() - p1.getCurrentTime()) < (p2.getServiceTime() - p2.getCurrentTime()))
			return -1;
		
		if ((p1.getServiceTime() - p1.getCurrentTime()) > (p2.getServiceTime() - p2.getCurrentTime()))
			return 1;
		
		return 0;
	}
	
}