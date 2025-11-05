from itertools import combinations
A = [i for i in range(1, 13)]
T = int(input())
for test_case in range(1, T + 1):
    cnt = 0
    N, K = map(int, input().split())
    part = list(combinations(A, N))
    for i in part :
        if sum(i) == K : cnt += 1
    print(f"#{test_case} {cnt}")