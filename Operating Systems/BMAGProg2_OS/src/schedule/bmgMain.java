//Ben Girone CSC 403 12/5/17
//This program implements 8 short term scheduling algorithms.

package schedule;

import java.util.LinkedList;

public class bmgMain
{

	public static void main(String[] args)
	{
		//processes assigned on blackboard
		bmgProcess A = new bmgProcess("A", 0, 3);
		bmgProcess B = new bmgProcess("B", 1, 6);
		bmgProcess C = new bmgProcess("C", 2, 4);
		bmgProcess D = new bmgProcess("D", 4, 8);
		bmgProcess E = new bmgProcess("E", 6, 4);
		bmgProcess F = new bmgProcess("F", 8, 2);

		//processes from the book
		bmgProcess A_book = new bmgProcess("A", 0, 3);
		bmgProcess B_book = new bmgProcess("B", 2, 6);
		bmgProcess C_book = new bmgProcess("C", 4, 4);
		bmgProcess D_book = new bmgProcess("D", 6, 5);
		bmgProcess E_book = new bmgProcess("E", 8, 2);
			
		//make a queue in order of arrival time
		bmgQueue processes = new bmgQueue(new LinkedList<bmgProcess>());
		processes.add(A);
		processes.add(B);
		processes.add(C);
		processes.add(D);
		processes.add(E);
		processes.add(F);
		
		//make a queue in order of arrival time
		bmgQueue processes_book = new bmgQueue(new LinkedList<bmgProcess>());
		processes_book.add(A_book);
		processes_book.add(B_book);
		processes_book.add(C_book);
		processes_book.add(D_book);
		processes_book.add(E_book);
		
		//make simulator objects
		bmgSimulator FCFS = new bmgSimulator(new bmgFCFS(processes.getResetCopy()));
		bmgSimulator RRq1 = new bmgSimulator(new bmgRRq1(processes.getResetCopy()));
		bmgSimulator RRq4 = new bmgSimulator(new bmgRRq4(processes.getResetCopy()));
		bmgSimulator SPN = new bmgSimulator(new bmgSPN(processes.getResetCopy()));
		bmgSimulator SRT = new bmgSimulator(new bmgSRT(processes.getResetCopy()));
		bmgSimulator HRRN = new bmgSimulator(new bmgHRRN(processes.getResetCopy()));
		bmgSimulator FBq1 = new bmgSimulator(new bmgFBq1(processes.getResetCopy()));
		bmgSimulator FBq2i = new bmgSimulator(new bmgFBq2i(processes.getResetCopy()));
		
		//make simulator objects
		bmgSimulator FCFS_book = new bmgSimulator(new bmgFCFS(processes_book.getResetCopy()));
		bmgSimulator RRq1_book = new bmgSimulator(new bmgRRq1(processes_book.getResetCopy()));
		bmgSimulator RRq4_book = new bmgSimulator(new bmgRRq4(processes_book.getResetCopy()));
		bmgSimulator SPN_book = new bmgSimulator(new bmgSPN(processes_book.getResetCopy()));
		bmgSimulator SRT_book = new bmgSimulator(new bmgSRT(processes_book.getResetCopy()));
		bmgSimulator HRRN_book = new bmgSimulator(new bmgHRRN(processes_book.getResetCopy()));
		bmgSimulator FBq1_book = new bmgSimulator(new bmgFBq1(processes_book.getResetCopy()));
		bmgSimulator FBq2i_book = new bmgSimulator(new bmgFBq2i(processes_book.getResetCopy()));
		
		//run each simulator and print the analysis
		FCFS.start();
		FCFS.printAnalysis();
		System.out.println("--------------------------------");
		RRq1.start();
		RRq1.printAnalysis();
		System.out.println("--------------------------------");
		RRq4.start();
		RRq4.printAnalysis();
		System.out.println("--------------------------------");
		SPN.start();
		SPN.printAnalysis();
		System.out.println("--------------------------------");
		SRT.start();
		SRT.printAnalysis();
		System.out.println("--------------------------------");
		HRRN.start();
		HRRN.printAnalysis();
		System.out.println("--------------------------------");
		FBq1.start();
		FBq1.printAnalysis();
		System.out.println("--------------------------------");
		FBq2i.start();
		FBq2i.printAnalysis();
		System.out.println("--------------------------------");
		
		System.out.println("\n\nRunning the processes in the book to ensure the algorithms are correct.\n\n");
		
		//run each simulator and print the analysis
		FCFS_book.start();
		FCFS_book.printAnalysis();
		System.out.println("--------------------------------");
		RRq1_book.start();
		RRq1_book.printAnalysis();
		System.out.println("--------------------------------");
		RRq4_book.start();
		RRq4_book.printAnalysis();
		System.out.println("--------------------------------");
		SPN_book.start();
		SPN_book.printAnalysis();
		System.out.println("--------------------------------");
		SRT_book.start();
		SRT_book.printAnalysis();
		System.out.println("--------------------------------");
		HRRN_book.start();
		HRRN_book.printAnalysis();
		System.out.println("--------------------------------");
		FBq1_book.start();
		FBq1_book.printAnalysis();
		System.out.println("--------------------------------");
		FBq2i_book.start();
		FBq2i_book.printAnalysis();
		System.out.println("--------------------------------");
	}

}