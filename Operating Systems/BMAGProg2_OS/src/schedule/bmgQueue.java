//Ben Girone CSC 403 12/5/17
//This file defines a custom copy-able queue class. 

package schedule;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class bmgQueue implements Queue<bmgProcess>
{
	
	//data members
	public Queue<bmgProcess> queue;
	
	//constructor
	public bmgQueue(Queue<bmgProcess> queue)
	{
		this.queue = queue;
	}
	
	//constructor
	public bmgQueue(LinkedList<bmgProcess> queue)
	{
		this.queue = queue;
	}
	
	/**
	 * Gets a copy of this process queue.
	 * @return A queue containing all the elements in this queue.
	 */
	public Queue<bmgProcess> getResetCopy()
	{	
		Queue<bmgProcess> copy = new LinkedList<bmgProcess>();
		
		for (Iterator<bmgProcess> iterator = queue.iterator(); iterator.hasNext();)
		{
			bmgProcess process = iterator.next();
			
			copy.add(process.getResetCopy());
		}
		
		return copy;
	}

	@Override
	public boolean addAll(Collection<? extends bmgProcess> c)
	{
		return queue.addAll(c);
	}

	@Override
	public void clear()
	{
		queue.clear();
	}

	@Override
	public boolean contains(Object o)
	{
		return queue.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		return queue.containsAll(c);
	}

	@Override
	public boolean isEmpty()
	{
		return queue.isEmpty();
	}

	@Override
	public Iterator<bmgProcess> iterator()
	{
		return queue.iterator();
	}

	@Override
	public boolean remove(Object o)
	{
		return queue.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		return queue.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		return queue.retainAll(c);
	}

	@Override
	public int size()
	{
		return queue.size();
	}

	@Override
	public Object[] toArray()
	{
		return queue.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a)
	{
		return queue.toArray(a);
	}

	@Override
	public boolean add(bmgProcess e)
	{
		return queue.add(e);
	}

	@Override
	public bmgProcess element()
	{
		return queue.element();
	}

	@Override
	public boolean offer(bmgProcess e)
	{
		return queue.offer(e);
	}

	@Override
	public bmgProcess peek()
	{
		return queue.peek();
	}

	@Override
	public bmgProcess poll()
	{
		return queue.poll();
	}

	@Override
	public bmgProcess remove()
	{
		return queue.poll();
	}
}
