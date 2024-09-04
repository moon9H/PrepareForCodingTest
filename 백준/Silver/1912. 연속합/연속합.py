import sys
input = sys.stdin.readline
INF = float('inf')

def maxsum(low, high, arr) :
    if low == high :
        return max(arr[low], -INF)
    else :
        mid = (low + high) // 2
        lmax = maxsum(low, mid, arr)
        rmax = maxsum(mid + 1, high, arr)
        cmax = maxsum_crossing(low, mid, high, arr)
        return max(lmax, rmax, cmax)

def maxsum_crossing(low, mid, high, arr) :
    left = -INF
    curs = 0
    for i in range(mid, low - 1, -1) :
        curs += arr[i]
        left = max(left, curs)
    right = -INF
    curs = 0
    for j in range(mid + 1, high + 1) :
        curs += arr[j]
        right = max(right, curs)
    
    return left + right

n = int(input())
arr = list(map(int, input().split()))
print(maxsum(0, n - 1, arr))