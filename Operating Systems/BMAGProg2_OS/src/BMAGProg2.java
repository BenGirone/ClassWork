import java.util.LinkedList;
import java.util.Queue;

public class BMAGProg2
{
	public static void main(String[] args)
	{
//		Process A = new Process("A", 0, 3);
//		Process B = new Process("B", 1, 6);
//		Process C = new Process("C", 2, 4);
//		Process D = new Process("D", 4, 8);
//		Process E = new Process("E", 6, 4);
//		Process F = new Process("F", 8, 2);

		Process A = new Process("A", 0, 3);
		Process B = new Process("B", 2, 6);
		Process C = new Process("C", 4, 4);
		Process D = new Process("D", 6, 5);
		Process E = new Process("E", 8, 2);
			
		Queue<Process> processes = new LinkedList<Process>();
		processes.add(A);
		processes.add(B);
		processes.add(C);
		processes.add(D);
		processes.add(E);
//		processes.add(F);
		
		Simulator simulatorFCFS = new Simulator(new LinkedList<Process>(processes));
		Simulator simulatorRRq1 = new Simulator(new LinkedList<Process>(processes));
		Simulator simulatorRRq4 = new Simulator(new LinkedList<Process>(processes));
		
		Simulator simulatorSPN = new Simulator(new LinkedList<Process>(processes));
		Simulator simulatorSRT = new Simulator(new LinkedList<Process>(processes));
		
		//simulatorFCFS.runFCFS(); //done
		//simulatorRRq1.runRR_q1();
		//simulatorRRq4.runRR_q4();
		
		//simulatorSPN.runSPN(); //done
		//simulatorSRT.runSRT(); //done
	}
}
