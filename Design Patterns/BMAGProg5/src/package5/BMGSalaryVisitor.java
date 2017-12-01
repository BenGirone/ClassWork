package package5;
//Ben Girone CSC 352 11/29/17
//This class defines a visitor that can calculate the weekly pay of any employee type.

public class BMGSalaryVisitor
{
	/**getTotalSalary
	 * Calculates and returns the weekly pay of an hourly employee
	 * @param employee
	 * @return a double value that represents the weekly pay of the employee.
	 */
	public double getTotalSalary(BMGHourlyEmployee employee)
	{
		return employee.getHours() * employee.getPay();
	}
	
	/**getTotalSalary
	 * Calculates and returns the weekly pay of an commission employee
	 * @param employee
	 * @return a double value that represents the weekly pay of the employee.
	 */	
	public double getTotalSalary(BMGCommissionEmployee employee)
	{
		return employee.getSales() * employee.getPay()/100.0;
	}
	
	/**getTotalSalary
	 * Calculates and returns the weekly pay of an weekly employee
	 * @param employee
	 * @return a double value that represents the weekly pay of the employee.
	 */	
	public double getTotalSalary(BMGWeeklyEmployee employee)
	{
		return employee.getPay();
	}
}
