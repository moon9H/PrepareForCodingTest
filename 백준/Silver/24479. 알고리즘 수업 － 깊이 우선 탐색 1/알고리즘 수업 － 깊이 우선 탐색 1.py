import sys
sys.setrecursionlimit(1000000)
input = sys.stdin.readline

class Node:
    def __init__(self, value):
        self.value = value
        self.next = None

class LinkedListGraph:
    def __init__(self, size):
        self.headList = [[] for _ in range(size)]
        self.size = size
    
    def show(self):
        for adj_list in self.headList:
            print(' '.join(map(str, adj_list)))
    
    def linkEdge(self, u, v):
        self.headList[u - 1].append(v)
        self.headList[v - 1].append(u)
    
    def sortEdges(self):
        for adj_list in self.headList:
            adj_list.sort()

vertex_num, edge_num, start_vertex = map(int, input().split())

G = LinkedListGraph(vertex_num)
visited = [0] * vertex_num
seq = 1

for _ in range(edge_num):
    u, v = map(int, input().split())
    G.linkEdge(u, v)
    G.linkEdge(v, u)

# Sort adjacency lists to ensure the smallest nodes are visited first
G.sortEdges()

def dfs(graph, R):
    global seq
    visited[R - 1] = seq
    seq += 1
    for neighbor in graph.headList[R - 1]:
        if visited[neighbor - 1] == 0:
            dfs(graph, neighbor)

dfs(G, start_vertex)
for i in visited:
    print(i)
