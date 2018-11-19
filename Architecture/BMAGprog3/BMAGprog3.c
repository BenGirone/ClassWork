// Ben Girone 9/19/18 CSC 436
#include<stdio.h> // scanf printf;
#include<float.h> // FLT_MAX
#include<stdlib.h> // calloc


// simple data type
struct SimpleFloatArray
{
    int size;
    float *items;
};

// make a SimpleFloatArray from user input
struct SimpleFloatArray SimpleFloatArrayFromInput()
{
    struct SimpleFloatArray floats;
    float *iterator;

    printf("%s", "How many numbers : ");
	scanf("%d", &floats.size);

    floats.items = (float *) calloc(floats.size, sizeof(float));
    iterator = floats.items; // start the iterator at the front of the array
    
    for(int i = 0; i < floats.size; i++)
    {
        printf("Enter a number: ");
        scanf("%f", iterator++);
    }
    
    return floats;
}

// get the average of a SimpleFloatArray
float average(struct SimpleFloatArray floats)
{
    float sum = 0;

    for (int i = 0; i < floats.size; i++)
    {
        sum += floats.items[i];
    }

    return sum/(float)floats.size;
}

// get the minimum value in a SimpleFloatArray
float min(struct SimpleFloatArray floats)
{
    float min = FLT_MAX;

    for (int i = 0; i < floats.size; i++)
    {
        if (floats.items[i] < min)
            min = floats.items[i];
    }

    return min;
}

// get the maximum value in a SimpleFloatArray
float max(struct SimpleFloatArray floats)
{
    float max = FLT_MIN;

    for (int i = 0; i < floats.size; i++)
    {
        if (floats.items[i] > max)
            max = floats.items[i];
    }

    return max;
}

int main()
{
    // variable declaration 
	struct SimpleFloatArray floats = SimpleFloatArrayFromInput();

	// print results
	printf("Average is %.3f\n", average(floats));
	printf("Smallest is %.3f\n", min(floats));
	printf("Largest is %.3f\n", max(floats));

	// end
	return 0;
}

