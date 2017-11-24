public class EmployeeIterator
{
	private SupervisorEmployee employee;
	private int index = 0;
	
	public EmployeeIterator(SupervisorEmployee employee)
	{
		this.employee = employee;
	}
	
	public boolean hasNext()
	{
		return (index < employee.getSubordinates().size());
	};
	
	public IEmployee next()
	{
		IEmployee toReturn = (IEmployee) employee.getSubordinates().toArray()[index];
		index++;
		return toReturn;
	};
	
	
	
}
