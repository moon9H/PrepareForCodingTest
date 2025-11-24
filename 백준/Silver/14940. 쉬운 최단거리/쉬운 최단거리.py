import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())
roadMap = [list(map(int, input().split())) for _ in range(N)]

# 목표 지점(값이 2인 칸)의 좌표 찾기
sr, sc = 0, 0
for i in range(N) :
    for j in range(M) :
        if roadMap[i][j] == 2 :
            sr, sc = i, j

# dist 배열:
# - 기본값 -1 : "갈 수 있는 땅(1/2)인데 아직 도달 여부가 불명" → 나중에 도달 못 하면 -1 그대로 사용
# - 나중에 BFS로 방문하면 최단 거리로 갱신
dist = [[-1] * M for _ in range(N)]

# -------------------------------
# 1. BFS 준비
# -------------------------------
q = deque()

# 시작점(목표 지점)에서 BFS 시작
# 문제에서 "각 지점에서 목표 지점까지의 거리"를 구해야 하므로
# 목표(2)에서 역으로 퍼져나가면, 각 칸까지의 최단 거리가 됨.
q.append((sr, sc))
dist[sr][sc] = 0  # 시작점까지의 거리는 0

# 상, 하, 좌, 우 이동 정의
dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

# -------------------------------
# 2. BFS 진행
# -------------------------------
while q:
    r, c = q.popleft()

    for k in range(4):
        nr = r + dr[k]
        nc = c + dc[k]

        # 1) 맵 범위를 벗어나면 스킵
        if nr < 0 or nr >= N or nc < 0 or nc >= M:
            continue

        # 2) 원래 갈 수 없는 땅(0)은 애초에 BFS 대상이 아님
        if roadMap[nr][nc] == 0:
            continue

        # 3) 이미 방문한 칸(dist != -1)이면 스킵
        if dist[nr][nc] != -1:
            continue

        # 4) 위 조건들을 모두 통과하면 이동 가능 + 처음 방문하는 칸
        dist[nr][nc] = dist[r][c] + 1  # 현재까지 거리 + 1
        q.append((nr, nc))

# -------------------------------
# 3. 출력 처리
# -------------------------------
# 조건 정리:
# - roadMap[i][j] == 0 : 원래 갈 수 없는 땅 → 0 출력
# - roadMap[i][j] == 1 :
#     - dist[i][j] == -1 이면: 원래 갈 수 있는 땅인데 도달 불가 → -1 출력
#     - dist[i][j] >= 0 이면: 최단 거리 출력
# - roadMap[i][j] == 2 : 목표 지점 → dist가 0이므로 그대로 0 출력 (문제 예제와 동일)
for i in range(N):
    row = []
    for j in range(M):
        if roadMap[i][j] == 0:
            # 애초에 갈 수 없는 땅
            row.append('0')
        else:
            # 갈 수 있는 땅(1 또는 2)
            # dist가 -1이면 도달 불가, 아니면 최단 거리
            row.append(str(dist[i][j]))
    print(' '.join(row))