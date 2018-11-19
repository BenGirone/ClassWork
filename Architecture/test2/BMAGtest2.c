/* 
Ben Girone 11/18/18 CSC 436 
This program creates three implementations of adding the corresponding values of a matrix.
The program tests all three methods thousands of times and analyzes the efficiency of each implementation.
There is a also a theoretical explanation of the results above each implementation.
*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define ROW 6
#define COLUMN 5

/* free all memory used by a matrix */
void destroyMatrix(int **arr)
{
    free(*arr);
    free(arr);
}

/* create a matrix with the size defined by program constants */
int **createMatrix()
{
    int *values = (int *)malloc(ROW * COLUMN * sizeof(int));
    int **matrix = malloc(ROW*sizeof(int*));

    for (int i = 0; i < ROW; i++)
        matrix[i] = values + i * COLUMN;

    return matrix;
}

/* fills a matrix with random values [0,10) */
int **fillMatrix(int **matrix)
{
    int i, j;

    for (i = 0; i <  ROW; i++) 
      for (j = 0; j < COLUMN; j++) 
         matrix[i][j] = rand() % 10;

    return matrix;
}

/* print the matrix to show the correctness of the algorithms. */
void printMatrix(int **matrix) 
{
    int i, j;
    for(i = 0; i < ROW; i++)
    {
        printf("| ");

        for(j = 0; j < COLUMN; j++)
        {
            printf("% 3d ", matrix[i][j]);
        }

        printf("|\n");
    }

    printf("\n");
}

/* 
On each iteration, j is still being reinitialized.
Compilers sometimes help alleviate this, 
but it is typically the programmers duty to optimize nested loops in C.

Efficiency can be improved. The subsequent versions of this function will illustrate. 

Effeciency rank: 2
Time for v_1 = 546927.0 (on my VM)
    -gcc version 7.3.0 (Ubuntu 7.3.0-27ubuntu1~18.04)
*/
void sum_matrix_v1(int **A, int **B, int **C) {
    int i;
    for(i = 0; i < ROW; i++)
    {
        int j;
        for(j = 0; j < COLUMN; j++)
            C[i][j] = A[i][j] + B[i][j];
    }
}

/* 
In this version, initialization of both counters is handled outside the loops.
This typically results in slightly better performance.

However, a mistake has been made.
The nesting order of the loops is incorrect. 
The loops go through the data column by column.
The memory for the matrix is organized as an array of rows, not an array of columns.
A column cannot be cached, because it only exists in a logical sense.

Efficiency rank: 3
Time for v_2 = 553894.0 (on my VM)
    -gcc version 7.3.0 (Ubuntu 7.3.0-27ubuntu1~18.04)
*/
void sum_matrix_v2(int **A, int **B, int **C) {
    int i, j;

    for(j = 0; j < COLUMN; j++)
        for(i = 0; i < ROW; i++)
            C[i][j] = A[i][j] + B[i][j];
}

/*
In this final version the mistake has been corrected.
This access pattern does not require a different row to be accessed on each step.
A single row can be cached until all its elements have been accessed.

Efficiency rank: 1
Time for v_3 = 539575.0 (on my VM)
    -gcc version 7.3.0 (Ubuntu 7.3.0-27ubuntu1~18.04)
*/
void sum_matrix_v3(int **A, int **B, int **C) {
    int i, j;

    for(i = 0; i < ROW; i++)
        for(j = 0; j < COLUMN; j++)
            C[i][j] = A[i][j] + B[i][j];
}

int main(int argc, char const *argv[])
{
    srand(time(NULL));

    double v_1_time = 0.0;
    double v_2_time = 0.0;
    double v_3_time = 0.0;

    clock_t start;

    /* run implementation v_3 1000000 times and totals the approximate cpu time used */
    for (int i = 0; i < 1000000; i++)
    {
        int **A = fillMatrix(createMatrix());
        int **B = fillMatrix(createMatrix());
        int **C = fillMatrix(createMatrix());
        int **D = createMatrix();

        start = clock();
        sum_matrix_v3(A, B, D);
        sum_matrix_v3(D, C, D);
        v_3_time += difftime(clock(), start);

        destroyMatrix(A);
        destroyMatrix(B);
        destroyMatrix(C);
        destroyMatrix(D);
    }

    printf("Time for v_3 = %.1f\n", v_3_time);

    /* run implementation v_2 1000000 times and totals the approximate cpu time used */
    for (int i = 0; i < 1000000; i++)
    {
        int **A = fillMatrix(createMatrix());
        int **B = fillMatrix(createMatrix());
        int **C = fillMatrix(createMatrix());
        int **D = createMatrix();

        start = clock();
        sum_matrix_v2(A, B, D);
        sum_matrix_v2(D, C, D);
        v_2_time += difftime(clock(), start);

        destroyMatrix(A);
        destroyMatrix(B);
        destroyMatrix(C);
        destroyMatrix(D);
    }

    printf("Time for v_2 = %.1f\n", v_2_time);

    /* run implementation v_1 1000000 times and totals the approximate cpu time used */
    for (int i = 0; i < 1000000; i++)
    {
        int **A = fillMatrix(createMatrix());
        int **B = fillMatrix(createMatrix());
        int **C = fillMatrix(createMatrix());
        int **D = createMatrix();

        start = clock();
        sum_matrix_v1(A, B, D);
        sum_matrix_v1(D, C, D);
        v_1_time += difftime(clock(), start);

        destroyMatrix(A);
        destroyMatrix(B);
        destroyMatrix(C);
        destroyMatrix(D);
    }

    printf("Time for v_1 = %.1f\n", v_1_time);

    return 0;
}
