import sys
input = sys.stdin.readline

from collections import deque

def bfs(start) :
    global visited, graph, nodeCnt
    q = deque([start])
    visited[start] = True
    
    while q :
        u = q.popleft()
        for v in range(1, nodeCnt + 1) :
            if graph[u][v] == 1 and not visited[v] :
                visited[v] = True
                q.append(v)

nodeCnt, edgeCnt = map(int, input().split())
graph = [[0] * (nodeCnt + 1) for _ in range(nodeCnt + 1)]
visited = [False] * (nodeCnt + 1)
for _ in range(edgeCnt) :
    i, j = map(int, input().split())
    graph[i][j] = 1
    graph[j][i] = 1

count = 0
for node in range(1, nodeCnt + 1) :
    if not visited[node] :
        bfs(node)
        count += 1

print(count)