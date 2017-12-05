package schedule;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class bmgMultiQueue
{
	//multiQueue
	ArrayList<Queue<bmgProcess>> RQ = new ArrayList<Queue<bmgProcess>>();
	int currentRQ;
	int I;
	
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
	
	public void add(bmgProcess p)
	{
		RQ.get(0).add(p);
	}
	
	public bmgProcess poll()
	{
		return getCurrentRQ().poll();
	}
	
	public bmgProcess peek()
	{
		return getCurrentRQ().peek();
	}
	
	public void reinsert(bmgProcess p)
	{
		if (p.getTimesPreempted() >= (I - 1))
			RQ.get(I - 1).add(p);
		else
			RQ.get(p.getTimesPreempted()).add(p);
	}
	
	public boolean isEmpty()
	{
		for (int i = 0; i < I; i++)
		{
			if (!RQ.get(i).isEmpty())
				return false;
		}
		
		return true;
	}
	
	public int getCurrentRQi()
	{
		return currentRQ;
	}
	
	private Queue<bmgProcess> getCurrentRQ()
	{
		int i = 0;
		Queue<bmgProcess> RQi = RQ.get(i);
		
		while (RQi.isEmpty())
		{
			i++;
			RQi = RQ.get(i);
		}
		
		currentRQ = i;
		
		return RQi;
	}
}
