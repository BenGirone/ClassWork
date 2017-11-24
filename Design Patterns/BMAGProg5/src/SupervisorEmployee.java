import java.util.ArrayList;
import java.util.Iterator;

public class SupervisorEmployee implements IEmployee
{	
	private IEmployee employee;
	
	private ArrayList<IEmployee> subordinates = new ArrayList<IEmployee>();
	
	public SupervisorEmployee(IEmployee e)
	{
		employee = e;
	}
	
	public ArrayList<IEmployee> getSubordinates()
	{
		return subordinates;
	}

	@Override
	public String GetName()
	{
		return employee.GetName();
	}

	@Override
	public void SetName(String name)
	{
		employee.SetName(name);
	}

	@Override
	public String GetTitle()
	{
		return employee.GetTitle();
	}

	@Override
	public void SetTitle(String title)
	{
		employee.SetTitle(title);
	}

	@Override
	public double GetPay()
	{
		return employee.GetPay();
	}

	@Override
	public void SetPay(double pay)
	{
		employee.SetPay(pay);
	}

	@Override
	public double CalculateWeeklyPay()
	{
		return employee.CalculateWeeklyPay();
	}

	public void AddSubordinate(IEmployee employee)
	{
		subordinates.add(employee);
	}
	
	public void RemoveSubordinate(IEmployee employee)
	{
		if (subordinates.contains(employee))
			subordinates.remove(employee);
	}

	@Override
	public void display()
	{
		employee.display();
		
		for (Iterator<IEmployee> iterator = subordinates.iterator(); iterator.hasNext();)
		{
			System.out.print("    ");
			iterator.next().display();
		}
	}

	@Override
	public void displayCustomIterator()
	{
		employee.displayCustomIterator();
		
		for (EmployeeIterator iterator = new EmployeeIterator(this); iterator.hasNext();)
		{
			System.out.print("    ");
			iterator.next().displayCustomIterator();
		}
	}
}
