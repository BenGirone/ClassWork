
public abstract class BMGBaseEmployee implements BMGIEmployee
{
	protected String name;
	
	protected String title;
	
	protected double pay;
	
	public BMGBaseEmployee(String name, String title, double pay)
	{
		this.name = name;
		this.title = title;
		this.pay = pay;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String getTitle()
	{
		return title;
	}

	@Override
	public void setTitle(String title)
	{
		this.title = title;
	}

	@Override
	public double getPay()
	{
		return pay;
	}

	@Override
	public void setPay(double pay)
	{
		this.pay = pay;
	}

	@Override
	public abstract void display();

	@Override
	public void displayCustomIterator()
	{
		display();
	}
	
	@Override
	public abstract double acceptSalaryVisitor(BMGEmployeeVisitor visitor);

}
