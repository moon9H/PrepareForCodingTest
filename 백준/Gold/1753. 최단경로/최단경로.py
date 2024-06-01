import sys
import heapq

input = sys.stdin.readline

class LinkedListGraph:
    def __init__(self, size):
        self.headList = [[] for _ in range(size)]
        self.size = size
    
    def show(self):
        for i, adj_list in enumerate(self.headList):
            print(f"{i+1}: {' '.join(map(str, adj_list))}")
    
    def linkEdge(self, u, v, w):
        self.headList[u - 1].append((v, w))
    
    def sortEdges(self):
        for adj_list in self.headList:
            adj_list.sort()

def dijkstra(graph, R, size):
    d = [float("inf")] * size
    d[R - 1] = 0
    pq = [(0, R)]  # Priority queue of (distance, vertex)
    
    while pq:
        dist_u, u = heapq.heappop(pq)
        
        if dist_u > d[u - 1]:
            continue
        
        for v, weight in graph.headList[u - 1]:
            new_dist = dist_u + weight
            if new_dist < d[v - 1]:
                d[v - 1] = new_dist
                heapq.heappush(pq, (new_dist, v))
    
    return d

V, E = map(int, input().split())  # V -> # of vertex, E = # of Edge
K = int(input())  # K -> Start Vertex

G = LinkedListGraph(V)

for _ in range(E):
    u, v, w = map(int, input().split())  # u->v, w -> weight
    G.linkEdge(u, v, w)

# G.sortEdges()

distances = dijkstra(G, K, V)
for distance in distances:
    print(distance if distance != float("inf") else "INF")
