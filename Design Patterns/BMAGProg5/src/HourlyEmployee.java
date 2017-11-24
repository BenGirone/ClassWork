class HourlyEmployee extends BaseEmployee
{
	private double hours;
	
	public HourlyEmployee(String name, String title, double pay, double hours)
	{
		super(name, title, pay);
		this.hours = hours;
	}

	public double getHours()
	{
		return hours;
	}

	public void setHours(double hours)
	{
		this.hours = hours;
	}

	@Override
	public double CalculateWeeklyPay()
	{
		return hours * pay;
	}

	@Override
	public void display()
	{
		System.out.println(name + " : " + title + "; Hours: " + hours + ", Rate: " + pay);
	}
	
}