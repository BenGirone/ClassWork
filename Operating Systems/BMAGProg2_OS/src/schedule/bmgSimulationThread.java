//Ben Girone CSC 403 12/5/17
//This file defines a thread class to execute a scheduling algorithm outside the GUI. 

package schedule;

public class bmgSimulationThread extends Thread
{
	//data members
	private bmgSimulator simulator;

	//constructor
	public bmgSimulationThread(bmgSimulator simulator)
	{
		this.simulator = simulator;
	}
	
	@Override
	public void run()
	{
		//run the simulator and print an analysis after it is complete
		simulator.start();
		simulator.printAnalysis();
		System.out.println("---------------------------------------");
		
		//reset the processes to be used
		bmgMainGUI.processesToBeUsed = bmgMainGUI.chosenProcesses.getResetCopy();
	}
}
