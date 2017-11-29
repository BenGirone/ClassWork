//Ben Girone CSC 352 11/29/17
//This file defines an interface that every employee must conform to.getget

public interface BMGIEmployee
{
	/** getName
	 * Gets the name of the employee.
	 * @return a String representation of the employee name
	 */
	public String getName();
	
	/** setName
	 * Sets the name of the employee.
	 * @param name
	 */
	public void setName(String name);
	
	/**getTitle
	 * Gets the title of the employee.
	 * @return A String representation of the employee title
	 */
	public String getTitle();
	
	/** setTitle
	 * Sets the employee title.
	 * @param title
	 */
	public void setTitle(String title);
	
	/** getPay
	 * Gets the pay of the employee. Can be weekly, hourly or commission percentage.
	 * @return a double value that represents the employee's pay
	 */
	public double getPay();
	
	/**setPay
	 * Sets the pay of the employee
	 * @param pay
	 */
	public void setPay(double pay);
	
	/** display
	 * Prints the basic info about the employee and his/her pay information.
	 */
	public void display();
	
	/** displayCustomIterator
	 * Does the same as <code>display()</code>, but uses a custom iterator.
	 */
	public void displayCustomIterator();
	
	/** acceptSalaryVisitor
	 * Accept a visitor that can calculate the weekly salary of the employee, regardless of pay type.
	 * @param visitor
	 * @return a double value that represents the weekly pay of the employee
	 */
	public double acceptSalaryVisitor(BMGSalaryVisitor visitor);
}
