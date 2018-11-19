// Ben Girone 9/10/18 CSC 436
#include<stdio.h> // scanf printf;
#include<float.h> // FLT_MAX

int main()
{
	// get the number of floats
	int n;
	printf("%s", "How many numbers to average? : ");
	scanf("%d", &n);

	// variables to store floats and track largest and smallest floats
	float x;
	float largest = 0;
	float smallest = FLT_MAX;
	float sum = 0;

	// collect and sum floats while deciding largest and smallest 
	for (int i = 0; i < n; ++i)
	{
		printf("%s\n", "Enter a real number");
		scanf("%f", &x);

		if (x > largest)
			largest = x;
		else if (x < smallest)
			smallest = x;

		sum += x;
	}

	// print results
	printf("Average is %.3f\n", sum/(float)n);
	printf("Smallest is %.3f\n", smallest);
	printf("Largest is %.3f\n", largest);

	// end
	return 0;
}