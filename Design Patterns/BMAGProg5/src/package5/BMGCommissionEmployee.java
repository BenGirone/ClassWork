package package5;
//Ben Girone CSC 352 11/29/17
//This file contains a class to define employees who are paid by commission.

public class BMGCommissionEmployee extends BMGBaseEmployee
{
	//data members
	private double sales;
	
	//constructor
	public BMGCommissionEmployee(String name, String title, double pay, double sales)
	{
		super(name, title, pay);
		this.sales = sales;
	}
	
	/** getSales
	 * Returns the amount of sales in USD in a week.
	 * @return sales
	 */
	public double getSales()
	{
		return sales;
	}

	/** setSales
	 * Sets the amount of sales in USD in a week.
	 * @param sales
	 */
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
	public double acceptSalaryVisitor(BMGSalaryVisitor visitor)
	{
		return visitor.getTotalSalary(this);
	}
}
