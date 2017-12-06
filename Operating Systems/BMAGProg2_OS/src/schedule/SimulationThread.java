package schedule;

public class SimulationThread extends Thread
{
	private bmgSimulator simulator;

	public SimulationThread(bmgSimulator simulator)
	{
		this.simulator = simulator;
	}
	
	public void run()
	{
		simulator.start();
		simulator.printAnalysis();
		System.out.println("---------------------------------------");
	}
}
