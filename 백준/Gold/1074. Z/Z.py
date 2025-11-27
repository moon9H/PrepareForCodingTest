import sys
input = sys.stdin.readline

N, R, C = map(int, input().split())

def zSearch(n, r, c) :
    if n == 0 :
        return 0
    half = pow(2, n - 1)
    area = half * half

    if r < half and c < half :
        return zSearch(n - 1, r, c)
    elif r < half and c >= half :
        return area * 1 + zSearch(n - 1, r, c - half)
    elif r >= half and c < half :
        return area * 2 + zSearch(n - 1, r - half, c)
    else :
        return area * 3 + zSearch(n - 1, r - half, c - half)

print(zSearch(N, R, C))