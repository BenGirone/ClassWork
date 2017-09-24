//Ben Girone, CSC 352, 9/8/17

//This file implements the List Class described at:
//http://pages.cs.wisc.edu/~hasti/cs368/JavaTutorial/


package package1;

/*
 *  BMAGList class
 *    
 *  A List is an ordered collection of any kind of Object.
 * 
 *  Operations:
 *     AddToEnd    		Add a given object to the end of the list.
 *     Print       		Print (to standard output) the objects in the list in order,
 *                 		enclosed in square brackets, separated by spaces.
 *     firstElement		Sets the pointer to point to the first element in the list and returns the element.
 *     nextElement		Returns the current object and increments the pointer to the next one.
 *     hasMoreElements	Checks if the pointer to the current object has reached an empty space
 * 						or the end of the list.
 */
public class BMAGList 
{
  private static final int INIT_LEN = 10;
  private Object [] items;  // the actual items
  private int numItems;     // the number of items currently in the list
  
  private int currentObject; //the current object
  
  /*
   * constructor: initialize the list to be empty
   */
  public BMAGList()
  {
    items = new Object [INIT_LEN];
    numItems = 0;
    currentObject = 0;
  }

  /*
   * AddToEnd
   *
   * Given: Object ob
   * Do:    Add ob to the end of the list.
   */
  public void AddToEnd(Object ob)
  {
	  //check if we have filled the array
	  if (numItems == items.length)
	  {
		  Object [] temp = new Object [items.length * 2];
		  
		  for(int i = 0; i < items.length; i++)
		  {
			  temp[i] = items[i];
		  }
		  
		  //replace the current array with a copy that is double the current size
		  items = temp;
	  }
	  
	  //add the new item and increment the number of items
	  items[numItems] = ob;
	  numItems++;
  }
  
  /*
   * Print
   *
   * Print (to standard output) the objects in the list in order, enclosed in
   * square brackets, separated by spaces.
   */
  public void Print()
  {
	  //print the first square bracket
	  System.out.print("[");
	  
	  //ensure there are items to be printed
	  if (numItems > 0)
	  {
		  //print all but the final element in items
		  for (int i = 0; i < (numItems - 1); i++)
		  {
			  System.out.print(items[i].toString() + ", ");
		  }
		  
		  //print the final element
		  System.out.print(items[(numItems - 1)].toString());
	  }
	  
	  //print the second square bracket and end line
	  System.out.println("]");
  }
  
  /** firstElement
   * 
   * Sets the pointer to point to the first element in the list and returns the element.
   * 
   * @return The first element in the list
   */
  public Object firstElement()
  {
	  currentObject = 0;
	  return items[currentObject];
  }
  
  
  /** nextElement
   * 
   * Returns the current object and increments the pointer to the next one.
   * 
   * @return The current object
   */
  public Object nextElement()
  {
	  currentObject++;
	  return items[currentObject - 1];
  }
  
  /** hasMoreElements
   * 
   * Checks if the pointer to the current object has reached an empty space
   * or the end of the list.
   * 
   * @return True if the pointer can be incremented. False if not
   */
  public boolean hasMoreElements()
  {
	  //check if we have reached an empty portion of the array or the end of the array if full
	  if (currentObject > (numItems - 1))
	  {
		  return false;
	  }
	  else
	  {
		  return true;
	  }
  }
  
  //This function tests BMAGList.
  //It will test the print function
  //It will test the AddToEnd function
  //It will test the firstElement function.
  //It will test whether the hasMoreElements function can detect the end of the list
  public static void main(String[] args) 
  {
	    // create a list of strings from 0 to 35
	    BMAGList L = new BMAGList();
	    for (int i = 0; i < 36; i++) 
	    {
	        L.AddToEnd(Integer.toString(i));
	    }
	    
	    //test the print function
	    L.Print();
    	
	    // now iterate through the list, printing each item
	    L.firstElement();
	    while (L.hasMoreElements())
	    {
	        String tmp = (String)L.nextElement();
	        System.out.println(tmp);
	    }
	}
  
}
