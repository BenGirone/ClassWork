import java.util.ArrayList;
import java.util.Iterator;

public class BMGSupervisorEmployee implements BMGIEmployee
{	
	private BMGIEmployee employee;
	
	private ArrayList<BMGIEmployee> subordinates = new ArrayList<BMGIEmployee>();
	
	public BMGSupervisorEmployee(BMGIEmployee e)
	{
		employee = e;
	}
	
	public ArrayList<BMGIEmployee> getSubordinates()
	{
		return subordinates;
	}

	@Override
	public String getName()
	{
		return employee.getName();
	}

	@Override
	public void setName(String name)
	{
		employee.setName(name);
	}

	@Override
	public String getTitle()
	{
		return employee.getTitle();
	}

	@Override
	public void setTitle(String title)
	{
		employee.setTitle(title);
	}

	@Override
	public double getPay()
	{
		return employee.getPay();
	}

	@Override
	public void setPay(double pay)
	{
		employee.setPay(pay);
	}

	@Override
	public double calculateWeeklyPay()
	{
		return employee.calculateWeeklyPay();
	}

	public void AddSubordinate(BMGIEmployee employee)
	{
		subordinates.add(employee);
	}
	
	public void RemoveSubordinate(BMGIEmployee employee)
	{
		if (subordinates.contains(employee))
			subordinates.remove(employee);
	}

	@Override
	public void display()
	{
		employee.display();
		
		for (Iterator<BMGIEmployee> iterator = subordinates.iterator(); iterator.hasNext();)
		{
			System.out.print("    ");
			iterator.next().display();
		}
	}

	@Override
	public void displayCustomIterator()
	{
		employee.displayCustomIterator();
		
		for (BMGEmployeeIterator iterator = new BMGEmployeeIterator(this); iterator.hasNext();)
		{
			System.out.print("    ");
			iterator.next().displayCustomIterator();
		}
	}
	
	@Override
	public double acceptSalaryVisitor(BMGEmployeeVisitor visitor)
	{
		if (employee instanceof BMGCommissionEmployee)
			return visitor.getTotalSalary((BMGCommissionEmployee)employee);
		
		if (employee instanceof BMGHourlyEmployee)
			return visitor.getTotalSalary((BMGHourlyEmployee)employee);
		
		return visitor.getTotalSalary((BMGWeeklyEmployee)employee);
	}
	
	
}
