import sys
input = sys.stdin.readline
from collections import deque

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]
T = int(input())
for _ in range(T) :
    colL, rowL, cabbageN = map(int, input().split())
    visited = [[-1] * colL for _ in range(rowL)]
    q = deque()
    ans = 0
    sr, sc = 0, 0
    farm = [[0] * colL for _ in range(rowL)]
    cabbages = []
    for _ in range(cabbageN) :
        posX, posY = map(int, input().split())
        farm[posY][posX] = 1
        cabbages.append([posY, posX])
    
    for i in cabbages :
        sr, sc = i
        if visited[sr][sc] != -1:
            continue
        else :
            ans += 1
            q.append((sr, sc))
            visited[sr][sc] = ans
            
            while q :
                r, c = q.popleft()

                for k in range(4) :
                    nr = r + dr[k]
                    nc = c + dc[k]

                    if nr < 0 or nr >= rowL or nc < 0 or nc >= colL :
                        continue
                    if farm[nr][nc] == 0 :
                        continue
                    if visited[nr][nc] != -1:
                        continue
                    
                    visited[nr][nc] = ans
                    q.append((nr, nc))

    print(ans)