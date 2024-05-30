import sys
from collections import deque
sys.setrecursionlimit(10**6)
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

vertex_num , edge_num, start_vertex = map(int, input().split())

visited = [0] * vertex_num
G = LinkedListGraph(vertex_num)
seq = 1
dq = deque()

for _ in range(edge_num):
    u, v = map(int, input().split())
    G.linkEdge(u, v)
    G.linkEdge(v, u)

G.sortEdges()

def bfs(graph, R) :
    global seq
    visited[R - 1] = seq
    seq += 1
    dq.append(R - 1)
    while (len(dq) != 0) :
        u = dq.popleft()
        for v in G.headList[u] :
            if not visited[v - 1] :
                visited[v - 1] = seq
                seq += 1
                dq.append(v - 1)

bfs(G, start_vertex)

for i in visited :
    print(i)