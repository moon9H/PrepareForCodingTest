T = int(input())
for tc in range(1, T + 1):
    N = int(input())
    W = [list(map(int, input().split())) for _ in range(N)]

    visited = [False] * N
    visited[0] = True               
    INF = float('inf')
    best = INF

    m = INF
    for i in range(N):
        for j in range(N):
            if i != j and W[i][j] < m:
                m = W[i][j]

    order = [[] for _ in range(N)]
    for i in range(N):
        nxts = list(range(1, N))            
        nxts.sort(key=lambda j: W[i][j])
        order[i] = nxts

    def dfs(cur, used, cost):
        global best

        remaining_edges = (N - 1 - used) + 1

        if cost + m * remaining_edges >= best:
            return

    
        if used == N - 1:
            total = cost + W[cur][0]
            if total < best:
                best = total
            return

    
        for nxt in order[cur]:
            if not visited[nxt]:
                visited[nxt] = True
                dfs(nxt, used + 1, cost + W[cur][nxt])
                visited[nxt] = False 

    dfs(0, 0, 0)
    print(f"#{tc} {best}")