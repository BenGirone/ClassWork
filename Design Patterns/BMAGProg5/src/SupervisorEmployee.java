import java.util.ArrayList;

public class SupervisorEmployee implements IEmployee
{	
	private IEmployee employee;
	
	private ArrayList<IEmployee> subordinates = new ArrayList<IEmployee>();
	
	public SupervisorEmployee(IEmployee e)
	{
		employee = e;
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
		// TODO Auto-generated method stub
		return 0;
	}

	public void AddSubordinate(IEmployee employee)
	{
		subordinates.add(employee);
	}
	
	public void RemoveSubordinate()
	{
		//ToDo Figure this out.
	}
}
