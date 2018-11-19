// Ben Girone 9/10/18 CSC 436
#include<stdio.h> // scanf printf;

int main()
{
	// get the number of floats
	int n;
	printf("%s", "How many numbers to average? : ");
	scanf("%d", &n);

	// variables to store floats
	float x;
	float sum = 0;

	// recieve and sum floats
	for (int i = 0; i < n; ++i)
	{
		printf("%s\n", "Enter a real number");
		scanf("%f", &x);
		sum += x;
	}

	// print results
	printf("Average is %.3f\n", sum/(float)n);

	// end
	return 0;
}