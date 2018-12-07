/* Ben Girone    12/2/18    CSC 436 */
/* This program uses the producer-consumer problem to illustrate race conditions and solutions */

#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <semaphore.h>

/* a counter shared by the producer and consumer */
static int counter;

/* simple producer that does not account for race conditions */
void * producer() 
{
    int i;
    int sum = 0;

    for (i = 1; i <= 10; i++) 
    {
        counter = i;
        sum += counter;
    }

    printf("Producer Value: %d\n", sum);

    pthread_exit(NULL);
}

/* simple consumer that does not account for race conditions */
void * consumer() 
{
    int i;
    int sum = 0;

    for (i = 1; i <= 10; i++) 
    {
        sum += counter;
    }

    printf("Consumer Value: %d\n", sum);

    pthread_exit(NULL);
}

/* semaphores */
static sem_t producerSemaphore;
static sem_t consumerSemaphore;

/* advanced producer that uses a semaphore to ensure the consumer has a chance to consume */
void * producer_fixed() 
{
    int i;
    int sum = 0;

    for (i = 1; i <= 10; i++) 
    {
        /* acquire the producer semaphore (or wait) */
        sem_wait(&producerSemaphore);

        counter = i;
        sum += counter;

        /* release the consumer semaphore so it can consume the most recent number produced */
        sem_post(&consumerSemaphore);
    }

    printf("Producer (Fixed) Value: %d\n", sum);

    pthread_exit(NULL);
}

/* advanced consumer that waits for the producer to produce before it consumes */
void * consumer_fixed() 
{
    int i;
    int sum = 0;

    for (i = 1; i <= 10; i++) 
    {
        /* acquire the consumer semaphore (or wait) */
        sem_wait(&consumerSemaphore);
        
        sum += counter;
        
        /* release the producer semaphore so that another number can be produced */
        sem_post(&producerSemaphore);
    }

    printf("Consumer (Fixed) Value: %d\n", sum);

    pthread_exit(NULL);
}

int main(int argc, char const *argv[])
{
    pthread_t producerThread, consumerThread;

    /* initialize and run threads */
    pthread_create(&producerThread, NULL, producer, NULL);
    pthread_create(&consumerThread, NULL, consumer, NULL);
    
    /* wait until both threads are finished */
    pthread_join(producerThread, NULL);
    pthread_join(consumerThread, NULL);

    printf("\n");
    
    /* initialize the semaphores so that the producer runs first*/
    if (sem_init(&producerSemaphore, 0, 1) || sem_init(&consumerSemaphore, 0, 0))
        printf("Semaphore initialization failed\n");
    
    pthread_t producerThread_fixed, consumerThread_fixed;

    /* initialize and run threads */
    pthread_create(&producerThread_fixed, NULL, producer_fixed, NULL);
    pthread_create(&consumerThread_fixed, NULL, consumer_fixed, NULL);
    
    /* wait until both threads are finished */
    pthread_join(producerThread_fixed, NULL);
    pthread_join(consumerThread_fixed, NULL);

    pthread_exit(NULL);
}
/* gcc -v && gcc -pthread BMAGprog5.c -o test && ./test

gcc version 7.3.0 (Ubuntu 7.3.0-27ubuntu1~18.04)
Thread model: posix

Producer Value: 55
Consumer Value: 100

Producer (Fixed) Value: 55
Consumer (Fixed) Value: 55

*/