T = int(input())
for test_case in range(1, T + 1):
    N, M = map(int, input().split())
    arr = list(map(int, input().split()))
    max = float('-inf')
    min = float('inf')
    for i in range(N - M + 1) :
        rangeSum = sum(arr[i : i + M])
        min = rangeSum if rangeSum < min else min
        max = rangeSum if rangeSum > max else max
    print(f"#{test_case} {max - min}")