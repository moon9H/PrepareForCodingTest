import sys
from collections import deque
input = sys.stdin.readline

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

N, M = map(int, input().split())
Maze = [[0] * M for _ in range(N)]
visit = [[False] * M for _ in range(N)]

for i in range(N):
  temp = list(input())
  for j in range(M):
    Maze[i][j] = int(temp[j])


def BFS():
  dq = deque()
  dq.append((0, 0))
  visit[0][0] = True
  while dq:
    now = dq.popleft()
    for i in range(4):
      x = now[0] + dx[i]
      y = now[1] + dy[i]
      if (x >= 0 and x < N and y >= 0 and y < M):
        if (Maze[x][y] != 0 and not visit[x][y]):
          visit[x][y] = True
          Maze[x][y] = Maze[now[0]][now[1]] + 1
          dq.append((x, y))

BFS()
print(Maze[N - 1][M - 1])