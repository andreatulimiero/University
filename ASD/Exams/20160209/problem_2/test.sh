#!/bin/bash 

printf '=== Testing AVL === \n\n'

# Test with true input
./main < AVL.in
printf '[Expected] true\n\n'

# Test with false input
./main < Not_AVL.in
printf '[Expected] false\n\n'

printf '=== End of test === \n\n'