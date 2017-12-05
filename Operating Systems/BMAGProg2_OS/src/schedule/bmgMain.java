package schedule;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class bmgMain
{

	public static void main(String[] args)
	{
//		bmgProcess A = new bmgProcess("A", 0, 3);
//		bmgProcess B = new bmgProcess("B", 1, 6);
//		bmgProcess C = new bmgProcess("C", 2, 4);
//		bmgProcess D = new bmgProcess("D", 4, 8);
//		bmgProcess E = new bmgProcess("E", 6, 4);
//		bmgProcess F = new bmgProcess("F", 8, 2);

		bmgProcess A = new bmgProcess("A", 0, 3);
		bmgProcess B = new bmgProcess("B", 2, 6);
		bmgProcess C = new bmgProcess("C", 4, 4);
		bmgProcess D = new bmgProcess("D", 6, 5);
		bmgProcess E = new bmgProcess("E", 8, 2);
			
		bmgQueue processes = new bmgQueue(new LinkedList<bmgProcess>());
		processes.add(A);
		processes.add(B);
		processes.add(C);
		processes.add(D);
		processes.add(E);
//		processes.add(F);
		
		bmgSimulator FCFS = new bmgSimulator(new bmgFCFS(processes.getResetCopy()));
		bmgSimulator RRq1 = new bmgSimulator(new bmgRRq1(processes.getResetCopy()));
		bmgSimulator RRq4 = new bmgSimulator(new bmgRRq4(processes.getResetCopy()));
		bmgSimulator SPN = new bmgSimulator(new bmgSPN(processes.getResetCopy()));
		bmgSimulator SRT = new bmgSimulator(new bmgSRT(processes.getResetCopy()));
		bmgSimulator HRRN = new bmgSimulator(new bmgHRRN(processes.getResetCopy()));
		bmgSimulator FBq1 = new bmgSimulator(new bmgFBq1(processes.getResetCopy()));
		bmgSimulator FBq2i = new bmgSimulator(new bmgFBq2i(processes.getResetCopy()));
		
//		FCFS.start();
//		System.out.println("--------------------------------");
//		RRq1.start();
//		System.out.println("--------------------------------");
//		RRq4.start();
//		System.out.println("--------------------------------");
//		SPN.start();
//		System.out.println("--------------------------------");
//		SRT.start();
//		System.out.println("--------------------------------");
//		HRRN.start();
//		System.out.println("--------------------------------");
		FBq1.start();
	}

}
