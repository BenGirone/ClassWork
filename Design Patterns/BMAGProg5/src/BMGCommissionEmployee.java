
public class BMGCommissionEmployee extends BMGBaseEmployee
{
	private double sales;
	
	public BMGCommissionEmployee(String name, String title, double pay, double sales)
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
	public void display()
	{
		System.out.println(name + " : " + title + "; Sales: " + sales + ", Commission: " + pay);
	}
	
	@Override
	public double acceptSalaryVisitor(BMGEmployeeVisitor visitor)
	{
		return visitor.getTotalSalary(this);
	}
}
