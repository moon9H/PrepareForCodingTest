import sys
input = sys.stdin.readline

N, M, B = map(int, input().split())

freq = [0] * 257  # 높이 0~256 빈도수
min_h, max_h = 256, 0

for _ in range(N):
    row = list(map(int, input().split()))
    for h in row:
        freq[h] += 1
        if h < min_h:
            min_h = h
        if h > max_h:
            max_h = h

best_time = float('inf')
best_height = 0

# 만들 목표 높이 h를 min_h ~ max_h까지만 확인
for h in range(min_h, max_h + 1):
    time = 0
    inventory = B

    # 현재 높이 k에서 h로 맞출 때 드는 비용을 freq[k]만큼 한꺼번에 반영
    for k in range(min_h, max_h + 1):
        if freq[k] == 0:
            continue

        diff = k - h
        if diff > 0:
            # 블록 제거
            time += 2 * diff * freq[k]
            inventory += diff * freq[k]
        elif diff < 0:
            diff = -diff
            # 블록 설치
            time += diff * freq[k]
            inventory -= diff * freq[k]

    if inventory < 0:
        continue  # 블록 부족하면 이 높이는 불가능

    if time < best_time:
        best_time = time
        best_height = h
    elif time == best_time and h > best_height:
        best_height = h

print(best_time, best_height)