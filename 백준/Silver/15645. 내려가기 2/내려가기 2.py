import sys
input = sys.stdin.readline

def max_down(line, A) :
    maxs = A[0]
    for i in range(1, line) :
        temp = []
        temp.append(max(maxs[0], maxs[1]) + A[i][0])
        temp.append(max(maxs[0], maxs[1], maxs[2]) + A[i][1])
        temp.append(max(maxs[1], maxs[2]) + A[i][2])
        maxs = temp
    return max(maxs)

def min_down(line, A) :
    mins = A[0]
    for i in range(1, line) :
        temp = []
        temp.append(min(mins[0], mins[1]) + A[i][0])
        temp.append(min(mins[0], mins[1], mins[2]) + A[i][1])
        temp.append(min(mins[1], mins[2]) + A[i][2])
        mins = temp
    return min(mins)

line = int(input())
arr = []
for _ in range(line) :
    temp = list(map(int, input().split()))
    arr.append(temp)

print(max_down(line, arr), min_down(line, arr))