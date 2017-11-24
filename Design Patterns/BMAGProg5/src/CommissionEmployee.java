
public class CommissionEmployee extends BaseEmployee
{
	private double sales;
	
	public CommissionEmployee(String name, String title, double pay, double sales)
	{
		super(name, title, pay);
		this.sales = sales;
	}
	
	public double getSales()
	{
		return sales;
	}

	public void setSales(double sales)
	{
		this.sales = sales;
	}

	@Override
	public double CalculateWeeklyPay()
	{
		return (pay/100) * sales;
	}

	@Override
	public void display()
	{
		System.out.println(name + " : " + title + "; Sales: " + sales + ", Commission: " + pay);
	}
}
