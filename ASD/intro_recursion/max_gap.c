#include <assert.h>
#include <stdio.h>

#include "max_gap.h"

int maxGap(int * array, int start, int end) {	
    if(start + 2 == end) return array[start + 1] - array[start];
    int next = maxGap(array, start + 1, end);
    int current = array[start + 1] - array[start];
    return current > next ? current : next;
}
