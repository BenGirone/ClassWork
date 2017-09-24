//Ben Girone, CSC 352, 9/8/17 

//This file tests BMAGList3.
//It will test the print function
//It will test the AddToEnd function
//It will test the firstElement function
//It will test whether the hasMoreElements function can detect the end of the list
//It will also test whether the class can properly through an exception

package ListPkg;

public class TestClass3 {

	public static void main(String[] args) {
	    // create a list of strings from 0 to 35
	    BMAGList3 L = new BMAGList3();
	    for (int i = 0; i < 36; i++)
	    {
	        L.AddToEnd(Integer.toString(i));
	    }
	    
	    //test the print function
	    L.Print();
    	
	    //A variable to hold our strings while they print
	    String tmp;
	    
	    // now iterate through the list, printing each item
	    L.firstElement();
	    while (L.hasMoreElements())
	    {
	        tmp = (String)L.nextElement();
	        System.out.println(tmp);
	    }
	    
	    // now test the exception handling
	    L.firstElement();
	    System.out.println("An exception will be printed after the 35th element");
	    while (true)
	    {
	    	try
	    	{
	    		tmp = (String)L.nextElement();
	    	}
	    	catch(NoNextElementException ex)
	    	{
	    		System.out.println(ex.getMessage());
	    		break;
	    	}
	    	
	    	System.out.println(tmp);
	    }
	}
}
