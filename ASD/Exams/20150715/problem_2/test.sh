#!/bin/bash 

printf '=== Testing albero_bello === \n\n'

# Test with true input
./main < albero_bello.in
printf '[Expected] true\n\n'

# Test with false input
./main < not_albero_bello.in
printf '[Expected] false\n\n'

printf '=== End of test === \n\n'