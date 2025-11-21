def rsp_game(l, r, arr) :
    p1 = arr[l]
    p2 = arr[r]
    if p1 == p2 :
        return l if l < r else r
    else :
        if p1 == 2 :
            return l if p2 == 1 else r
        elif p1 == 1 :
            return l if p2 == 3 else r
        elif p1 == 3 :
            return l if p2 == 2 else r

def tournament(l, r, arr):
    if l == r :
        return l
    mid = (l + r) // 2
    left = tournament(l, mid, arr)
    right = tournament(mid + 1, r, arr)
    return rsp_game(left, right, arr)

T = int(input())
for test_case in range(1, T + 1):
    N = int(input())
    cards = list(map(int, input().split()))
    
    print(f"#{test_case} {tournament(0, N - 1, cards) + 1}")