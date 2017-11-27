
public interface BMGIEmployee
{
	public String getName();
	
	public void setName(String name);
	
	public String getTitle();
	
	public void setTitle(String title);
	
	public double getPay();
	
	public void setPay(double pay);
	
	public double calculateWeeklyPay();
	
	public void display();
	
	public void displayCustomIterator();
	
	public double acceptSalaryVisitor(BMGEmployeeVisitor visitor);
}
