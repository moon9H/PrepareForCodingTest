T = int(input())
for test_case in range(1, T + 1):
    N = int(input())
    numList = list(map(int, input().split()))
    binHeap = [0]
    for i in range(N) :
        binHeap.append(numList[i])
        child = i + 1
        parent = child // 2
        while (binHeap[child] < binHeap[parent]) :
            binHeap[parent], binHeap[child] = binHeap[child], binHeap[parent]
            child = parent
            parent = child // 2
    ans = 0
    while(N > 0) :
        ans += binHeap[N // 2]
        N //= 2
    print(f"#{test_case} {ans}")