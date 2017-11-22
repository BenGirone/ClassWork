
public class WeeklyEmployee implements IEmployee
{
	
	private String name;
	
	private String title;
	
	private double pay;
	
	public WeeklyEmployee(String name, String title, double pay)
	{
		this.name = name;
		this.title = title;
		this.pay = pay;
	}
	
	@Override
	public String GetName()
	{
		return name;
	}

	@Override
	public void SetName(String name)
	{
		this.name = name;
	}

	@Override
	public String GetTitle()
	{
		return title;
	}

	@Override
	public void SetTitle(String title)
	{
		this.title = title;
	}

	@Override
	public double GetPay()
	{
		return pay;
	}

	@Override
	public void SetPay(double pay)
	{
		this.pay = pay;
	}

	@Override
	public double CalculateWeeklyPay()
	{
		return pay;
	}

}
