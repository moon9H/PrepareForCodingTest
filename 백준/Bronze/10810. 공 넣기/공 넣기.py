N, M = map(int, input().split())
basket = [0 for _ in range(N)]
for _ in range(M) :
    i, j, k = map(int, input().split())
    basket[i - 1 : j] = [k for _ in range(j - i + 1)]
for i in basket :
    print(i, end=' ')
    