
public abstract class BaseEmployee implements IEmployee
{
	protected String name;
	
	protected String title;
	
	protected double pay;
	
	public BaseEmployee(String name, String title, double pay)
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
	public abstract double CalculateWeeklyPay();

	@Override
	public abstract void display();

	@Override
	public void displayCustomIterator()
	{
		display();
	}

}
