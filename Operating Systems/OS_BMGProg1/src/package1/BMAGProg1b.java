//Ben Girone	CSC 403		10/23/17
//This program creates and runs two threads. 
//One thread (ProducerB) produces numbers sequentially and writes them to a variable recognized throughout the program.
//The other thread (ConsumerB) reads the numbers from that variable and sums them. It then outputs the sum upon completion.
//The threads communicate using semaphores to synchronize the production and consumption of each number.

package package1;

//allow for the use of semaphores
import java.util.concurrent.Semaphore;

//the producer class which executes the producer thread
class ProducerB extends Thread
{
	//declare a semaphore for the producer and set it to 1
	public static final Semaphore semProducer = new Semaphore(1);

	//begin thread execution
	public void run()
	{
		for (int i = 1; i <= BMAGProg1b.numbersToProduce; i++)
		{
			//decrement the producer semaphore or wait until it can be
			try
			{
				semProducer.acquire();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			//increment the common integer
			BMAGProg1b.commonInt++;

			//increment the consumer semaphore
			ConsumerB.semConsumer.release();
		}
	} //end thread execution
}

//the consumer class which implements the consumer thread
class ConsumerB extends Thread
{
	//declare a variable to hold the sum of each number produced
	private int x = BMAGProg1b.commonInt;

	//declare a semaphore for the consumer and set it to 0
	public static final Semaphore semConsumer = new Semaphore(0);

	//begin thread execution
	public void run()
	{
		for (int i = 1; i <= BMAGProg1b.numbersToProduce; i++)
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
			x += BMAGProg1b.commonInt;

			//increment the producer semaphore
			ProducerB.semProducer.release();
		}

		//output the sum
		System.out.println(x);
	} //end thread execution
}

//Test Class
public class BMAGProg1b
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
		ProducerB producer = new ProducerB();
		ConsumerB consumer = new ConsumerB();

		//start the producer and consumer threads
		producer.start();
		consumer.start();

	} //end main function
}

/* PseudoCode

BMAGProg1b
Set commonInt to 0;
(main)
	Create a ProducerB object (producer).
	Create a ConsumerB object (consumer).
	Start the objects (producer, consumer) in their own threads. (Call the start method for both objects)

ProducerB 
Set the producer semaphore to 1.
(run)
	For i along the interval [1,BMAGProg1b.numbersToProduce] in steps of 1.
		Acquire permission to run from the producer semaphore and decrement it.
		Increment BMAGProg1b.commonInt.
		Increment the consumer semaphore.
		
ConsumerB
Set the consumer semaphore to 0.
Set x to BMAGProg1b.commonInt.
(run)
	For i along the interval [1,BMAGProg1b.numbersToProduce] in steps of 1.
		Acquire permission to run from the consumer semaphore and decrement it.
		x = x + BMAGProg1b.commonInt.
		Increment the producer semaphore.
	Print x.
*/