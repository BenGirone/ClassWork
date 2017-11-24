
public class WeeklyEmployee extends BaseEmployee
{	
	public WeeklyEmployee(String name, String title, double pay)
	{
		super(name, title, pay);
	}
	
	@Override
	public double CalculateWeeklyPay()
	{
		return pay;
	}

	@Override
	public void display()
	{
		System.out.println(name + " : " + title + "; Weekly Rate: " + pay);
	}

}
