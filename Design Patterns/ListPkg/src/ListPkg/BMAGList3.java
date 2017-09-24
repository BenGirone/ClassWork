//Ben Girone, CSC 352, 9/8/17

//This file implements the List Class described at:
//http://pages.cs.wisc.edu/~hasti/cs368/JavaTutorial/

package ListPkg;

class NoNextElementException extends NullPointerException{
	public NoNextElementException(String message) {
        super(message);
    }
}

class ListNode
{
	public Object item;
	public ListNode next = null;
	
	public ListNode()
	{
		
	}
	
	public ListNode(Object o)
	{
		item = o;
	}
}

/*
 *  BMAGList3 class
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
public class BMAGList3
{
  private ListNode headPtr;  // the first headPtr
  private int numItems;     // the number of items currently in the list
  
  private ListNode currentObject; //the current object
  private ListNode mostRecentNode;
  
  /*
   * constructor: initialize the list to be empty
   */
  public BMAGList3()
  {
    numItems = 0;
  }

  /*
   * AddToEnd
   *
   * Given: Object ob
   * Do:    Add ob to the end of the list.
   */
  public void AddToEnd(Object ob)
  {
	  ListNode n = new ListNode(ob);
	  
	  if (headPtr == null)
	  {
		  headPtr = n;
		  mostRecentNode = headPtr;
	  }
	  else
	  {
		  mostRecentNode.next = n;
		  mostRecentNode = mostRecentNode.next;
	  }
	  
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
	  //ensure the list is not empty
	  if (headPtr != null)
	  {
		  //print the first square bracket
		  System.out.print("[");
		  ListNode currentNode = headPtr;
		  
		  //print all but the last item
		  while (currentNode.next != null)
		  {
			  System.out.print(currentNode.item.toString() + ", ");
			  currentNode = currentNode.next;
		  }
		  
		  //print the last item and the second square bracket and end line
		  System.out.println(currentNode.item.toString() + "]");
	  }
	  else
	  {
		  System.out.println("[]");
	  }
  }
  
  /** firstElement
   * 
   * Sets the pointer to point to the first element in the list and returns the element.
   * 
   * @return The first element in the list
   */
  public Object firstElement()
  {
	  currentObject = headPtr;
	  return headPtr;
  }
  
  
  /** nextElement
   * 
   * Returns the current object and increments the pointer to the next one.
   * 
   * @return The current object
   */
  public Object nextElement()
  {
	  
	  Object toReturn = null;

	  try
	  {
		  toReturn = currentObject.item;
		  currentObject = currentObject.next;
	  }
	  catch(NullPointerException ex)
	  {
		  throw new NoNextElementException("EXCEPTION: There are no more elements in the list.");
	  }
	  
	  return toReturn;
	  
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
	  if (currentObject == null)
	  {
		  return false;
	  }
	  else
	  {
		  return true;
	  }
  }

}


