//Ben Girone CSC 403 12/5/17
//This file implements the scheduler class.

package schedule;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class bmgSimulator
{
	//data members
	private bmgAlgorithm algorithm;
	private Queue<bmgProcess> processesReference;
	
	//constructor
	public bmgSimulator(bmgAlgorithm algorithm)
	{
		this.algorithm = algorithm;
	}
	
	/**
	 * Starts the algorithm contained within this object.
	 */
	public void start()
	{
		processesReference = new LinkedList<bmgProcess>(algorithm.getProcesses());
		algorithm.run();
	}
	
	/**
	 * Prints an analysis of each process after the algorithm completes.
	 */
	public void printAnalysis()
	{
		//ensure the algorithm is done
		if (algorithm.isDone())
		{
			//variable declaration
			double total_Tt = 0.0;
			double total_TsTt = 0.0;
			double n = 0.0;
			
			//iterate over the processes
			for (Iterator<bmgProcess> iterator = processesReference.iterator(); iterator.hasNext();)
			{
				//get the next process
				bmgProcess process = iterator.next();
				
				//output the process info
				System.out.println(process.getInfo());
				
				//calculate info for averages
				total_Tt += process.getTT();
				total_TsTt += process.getTT() / process.getServiceTime();
				n++;
			}
			
			//output the averages
			System.out.printf("Average Tt: %.2f\tAverage Tt/Ts: %.2f\n", total_Tt/n, total_TsTt/n);
		}
		else //algorithm is running or has not started
		{
			System.out.println("Algorithm is not complete or has not run.");
		}
	}
}
