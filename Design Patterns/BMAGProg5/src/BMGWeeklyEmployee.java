//Ben Girone CSC 352 11/29/17
//This file contains a class to define employees that are paid a weekly salary.

public class BMGWeeklyEmployee extends BMGBaseEmployee
{	
	//constructor
	public BMGWeeklyEmployee(String name, String title, double pay)
	{
		super(name, title, pay);
	}

	@Override
	public void display()
	{
		System.out.println(name + " : " + title + "; Weekly Rate: " + pay);
	}
	
	@Override
	public double acceptSalaryVisitor(BMGSalaryVisitor visitor)
	{
		return visitor.getTotalSalary(this);
	}

}
