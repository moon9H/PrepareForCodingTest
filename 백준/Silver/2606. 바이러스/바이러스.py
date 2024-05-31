import sys
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

computer_cnt = int(input())
edge_cnt = int(input())

visited = [0] * computer_cnt
G = LinkedListGraph(computer_cnt)

for _ in range(edge_cnt):
    u, v = map(int, input().split())
    G.linkEdge(u, v)
    G.linkEdge(v, u)

G.sortEdges()

def dfs(graph, R):
    visited[R - 1] = 1
    for neighbor in graph.headList[R - 1]:
        if visited[neighbor - 1] == 0:
            dfs(graph, neighbor)

dfs(G, 1)

print(sum(visited) - 1)