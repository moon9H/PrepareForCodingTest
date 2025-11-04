from collections import deque

T = int(input())
for tc in range(1, T + 1):
    N = int(input())
    maze = [list(input().strip()) for _ in range(N)]

    # 1) 시작점 찾기
    sr = sc = -1
    for r in range(N):
        for c in range(N):
            if maze[r][c] == '2':
                sr, sc = r, c
                break
        if sr != -1:
            break

    # 2) BFS 준비
    q = deque()
    q.append((sr, sc))
    dist = [[-1]*N for _ in range(N)]
    dist[sr][sc] = 0

    # 4방향
    dr = (-1, 1, 0, 0)
    dc = (0, 0, -1, 1)

    answer = 0  # 기본값(불가능)

    # 3) BFS
    while q:
        r, c = q.popleft()
        for k in range(4):
            nr, nc = r + dr[k], c + dc[k]
            if nr < 0 or nr >= N or nc < 0 or nc >= N:
                continue
            if maze[nr][nc] == '1':        # 벽
                continue
            if dist[nr][nc] != -1:         # 이미 방문
                continue

            dist[nr][nc] = dist[r][c] + 1

            if maze[nr][nc] == '3':        # 도착 발견
                # 시작/도착 칸 제외한 길이 = 현재 거리 - 1
                answer = dist[nr][nc] - 1
                q.clear()  # 더 볼 필요 없음
                break
            
            q.append((nr, nc))

    print(f"#{tc} {max(0, answer)}")