//Ben Girone	CSC 403		10/23/17
//This program creates and runs three threads. 
//One thread (ProducerC) produces numbers sequentially and writes them to a variable recognized throughout the program.
//The other threads (ConsumerC) read the numbers from that variable and sum them. They then output the sum upon completion.
//The threads communicate using semaphores to synchronize the production and consumption of each number.

package package1;

//allow for the use of semaphores
import java.util.concurrent.Semaphore;

//the producer class which executes the producer thread
class ProducerC extends Thread
{
	//declare a semaphore for the producer and set it to 2
	public static final Semaphore semProducer = new Semaphore(2);

	//begin thread execution
	public void run()
	{
		for (int i = 1; i <= BMAGProg1c.numbersToProduce; i++)
		{
			//decrement the producer semaphore or wait until it can be
			try
			{
				semProducer.acquire(2);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			//increment the common integer
			BMAGProg1c.commonInt++;

			//increment the consumer semaphore
			ConsumerC.semConsumer.release();
			
			//decrement the producer semaphore or wait until it can be
			try
			{
				semProducer.acquire();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			//increment the consumer semaphore
			ConsumerC.semConsumer.release();
			
			//decrement the producer semaphore or wait until it can be
			try
			{
				semProducer.acquire();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			//increment the consumer semaphore twice
			ConsumerC.semInternal.release(2);
		}
	} //end thread execution
}

//the consumer class which implements the consumer thread
class ConsumerC extends Thread
{
	//declare a variable to hold the sum of each number produced
	private int x = BMAGProg1c.commonInt;

	//declare a semaphore for the consumer and set it to 0
	public static final Semaphore semConsumer = new Semaphore(0);
	
	//declare a semaphore to synchronize the individual consumers
	public static final Semaphore semInternal = new Semaphore(0);

	//begin thread execution
	public void run()
	{
		for (int i = 1; i <= BMAGProg1c.numbersToProduce; i++)
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
			x += BMAGProg1c.commonInt;
			
			//increment the producer semaphore
			ProducerC.semProducer.release();
			
			//decrement the internal semaphore
			try
			{
				semInternal.acquire();
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//increment the producer semaphore
			ProducerC.semProducer.release();
		}

		//output the sum
		System.out.println(this.getName() + ": " + x);
	} //end thread execution
}

//Test Class
public class BMAGProg1c
{

	//declare the amount of numbers to be produced
	public static final int numbersToProduce = 10; 

	//the common integer
	//the producer thread will modify the value of this variable
	//the consumer thread will sum the value of this variable
	public static int commonInt = 0;

	//begin main function
	public static void main(String[] args)
	{
		//declare Producer and Consumer objects
		ProducerC producer = new ProducerC();
		ConsumerC consumer1 = new ConsumerC();
		ConsumerC consumer2 = new ConsumerC();
		
		//start the producer and consumer threads
		producer.start();
		consumer1.start();
		consumer2.start();

	} //end main function
}

/* PseudoCode

BMAGProg1c
Set commonInt to 0;
(main)
	Create a ProducerC object (producer).
	Create 2 ConsumerC objects (consumer1, consumer2).
	Start the objects (producer, consumer1, consumer2) in their own threads. (Call the start method for each object)

ProducerC
Set the producer semaphore to 1.
(run)
	For i along the interval [1,BMAGProg1c.numbersToProduce] in steps of 1.
		Acquire 2 permissions to run from the producer semaphore and decrement it.
		Increment BMAGProg1c.commonInt.
		Increment the consumer semaphore.
		Acquire permission to run from the producer semaphore and decrement it.
		Increment the consumer semaphore.
		Acquire permission to run from the producer semaphore and decrement it.
		Increment the internal semaphore twice.
		
ConsumerC
Set the consumer semaphore to 0.
Set the internal semaphore to 0.
Set x to BMAGProg1c.commonInt.
(run)
	For i along the interval [1,BMAGProg1c.numbersToProduce] in steps of 1.
		Acquire permission to run from the consumer semaphore and decrement it.
		x = x + BMAGProg1c.commonInt.
		Increment the producer semaphore.
		Acquire permission to run from the internal semaphore and decrement it.
		Increment the producer semaphore.
	Print x.
*/
