package schedule;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class bmgSimulator
{
	private bmgAlgorithm algorithm;
	private Queue<bmgProcess> processesReference;
	
	public bmgSimulator(bmgAlgorithm algorithm)
	{
		this.algorithm = algorithm;
	}
	
	public void start()
	{
		processesReference = new LinkedList<bmgProcess>(algorithm.getProcesses());
		algorithm.run();
	}
	
	public void printAnalysis()
	{
		if (algorithm.isDone())
		{
			double total_Tt = 0.0;
			double total_TsTt = 0.0;
			double n = 0.0;
			
			for (Iterator<bmgProcess> iterator = processesReference.iterator(); iterator.hasNext();)
			{
				bmgProcess process = iterator.next();
				
				System.out.println(process.getInfo());
				
				total_Tt += process.getTT();
				total_TsTt += process.getTT() / process.getServiceTime();
				
				n++;
			}
			
			System.out.printf("Average Tt: %.2f\tAverage Tt/Ts: %.2f\n", total_Tt/n, total_TsTt/n);
		}
		else
		{
			System.out.println("Algorithm is not complete or has not run.");
		}
	}
}
