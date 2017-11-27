public class BMGEmployeeIterator
{
	private BMGSupervisorEmployee employee;
	private int index = 0;
	
	public BMGEmployeeIterator(BMGSupervisorEmployee employee)
	{
		this.employee = employee;
	}
	
	public boolean hasNext()
	{
		return (index < employee.getSubordinates().size());
	};
	
	public BMGIEmployee next()
	{
		BMGIEmployee toReturn = (BMGIEmployee) employee.getSubordinates().toArray()[index];
		index++;
		return toReturn;
	};
	
	
	
}
