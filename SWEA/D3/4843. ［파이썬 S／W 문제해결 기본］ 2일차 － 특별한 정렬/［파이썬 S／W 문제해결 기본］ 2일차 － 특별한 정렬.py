from collections import deque as dq
T = int(input())
for test_case in range(1, T + 1):
    N = int(input())
    numList = list(map(int, input().split()))
    numList = dq(sorted(numList))
    specialSort = []
    while (numList) :
        specialSort.append(numList.pop())
        specialSort.append(numList.popleft())
    ans = " ".join(str(x) for x in specialSort[0 : 10])
    print(f"#{test_case} {ans}")