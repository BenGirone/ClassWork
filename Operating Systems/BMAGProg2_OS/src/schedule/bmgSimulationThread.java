package schedule;

public class bmgSimulationThread extends Thread
{
	private bmgSimulator simulator;

	public bmgSimulationThread(bmgSimulator simulator)
	{
		this.simulator = simulator;
	}
	
	public void run()
	{
		simulator.start();
		simulator.printAnalysis();
		System.out.println("---------------------------------------");
		
		bmgMain.processesToBeUsed = bmgMain.chosenProcesses.getResetCopy();
	}
}
