from random import randint

nodesno = 10

with open('bst.in', mode='w+') as f:
    for _ in range(nodesno):
        print('{} {}'.format(randint(1, 1000), randint(1, 1000)), file=f)
