//Ben Girone CSC 403 12/5/17
//This file defines a custom multi queue class. 

package schedule;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class bmgMultiQueue
{
	//multiQueue
	private ArrayList<Queue<bmgProcess>> RQ = new ArrayList<Queue<bmgProcess>>();
	private int currentRQ;
	private int I;
	
	public bmgMultiQueue(int I)
	{
		//ensure the amount of queues is valid
		if (I > 0)
			this.I = I;
		else
			this.I = 1;
		
		//fill the multiQueue
		for (int i = 0; i < I; i++)
		{
			Queue<bmgProcess> readyQueue = new LinkedList<bmgProcess>();
			RQ.add(readyQueue);
		}
		
		currentRQ = 0;
	}
	
	/**
	 * Adds a process <strong>p</strong> to RQ0.
	 * @param p The process to add to RQ0.
	 */
	public void add(bmgProcess p)
	{
		RQ.get(0).add(p);
	}
	
	/**
	 * Gets the next process from the current RQ.
	 * @return The next process from the current RQ.
	 */
	public bmgProcess poll()
	{
		return getCurrentRQ().poll();
	}
	
	/**
	 * Gets the next process from the current RQ without removing it.
	 * @return The next process from the current RQ.
	 */
	public bmgProcess peek()
	{
		return getCurrentRQ().peek();
	}
	
	/**
	 * Adds a previously polled process back to the multi-queue.
	 * @param p The process to reinsert.
	 */
	public void reinsert(bmgProcess p)
	{
		if (p.getTimesPreempted() >= (I - 1))
			RQ.get(I - 1).add(p);
		else
			RQ.get(p.getTimesPreempted()).add(p);
	}
	
	/**
	 * Checks if all the queues in the multi-queue are empty.
	 * @return True if all the queues in the multi-queue are empty, false otherwise.
	 */
	public boolean isEmpty()
	{
		for (int i = 0; i < I; i++)
		{
			if (!RQ.get(i).isEmpty())
				return false;
		}
		
		return true;
	}
	
	/**
	 * Gets the index of the current RQ in the multi-queue.
	 * @return The integer index of the current RQ.
	 */
	public int getCurrentRQi()
	{
		return currentRQ;
	}
	
	/**
	 * Gets the current RQ in the multi-queue.
	 * @return the current Queue in the multi-queue.
	 */
	private Queue<bmgProcess> getCurrentRQ()
	{
		int i = 0;
		Queue<bmgProcess> RQi = RQ.get(i);
		
		while (RQi.isEmpty() && i < I)
		{
			i++;
			RQi = RQ.get(i);
		}
		
		currentRQ = i;
		
		return RQi;
	}
}
