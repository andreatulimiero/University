#!/bin/bash 

./main < albero_bello.in
echo '[Expected] true'

./main < not_albero_bello.in
echo '[Expected] false'