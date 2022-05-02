#include <stdio.h>
int removeDuplicates(int* nums, int numsSize){
    int* in = nums;
    int* out = nums;
    
    while (numsSize--) {
        if (*in == *out) {
            out++;
            continue;
        }
        else {
            *++in = *out++;
        }
    }

    return in - nums + 1;
}

int main(int argc, char**argv) {
    int a [] = {0,0,1,1,1,2,2,4,4,5,6,7,7,8,8,8,9,11,12,14,14,14,14,14,15,17};
    // int a [] = {4,4};
    // int a [] = {5};
    int *p = a;
    int i;
    int n = sizeof(a)/sizeof(a[0]);
    int k = 0;

    for (i = 0; i < n; ++i) printf("%d ", p[i]); printf("\n");
    k = removeDuplicates(a, n);
    for (i = 0; i < k; ++i) printf("%d ", p[i]); printf("\n"); printf("k=%d\n", k);
}