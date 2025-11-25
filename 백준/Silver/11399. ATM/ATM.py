import sys
input = sys.stdin.readline

N = int(input())
human = list(map(int, input().split()))
human.sort()
for i in range(1, N) :
    human[i] += human[i - 1]

print(sum(human))