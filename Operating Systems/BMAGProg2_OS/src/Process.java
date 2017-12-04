import java.util.concurrent.Semaphore;

public class Process extends Thread
{
	private String name;
	private int arrivalTime; //seconds
	private int serviceTime; //seconds
	private Clock clock; //milliseconds
	private long currentTime = 0; //milliseconds
	private long exitingTime = 0; //milliseconds
	private long TAT = 0; //seconds
	
	
	private long lastBurst;
	private Clock burstClock;
	
	
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
		burstClock = new Clock();
		
		do
		{
			try
			{
				sem.acquire();
				lastBurst = burstClock.getElapsedTime();
				sem.release();
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		} while((currentTime + lastBurst) < ((serviceTime * 1000) - 10));
	}
	
	public void stopProcess()
	{
		currentTime += lastBurst;
		
		try
		{
			sem.acquire();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public void resumeProcess()
	{
		if (currentTime + lastBurst >= (serviceTime * 1000))
			System.out.println("error");
		
		sem.release();
		burstClock = new Clock();
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
	
	public int getServiceTime()
	{
		return serviceTime;
	}
	
	public long getCurrentTime()
	{
		return currentTime;
	}
	
	public long getExitingTime()
	{
		return exitingTime;
	}

	public void setExitingTime(long exitingTime)
	{
		this.exitingTime = exitingTime;// - (arrivalTime * 1000);
	}

	public long getTAT()
	{
		return TAT;
	}

	public void setTAT(long tAT)
	{
		TAT = tAT;
	}
	
	public long getLastBurst()
	{
		return lastBurst;
	}
}