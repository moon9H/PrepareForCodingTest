import sys
input = sys.stdin.readline
numABC = []
for _ in range(3) :
    numABC.append(int(input()))

print(numABC[0] + numABC[1] - numABC[2])
print(int(str(numABC[0]) + str(numABC[1])) - numABC[2])