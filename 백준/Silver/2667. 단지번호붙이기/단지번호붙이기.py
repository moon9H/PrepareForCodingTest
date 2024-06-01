import sys
from collections import deque
input = sys.stdin.readline

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

map_size = int(input())

apart_map = [[0]*map_size for _ in range(map_size)]
visit = [[False]*map_size for _ in range(map_size)]

for i in range(map_size):
  temp = list(input())
  for j in range(map_size):
    apart_map[i][j] = int(temp[j])


def BFS(start_x, start_y):
  block_apart_num = 1
  dq = deque()
  dq.append((start_x, start_y))
  visit[start_x][start_y] = True
  while dq:
    now = dq.popleft()
    for i in range(4):
      x = now[0] + dx[i]
      y = now[1] + dy[i]
      if (x >= 0 and x < map_size and y >= 0 and y < map_size):
        if (apart_map[x][y] != 0 and not visit[x][y]):
          block_apart_num += 1
          visit[x][y] = True
          apart_map[x][y] = apart_map[now[0]][now[1]] + 1
          dq.append((x, y))
  return block_apart_num  

block_num = 0
block_apart_list = []
for i in range(map_size) :
    for j in range(map_size) :
        if apart_map[i][j] == 1 and not visit[i][j] :
            block_num += 1
            block_apart_list.append(BFS(i,j))

print(block_num)
block_apart_list.sort()
for i in block_apart_list :
    print(i)