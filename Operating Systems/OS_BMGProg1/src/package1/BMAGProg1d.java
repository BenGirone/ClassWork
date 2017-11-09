//Ben Girone	CSC 403		10/23/17
//This program creates and runs any number of threads. 
//One thread (ProducerC) produces numbers sequentially and writes them to a variable recognized throughout the program.
//The other threads (ConsumerC) read the numbers from that variable and sum them. They then output the sum upon completion.
//The threads communicate using semaphores to synchronize the production and consumption of each number.


package package1;

//allow for the use of semaphores
import java.util.concurrent.Semaphore;

//the producer class which executes the producer thread
class ProducerD extends Thread
{
	//declare a semaphore for the producer and set it to n
	public static final Semaphore semProducer = new Semaphore(BMAGProg1d.n);

	//begin thread execution
	public void run()
	{
		for (int i = 1; i <= BMAGProg1d.numbersToProduce; i++)
		{
			//decrement the producer semaphore or wait until it can be
			try
			{
				semProducer.acquire(BMAGProg1d.n);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			//increment the common integer
			BMAGProg1d.commonInt++;

			
			for (int j = 0; j < BMAGProg1d.n; j++)
			{
				//increment the consumer semaphore
				ConsumerD.semConsumer.release();
				
				//decrement the producer semaphore or wait until it can be
				try
				{
					semProducer.acquire();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
			}
			
			ConsumerD.semInternal.release(BMAGProg1d.n);
		}
	} //end thread execution
}

//the consumer class which implements the consumer thread
class ConsumerD extends Thread
{
	//declare a variable to hold the sum of each number produced
	private int x = BMAGProg1d.commonInt;

	//declare a semaphore for the consumer and set it to 0
	public static final Semaphore semConsumer = new Semaphore(0);
	
	//declare a semaphore to synchronize the individual consumers
	public static final Semaphore semInternal = new Semaphore(0);

	//begin thread execution
	public void run()
	{
		for (int i = 1; i <= BMAGProg1d.numbersToProduce; i++)
		{
			//decrement the consumer semaphore or wait until it can be
			try
			{
				semConsumer.acquire();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			//sum the number most recently produced by the producer thread
			x += BMAGProg1d.commonInt;
			
			//increment the producer semaphore
			ProducerD.semProducer.release();
			
			try
			{
				semInternal.acquire();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			//increment the producer semaphore
			ProducerD.semProducer.release();
		}

		//output the sum
		System.out.println(this.getName() + ": " + x);
	} //end thread execution
}

public class BMAGProg1d
{

	//declare the amount of numbers to be produced
	public static final int numbersToProduce = 10;
	
	//declare the number of consumer threads
	public static final int n = 8;

	//the common integer
	//the producer thread will modify the value of this variable
	//the consumer thread will sum the value of this variable
	public static int commonInt = 0;

	//begin main function
	public static void main(String[] args)
	{
		//declare Producer and Consumer objects
		ProducerD producer = new ProducerD();
		
		for(int i = 0; i < n; i++)
		{
			new ConsumerD().start();
		}
		
		//start the producer and consumer threads
		producer.start();

	} //end main function

}

/* PseudoCode

BMAGProg1d
Set commonInt to 0;
Set n to 8
(main)
	Create a ProducerD object (producer).
	For i along the interval [0,n) in steps of 1.
		Create and start a consumer object in its own thread. (Call the start method for the object)
	Start the producer object in its own thread. (Call the start method for the object)

ProducerD
Set the producer semaphore to 1.
(run)
	For i along the interval [1,BMAGProg1d.numbersToProduce] in steps of 1.
		Acquire BMAGProg1d.n permissions to run from the producer semaphore and decrement it.
		Increment BMAGProg1d.commonInt.
		For j along the interval [0,BMAGProg1d.n) in steps of 1.
			Increment the consumer semaphore.
			Acquire permission to run from the producer semaphore and decrement it.
		Increment the internal semaphore BMAGProg1d.n times.
		
ConsumerD
Set the consumer semaphore to 0.
Set the internal semaphore to 0.
Set x to BMAGProg1d.commonInt.
(run)
	For i along the interval [1,BMAGProg1d.numbersToProduce] in steps of 1.
		Acquire permission to run from the consumer semaphore and decrement it.
		x = x + BMAGProg1d.commonInt.
		Increment the producer semaphore.
		Acquire permission to run from the internal semaphore and decrement it.
		Increment the producer semaphore.
	Print x.
*/
