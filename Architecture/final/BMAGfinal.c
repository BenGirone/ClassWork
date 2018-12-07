/* 
Ben Girone 11/18/18 CSC 436 
This program tests dynamic array and 2d-matrix creation.
Results are printed at the end. 
*/

#include <stdio.h>
#include <stdlib.h>

/* free all memory used by a matrix */
void destroyMatrix(int **A)
{
    free(*A);
    free(A);
}

void destroyArray(int *arr)
{
    free(arr);
}

/* create a matrix with the size defined by program constants */
int **createMatrix(int row, int column)
{
    int *values = (int *)malloc(row * column * sizeof(int));
    int **matrix = malloc(row*sizeof(int*));

    int i,j;
    for (i = 0; i < row; i++) 
    {
        matrix[i] = values + i * column;
        for (j = 0; j < column; j++) {
            printf("Enter value for [%d, %d]:", i + 1, j + 1);

            /* production code should use sscanf to avoid buffer exploits */
            scanf ("%d", &matrix[i][j]);
        }
    }

    return matrix;
}

/* print the matrix to show the correctness of the algorithms. */
void printMatrix(int **matrix, int row, int column) 
{
    int i, j;
    for(i = 0; i < row; i++)
    {
        printf("| ");

        for(j = 0; j < column; j++)
        {
            /* 
            this table format supports 3 digit integers and has a little extra padding on the left when the integers are small
            Dr. Kovach said this is ok for the assignment
            */
            printf("% 3d ", matrix[i][j]);
        }

        printf("|\n");
    }

    printf("\n");
}

int *createArray(int size)
{
    int *arr = (int *)malloc(size * sizeof(int));
    
    int i;
    for (i = 0; i < size; i++) {
        printf("Enter value for [%d]:", i);
        /* production code should use sscanf to avoid buffer exploits */
        scanf ("%d", &arr[i]);
    }

    return arr;
}

void printArray(int *arr, int size)
{
    int i;

    printf("[");

    for (i = 0; i < size - 1; i++) {
        printf("%d, ", arr[i]);
    }

    printf("%d]\n", arr[size - 1]);
}

int main(int argc, char const *argv[])
{
    /* if this was production code, we would make a struct that contains the matrix dimensions */
    int **A = createMatrix(2,2);
    printMatrix(A,2,2);

    /* if this was production code, we would make a struct that contains the array dimensions */
    int *arr = createArray(3);
    printArray(arr, 3);

    /* free the memory */
    destroyMatrix(A);
    destroyArray(arr);
}

/* 
Valgrind mem leak test: PASS
gcc (Ubuntu 7.3.0-27ubuntu1~18.04) 7.3.0
OUTPUT:

Enter value for [1, 1]:1
Enter value for [1, 2]:2
Enter value for [2, 1]:3
Enter value for [2, 2]:4
|   1   2 |
|   3   4 |

Enter value for [0]:1
Enter value for [1]:2
Enter value for [2]:3
[1, 2, 3]
*/