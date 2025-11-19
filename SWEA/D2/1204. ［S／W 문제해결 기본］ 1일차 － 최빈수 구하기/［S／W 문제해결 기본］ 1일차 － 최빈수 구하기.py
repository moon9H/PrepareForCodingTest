T = int(input())
for test_case in range(1, T + 1):
    tc = int(input())
    scoreCnt = [0 for _ in range(101)]
    scoreList = list(map(int, input().split()))
    for sc in scoreList :
        scoreCnt[sc] += 1
    mode = float('-inf')
    mode_idx = -1
    for i in range(101) :
        if mode <= scoreCnt[i] :
            mode = scoreCnt[i]
            mode_idx = i

    print(f"#{tc} {mode_idx}")