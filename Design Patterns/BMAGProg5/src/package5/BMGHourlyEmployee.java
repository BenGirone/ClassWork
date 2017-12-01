package package5;
//Ben Girone CSC 352 11/29/17
//This file contains the a class to define hourly employees

class BMGHourlyEmployee extends BMGBaseEmployee
{
	//data members
	private double hours;
	
	//constructor
	public BMGHourlyEmployee(String name, String title, double pay, double hours)
	{
		super(name, title, pay);
		this.hours = hours;
	}

	/** getHours
	 * Returns the amount of hours the employee works per week.
	 * @return hours
	 */
	public double getHours()
	{
		return hours;
	}
	
	/** setHours
	 * Sets the amount of hours an employee works per week.
	 * @param hours
	 */
	public void setHours(double hours)
	{
		this.hours = hours;
	}
	
	@Override
	public void display()
	{
		System.out.println(name + " : " + title + "; Hours: " + hours + ", Rate: " + pay);
	}

	@Override
	public double acceptSalaryVisitor(BMGSalaryVisitor visitor)
	{
		return visitor.getTotalSalary(this);
	}
}