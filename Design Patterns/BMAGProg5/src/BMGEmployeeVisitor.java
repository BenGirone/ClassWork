import java.util.Iterator;

public class BMGEmployeeVisitor
{
	public double getTotalSalary(BMGHourlyEmployee employee)
	{
		return employee.getHours() * employee.getPay();
	}
	
	public double getTotalSalary(BMGCommissionEmployee employee)
	{
		return employee.getSales() * employee.getPay()/100;
	}
	
	public double getTotalSalary(BMGWeeklyEmployee employee)
	{
		return employee.getPay();
	}
	
	public double getSupervisorsSalary(BMGSupervisorEmployee employee)
	{
		double salary = employee.acceptSalaryVisitor(this);
		
		for (Iterator<BMGIEmployee> iterator = employee.getSubordinates().iterator(); iterator.hasNext();)
		{
			BMGIEmployee nextEmployee = iterator.next();
			if (nextEmployee instanceof BMGSupervisorEmployee)
				salary += ((BMGSupervisorEmployee) nextEmployee).acceptSupervisorVisitor(this);
		}
		
		System.out.println("Total weekly pay of all the supervisors under " + employee.getName() + ": $" + salary);
		return salary;
	}

	public void getNonSupervisorsSalaries(BMGSupervisorEmployee employee)
	{
		double nextSalary;
		BMGIEmployee nextEmployee;
		
		for (Iterator<BMGIEmployee> iterator = employee.getSubordinates().iterator(); iterator.hasNext();)
		{
			nextEmployee = iterator.next();
			if (!(nextEmployee instanceof BMGSupervisorEmployee))
			{
				nextSalary = nextEmployee.acceptSalaryVisitor(this);
				System.out.println("    " + nextEmployee.getName() + " : $" + nextSalary);
			}
			else
			{
				((BMGSupervisorEmployee) nextEmployee).acceptNonSupervisorVisitor(this);
			}
		}
	}

	public void getSalesResults(BMGSupervisorEmployee employee)
	{
		double nextSalary;
		double nextSales;
		BMGIEmployee nextEmployee;
		
		for (Iterator<BMGIEmployee> iterator = employee.getSubordinates().iterator(); iterator.hasNext();)
		{
			nextEmployee = iterator.next();
			if (nextEmployee instanceof BMGCommissionEmployee)
			{
				nextSales = ((BMGCommissionEmployee) nextEmployee).getSales();
				nextSalary = nextEmployee.acceptSalaryVisitor(this);
				
				System.out.println("    " + nextEmployee.getName() + " : Sold $" + nextSales + ", Was Paid $" + nextSalary);
			}
			else
			{
				if (nextEmployee instanceof BMGSupervisorEmployee)
				{
					((BMGSupervisorEmployee) nextEmployee).acceptSalesVisitor(this);
				}
			}
		}
	}
}
