#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* chunk size should be much larger, but is left small for demonstration purposes */
#define CHUNK 5

/* gets input and then echoes*/
int good_echo()
{
    printf("echo: ");

    char* input = NULL;
    char buf[CHUNK];

    size_t totalLength = 0;
    size_t currentLength = 0;

    /* call fgets repeatedly until we reach an endline */
    do {

        /* read a chunk and check for errors */
        if (fgets(buf, CHUNK, stdin) == NULL) 
        {
            printf("Input could not be read\n");
            free(input);
            return 1;
        }

        currentLength = strlen(buf);
        totalLength += currentLength;

        input = realloc(input, totalLength + 1);

        /* check if reallocation was successful */
        if (input == NULL)
        {
            printf("Could not allocate enough memory\n");
            free(input);
            return 1;
        }

        strcat(input, buf);
    /* check if the buffer was not filled or the buffer ends with \n\0 */
    } while (currentLength == (CHUNK - 1) && buf[CHUNK - 2] != '\n');
    
    /* print the result and free the memory, catching any errors */
    if (printf("%s", input) < 0)
    {
        printf("Could not print user input\n");
        free(input);
        return 1;
    }

    free(input);
    return 0;
}

/* Pseduocode
NOTE: check for errors and free memory where appropriate

prompt user for input
create nullptr to char input (inputPtr)
create a constant size buffer for char input (buffer)

do:
    fgets into (buffer) from standard input
        NOTE: fgets is C's method of retrieving raw stream data

    reallocate the (inputPtr) to its current size + (buffer)'s size

    concatenate (buffer) onto (inputPtr)
while: (buffer) is full and the last (non-null) character is not a newline

print the inputPtr contents

*/

/* this is not mentioned in the assignment, but any respectable echo program needs this
 * prints the argument provided by the command line 
 */
int good_echo_arg(const char * input)
{
    /* print the argument and catch any errors */
    if (printf("%s\n", input) < 0)
    {
        printf("Could not print argument\n");
        return 1;
    }
    
    return 0;
}

/* runs good_echo */
int main(int argc, char const *argv[])
{
    if (argc > 1)
    {
        /* prints the argument provided by the command line */
        return good_echo_arg(argv[1]);
    }
    else
    {
        return good_echo();
    }
}

/* Valgrind test report http://valgrind.org/docs/manual/quick-start.html
==12769== HEAP SUMMARY:
==12769==     in use at exit: 0 bytes in 0 blocks
==12769==   total heap usage: 7 allocs, 7 frees, 2,112 bytes allocated
==12769== 
==12769== All heap blocks were freed -- no leaks are possible
*/