//Ben Girone CSC 403 12/5/17
//This file defines the process class. 

package schedule;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class bmgProcess
{
	//data members
	private String processName;
	private int arrivalTime;
	private int serviceTime;
	private int startTime = -1;
	private boolean finished = false;
	private int finishTime;
	private int remainingTime;
	private int timesPreempted = 0;
	private int TT;
	private int row;
	
	//constructor
	public bmgProcess(String processName, int arrivalTime, int serviceTime)
	{
		this.processName = processName;
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
		this.remainingTime = serviceTime;
	}
	
	/**
	 * Executes the process for the specified amount of time.
	 * @param burst The amount of time units to burst for.
	 */
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
				Thread.sleep(500);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			//move the simulation timer forward
			bmgSimulationTimer.getTimer().forward(1);
			
			//decrease the time remaining
			remainingTime--;
			
			if (bmgMainGUI.table != null)
			{
				String rowName = "";
				row = 0;
				while (rowName != processName)
				{
					rowName = (String) bmgMainGUI.getValueAt(bmgMainGUI.table, 0, row);
					row++;
				}
				
				bmgMainGUI.table.getColumns().get(bmgSimulationTimer.getTimer().getValue()).setCellFactory(e -> {
				    return new TableCell() {
				        @Override
				        protected void updateItem(Object item, boolean empty) {
				            super.updateItem(item, empty);
				            
				            // If index is two we set the background color explicitly.
		                    if (getIndex() == (row - 1)) {
		                        this.setStyle("-fx-background-color: green;");
		                    }
				            
				        }
				    };
				});
				
				bmgMainGUI.table.refresh();
			}
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
	
	/**
	 * Gets a reset copy of this process.
	 * @return An unstarted copy of this object.
	 */
	public bmgProcess getResetCopy()
	{
		return new bmgProcess(this.processName, this.arrivalTime, this.serviceTime);
	}
	
	/**
	 * Gets all info of the process if completed.
	 * @return A string representation of each variable in the object.
	 */
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

	/**
	 * Gets the process name
	 * @return A string representation of the process name
	 */
	public String getProcessName()
	{
		return processName;
	}
	
	/**
	 * Gets the process arrival time.
	 * @return An integer representation of the arrival time
	 */
	public int getArrivalTime()
	{
		return arrivalTime;
	}
	
	/**
	 * Gets the process service time.
	 * @return An integer representation of the service time
	 */
	public int getServiceTime()
	{
		return serviceTime;
	}
	
	/**
	 * Gets the process arrival time.
	 * @return An integer representation of the startTime time
	 */
	public int getStartTime()
	{
		return startTime;
	}

	/**
	 * Sets the process start time. 
	 * @param startTime 
	 */
	public void setStartTime(int startTime)
	{
		this.startTime = startTime;
	}

	/**
	 * Checks if the process has completed running.
	 * @return True if the process has completed running, false otherwise.
	 */
	public boolean isFinished()
	{
		return finished;
	}

	/**
	 * Gets the time at which the process exited.
	 * @return An integer representation of the exiting time.
	 */
	public int getFinishTime()
	{
		return finishTime;
	}

	/**
	 * Sets the time at which the process exited.
	 * @param finishTime
	 */
	public void setFinishTime(int finishTime)
	{
		this.finishTime = finishTime;
	}

	/**
	 * Gets the remaining time needed to complete execution.
	 * @return The remaining service time needed by the process.
	 */
	public int getRemainingTime()
	{
		return remainingTime;
	}
	
	/**
	 * Gets the number of times this process was preempted.
	 * @return An integer representing the number of preemptions.
	 */
	public int getTimesPreempted()
	{
		return timesPreempted;
	}
	
	/**
	 * Increases the number of times this process was preempted by 1.
	 */
	public void incrmentTimesPreempted()
	{
		timesPreempted++;
	}

	/**
	 * Gets the total time this process has been available to run.
	 * @return An integer representing the turn around time.
	 */
	public double getTT()
	{
		return TT;
	}
}
