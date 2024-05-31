import sys
from collections import deque
input = sys.stdin.readline

class LinkedListGraph:
    def __init__(self, size):
        self.headList = [[i] for i in range(1, size + 1)]
        self.size = size
    
    def show(self):
        for adj_list in self.headList:
            print(' '.join(map(str, adj_list)))
    
    def linkEdge(self, u, v):
        self.headList[u - 1].append(v)
        # self.headList[v - 1].append(u)
    
    def sortEdges(self):
        for adj_list in self.headList:
            adj_list[1:len(adj_list)] = sorted(adj_list[1:len(adj_list)])


vertex_num, edge_num, start_vertex = map(int, input().split())

dfs_visited = [0] * vertex_num
bfs_visited = [0] * vertex_num

G = LinkedListGraph(vertex_num)

for _ in range(edge_num) :
    u, v = map(int, input().split())
    G.linkEdge(u, v)
    G.linkEdge(v, u)


G.sortEdges()

dfs_seq = 1
bfs_seq = 1
dfs_seqList = []
bfs_seqList = []

def dfs(graph, R):
    global dfs_seq
    dfs_visited[R - 1] = dfs_seq
    dfs_seq += 1
    dfs_seqList.append(R)
    for neighbor in graph.headList[R - 1]:
        if dfs_visited[neighbor - 1] == 0:
            dfs(graph, neighbor)

def bfs(graph, R) :

    global bfs_seq
    bfs_visited[R - 1] = bfs_seq
    bfs_seq += 1
    dq = deque()
    dq.append(R - 1)
    bfs_seqList.append(R)
    while (len(dq) != 0) :
        u = dq.popleft()
        for v in G.headList[u] :
            if not bfs_visited[v - 1] :
                bfs_visited[v - 1] = bfs_seq
                bfs_seq += 1
                bfs_seqList.append(v)
                dq.append(v - 1)


dfs(G, start_vertex)
bfs(G, start_vertex)

for i in dfs_seqList :
    print(i, end=' ')
print()
for i in bfs_seqList :
    print(i, end=' ')
print()