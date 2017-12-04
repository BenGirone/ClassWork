
public class Clock 
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