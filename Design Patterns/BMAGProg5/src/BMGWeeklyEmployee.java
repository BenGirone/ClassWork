
public class BMGWeeklyEmployee extends BMGBaseEmployee
{	
	public BMGWeeklyEmployee(String name, String title, double pay)
	{
		super(name, title, pay);
	}
	
	@Override
	public double calculateWeeklyPay()
	{
		return pay;
	}

	@Override
	public void display()
	{
		System.out.println(name + " : " + title + "; Weekly Rate: " + pay);
	}
	
	@Override
	public double acceptSalaryVisitor(BMGEmployeeVisitor visitor)
	{
		return visitor.getTotalSalary(this);
	}

}
