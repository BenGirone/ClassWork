import java.util.concurrent.Semaphore;
import java.util.PriorityQueue;

class Clock 
{
	public long startTime;
	
	public Clock()
	{
		this.startTime = System.currentTimeMillis();
	}
	
	public long getElapsedTime()
	{
		return System.currentTimeMillis() - startTime;
	}
}

class Process extends Thread
{
	private String name;
	private int arrivalTime; //seconds
	private int serviceTime; //seconds
	private Clock clock; //milliseconds
	private long currentTime = 0; //milliseconds
	private long exitingTime; //seconds
	private long TAT; //seconds
	
	private Semaphore sem = new Semaphore(1);
	
	public Process(String name, int arrivalTime, int serviceTime)
	{
		this.name = name;
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
	}
	
	public void run()
	{
		clock = new Clock();
		
		do
		{
			currentTime = clock.getElapsedTime();
		} while(currentTime < (serviceTime * 1000));
	}

	public String getProcessName()
	{
		return name;
	}

	public void setProcessName(String name)
	{
		this.name = name;
	}

	public int getArrivalTime()
	{
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime)
	{
		this.arrivalTime = arrivalTime;
	}

	public int getServiceTime()
	{
		return serviceTime;
	}

	public void setServiceTime(int serviceTime)
	{
		this.serviceTime = serviceTime;
	}

	public long getCurrentTime()
	{
		return currentTime;
	}

	public void setCurrentTime(long currentTime)
	{
		this.currentTime = currentTime;
	}

	public long getExitingTime()
	{
		return exitingTime;
	}

	public void setExitingTime(long exitingTime)
	{
		this.exitingTime = exitingTime;
	}

	public long getTAT()
	{
		return TAT;
	}

	public void setTAT(long tAT)
	{
		TAT = tAT;
	}
}

class Simulator 
{
	PriorityQueue<Process> processQueue;
	Process[] processes;
	
	Simulator(Process[] processes)
	{
		this.processes = processes;
	}

	public void runFCFS()
	{
		
	}
	
	public void runRR_q4()
	{
		
	}
	
	public void runFeedback_q1()
	{
		
	}
	
	public void runRR_q1()
	{
		
	}
	
	public void runSRT()
	{
		
	}
	
	public void runSPN()
	{
		
	}
	
	public void runHRRN_q2()
	{
		
	}
	
	public void runFeedback_q2()
	{
		
	}
}

public class BMAGProg2
{

	public static void main(String[] args)
	{
		Process A = new Process("A", 0, 3);
		Process B = new Process("B", 1, 6);
		Process C = new Process("C", 2, 4);
		Process D = new Process("D", 4, 8);
		Process E = new Process("E", 6, 4);
		Process F = new Process("F", 8, 2);

		Process[] processes = {A,B,C,D,E,F};
		Simulator simulator = new Simulator(processes);
		
		simulator.runFCFS();
	}

}
