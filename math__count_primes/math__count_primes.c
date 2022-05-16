/* https://leetcode.com/explore/interview/card/top-interview-questions-easy/102/math/744/

Count Primes

    Given an integer n, return the number of prime numbers that are strictly less than n.

 

Example 1:

    Input: n = 10
    Output: 4
    Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

Example 2:

    Input: n = 0
    Output: 0

Example 3:

    Input: n = 1
    Output: 0

 

Constraints:

    0 <= n <= 5 * 10^6  */

#include <stdio.h>
#include <stdlib.h>

/**************************************************************************/
// Way 1: Sieve of Eratosthenes
int isPrime(int n) {
    if (n < 2) {
        return 0;
    }
    int i = 2;
    while (i < n) {
        if ((n % i++) == 0) {
            return 0;
        }
    }
    return 1;
}

int countPrimes(int n) {
    if (n == 0) {
        return 0;
    }
    
    int* primes = malloc(n*4);

    int i;
    for (i = 2; i < n ; ++i) {
        primes[i] = 1;
    }


    for (i = 2; i*i < n; ++i) {
        if(isPrime(i)) {
            for (int j = i; j < n; j+=i) {
                primes[j] = 0;
            }
            primes[i] = 1; 
        }
    }

    int c = 0;
    for (i = 2; i < n; ++i) {
        if (primes[i] == 1) {
            ++c;
        }
    }
    free(primes);    
    return c;
}
/**************************************************************************/
// Way 2: straight
int isPrimeOddMoreEq11(int n /* odd and more or equal than 11*/) {
    if ((n & 1) == 0 || (n % 3) == 0 || (n % 5) == 0 || (n % 7) == 0) {
        return 0;
    } 
    int i = 11;
    while ((i*i) <= n) {
        if ((n % i) == 0) {
            return 0;
        }
        i += 2;
    }
    return 1;
}

int countPrimes2(int n /*0 <= n <= 5 * 10^6 */) {
    if (n <= 2) { 
        return 0;
    } else if (n <= 3) {
        return 1; // 2
    } else if (n <= 5) {
        return 2; // 2 3
    } else if (n <= 7 ) { 
        return 3; // 2 3 5
    } else if (n < 11) {
        return 4; // 2 3 5 7
    }

    int primes = 4;
    for (int i = 11; i < n; i+=2) {
        if (isPrimeOddMoreEq11(i)) {
             ++primes;
        }
    }
    return primes;
}

/**************************************************************************/
int main (int argc, char **argv)
{

    int n[] =  {10, 0, 1, 2, 3, 8, 10000, 499979, 5000000};
    int exp[] = {4, 0, 0, 0, 1, 4,  1229, 41537, 348513}; 

#if 0
    int number = 5000000;
    printf("1: %d\n", countPrimes(number)); 
    printf("2: %d\n", countPrimes2(number));
#else
    for (int i = 0; i < sizeof(n)/sizeof(n[0]); ++i) {
        int r = countPrimes(n[i]);
        printf("%s %d=countPrimes(%d) exp=%d\n", r == exp[i] ? "Pass" : "Fail", r,  n[i], exp[i]); 
    }
#endif
    return 0;
}