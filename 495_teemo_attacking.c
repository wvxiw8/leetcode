/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Teemo Attacking
 * @id             495
 * @Difficulty     Easy
 * @Tags           array, simulation
 * @Link           https://leetcode.com/problems/teemo-attacking/

Teemo Attacking

    Our hero Teemo is attacking an enemy Ashe with poison attacks! When Teemo attacks Ashe, Ashe gets poisoned for a exactly duration seconds. More formally, an attack at second t will mean Ashe is poisoned during the inclusive time interval [t, t + duration - 1]. If Teemo attacks again before the poison effect ends, the timer for it is reset, and the poison effect will end duration seconds after the new attack.

    You are given a non-decreasing integer array timeSeries, where timeSeries[i] denotes that Teemo attacks Ashe at second timeSeries[i], and an integer duration.

    Return the total number of seconds that Ashe is poisoned.


Example 1:

    Input: timeSeries = [1,4], duration = 2
    Output: 4
    Explanation: Teemo's attacks on Ashe go as follows:
    - At second 1, Teemo attacks, and Ashe is poisoned for seconds 1 and 2.
    - At second 4, Teemo attacks, and Ashe is poisoned for seconds 4 and 5.
    Ashe is poisoned for seconds 1, 2, 4, and 5, which is 4 seconds in total.

Example 2:

    Input: timeSeries = [1,2], duration = 2
    Output: 3
    Explanation: Teemo's attacks on Ashe go as follows:
    - At second 1, Teemo attacks, and Ashe is poisoned for seconds 1 and 2.
    - At second 2 however, Teemo attacks again and resets the poison timer. Ashe is poisoned for seconds 2 and 3.
    Ashe is poisoned for seconds 1, 2, and 3, which is 3 seconds in total.


Constraints:

    1 <= timeSeries.length <= 10^4
    0 <= timeSeries[i], duration <= 10^7
    timeSeries is sorted in non-decreasing order. */

#include <stdio.h>
#include <limits.h>

int findPoisonedDuration(int* timeSeries, int timeSeriesSize, int duration) {
    if (!duration)
        return 0;

    int total = 0;
    int currBeg = 0;
    int currEnd = 0; /* Not inclusive */
    int prevEnd = INT_MIN; /* Not inclusive */

    while (timeSeriesSize) {
        currBeg = *timeSeries++;
        currEnd = currBeg + duration;
        int overlap = prevEnd > currBeg;
        int dur = overlap ? currEnd - prevEnd : currEnd - currBeg;
        total += dur;
        printf("[%d %d) overlap=%d currDur=%d totalDur=%d \n", currBeg, currEnd, overlap, dur, total);
        prevEnd = currEnd;
        --timeSeriesSize;
    }
    return total;
}

int main() {
    int timeSeries[] = {1,4}; int duration = 2; int exp = 4;
    // int timeSeries[] = {1,2}; int duration = 2; int exp = 3;
    // int timeSeries[] = {1,2, 6}; int duration = 3; int exp = 7;

    for (int i = 0; i < sizeof(timeSeries)/sizeof(timeSeries[0]); ++i)  printf("%d,", timeSeries[i]);  printf(" duration=%d\n", duration);
    
    int output = findPoisonedDuration(timeSeries, sizeof(timeSeries)/sizeof(timeSeries[0]), duration);
    if (output == exp)
        printf("OK\n");
    else 
        printf("Failed. out=%d exp=%d\n", output, exp);

    return 0;
}
