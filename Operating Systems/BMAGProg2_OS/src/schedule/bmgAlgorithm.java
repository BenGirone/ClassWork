//Ben Girone CSC 403 12/5/17
//This file defines an abstract scheduling algorithm class. 

package schedule;

import java.util.Queue;

public abstract class bmgAlgorithm
{
	//data members
	protected Queue<bmgProcess> processes;
	protected bmgProcess currentProcess = null;
	protected boolean isDone = false;
	
	//constructor
	public bmgAlgorithm(Queue<bmgProcess> processes)
	{
		this.processes = processes;
	}
	
	/**
	 * Get the the queue of processes.
	 * @return The current queue of processes that have not been sent to the ready queue.
	 */
	public Queue<bmgProcess> getProcesses()
	{
		return processes;
	}
	
	/**
	 * Runs the algorithm
	 */
	public abstract void run();
	
	/**
	 * Updates the queue of ready processes.
	 */
	protected abstract void updateQueues();
	
	/**
	 * Performs the next burst of execution for the process.
	 */
	protected abstract void executeNextBurst();
	
	/**
	 * Changes the current process if appropriate.
	 */
	protected abstract void getNextProcess();
	
	/**
	 *  Checks if the algorithm has completed.
	 * @return True if the algorithm is complete, false otherwise.
	 */
	public boolean isDone()
	{
		return isDone;
	}
}
