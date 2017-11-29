//Ben Girone CSC 352 11/29/17
//This file contains a class whose objects can traverse the hierarchy of employees.

public class BMGEmployeeIterator
{
	//variable declaration
	private BMGSupervisorEmployee employee;
	private int index = 0;
	
	//constructor
	public BMGEmployeeIterator(BMGSupervisorEmployee employee)
	{
		this.employee = employee;
	}
	
	/** hasNext
	 * Checks if the iterator can continue.
	 * @return a boolean indicating whether or not the iterator has a next element
	 */
	public boolean hasNext()
	{
		return (index < employee.getSubordinates().size());
	};
	
	/** next
	 * Retrieves the next employee in the hierarchy.
	 * @return the next employee in the hierarchy
	 */
	public BMGIEmployee next()
	{
		BMGIEmployee toReturn = (BMGIEmployee) employee.getSubordinates().toArray()[index];
		index++;
		return toReturn;
	};
	
	
	
}
