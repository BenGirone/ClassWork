package schedule;

public class bmgProcess
{
	//variable declaration
	private String processName;
	private int arrivalTime;
	private int serviceTime;
	private int startTime = -1;
	private boolean finished = false;
	private int finishTime;
	private int remainingTime;
	private int timesPreempted = 0;
	private int TT;
	
	//constructor
	public bmgProcess(String processName, int arrivalTime, int serviceTime)
	{
		this.processName = processName;
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
		this.remainingTime = serviceTime;
	}
	
	
	public void burst(int burst)
	{
		//check if this is the first burst
		if (serviceTime == remainingTime)
		{
			startTime = bmgSimulationTimer.getTimer().getValue();
		}
		
		//perform each burst
		for (int i = 0; i < burst; i++)
		{
			//sleep 1/5th second (this defines how long 1 time unit lasts)
			try
			{
				Thread.sleep(200);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			//move the simulation timer forward
			bmgSimulationTimer.getTimer().forward(1);
			
			//decrease the time remaining
			remainingTime--;
			
			//check if the process is done executing
			if (remainingTime <= 0)
			{
				//update process values and the simulation timer
				finishTime = bmgSimulationTimer.getTimer().getValue();
				TT = finishTime - arrivalTime;
				finished = true;
				break;
			}
		}
	}
	
	public bmgProcess getResetCopy()
	{
		return new bmgProcess(this.processName, this.arrivalTime, this.serviceTime);
	}
	
	public String getInfo()
	{
		String info = "";
		
		if (finished)
			info = String.format("Process: %s:\n\tArrival Time: %d\n\tService Time: %d\n\tStart Time: %d\n\tExiting Time: %d\n\tTt: %d\n\tTt/Ts: %.2f\n\tTimes Preempted: %d\n", 
					processName, 
					arrivalTime, 
					serviceTime, 
					startTime, 
					finishTime, 
					TT,
					(double)TT/(double)serviceTime,
					timesPreempted);
		
		return info;
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

	public double getTT()
	{
		return TT;
	}
}
