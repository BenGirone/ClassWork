package schedule;

public class bmgProcess
{
	private String processName;
	private int arrivalTime;
	private int serviceTime;
	private int startTime = -1;
	private int priority;
	private boolean finished = false;
	private int finishTime;
	private int remainingTime;
	private int timesPreempted = 0;
	
	public bmgProcess(String processName, int arrivalTime, int serviceTime)
	{
		this.processName = processName;
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
		this.remainingTime = serviceTime;
	}
	
	public void burst(int burst)
	{
		if (serviceTime == remainingTime)
		{
			startTime = bmgSimulationTimer.getTimer().getValue();
		}
		
		for (int i = 0; i < burst; i++)
		{
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			bmgSimulationTimer.getTimer().forward(1);
			
			remainingTime--;
			
			if (remainingTime <= 0)
			{
				finishTime = bmgSimulationTimer.getTimer().getValue();
				finished = true;
				break;
			}
		}
	}
	
	public bmgProcess getResetCopy()
	{
		return new bmgProcess(this.processName, this.arrivalTime, this.serviceTime);
	}
	
	public void printInfo()
	{
		System.out.print("Process: " + processName);
		
		if (arrivalTime <= bmgSimulationTimer.getTimer().getValue())
		{
			System.out.print(" arrived at time: " + arrivalTime);
			
			if (startTime != -1)
			{
				System.out.print(" started at time: " + startTime);
				System.out.print(" and has run for " + (serviceTime - remainingTime) + " time units.");
				if (finished)
				{
					System.out.println(" The process exited at: " + finishTime);
				}
				else
				{
					System.out.println();
				}
			}
			else
			{
				System.out.println(" and is ready to start.");
			}
		}
		else
		{
			System.out.println(" has not arrived.");
		}
	}

	public String getProcessName()
	{
		return processName;
	}
	
	public int getArrivalTime()
	{
		return arrivalTime;
	}
	
	public int getServiceTime()
	{
		return serviceTime;
	}
	
	public int getStartTime()
	{
		return startTime;
	}

	public void setStartTime(int startTime)
	{
		this.startTime = startTime;
	}

	public int getPriority()
	{
		return priority;
	}

	public void setPriority(int priority)
	{
		this.priority = priority;
	}

	public boolean isFinished()
	{
		return finished;
	}

	public int getFinishTime()
	{
		return finishTime;
	}

	public void setFinishTime(int finishTime)
	{
		this.finishTime = finishTime;
	}

	public int getRemainingTime()
	{
		return remainingTime;
	}

	public void setRemainingTime(int remainingTime)
	{
		this.remainingTime = remainingTime;
	}
	
	public int getTimesPreempted()
	{
		return timesPreempted;
	}
	
	public void incrmentTimesPreempted()
	{
		timesPreempted++;
	}
}
