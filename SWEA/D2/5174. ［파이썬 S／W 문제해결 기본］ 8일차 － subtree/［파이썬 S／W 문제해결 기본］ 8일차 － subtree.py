T = int(input())

for test_case in range(1, T + 1):
    edgeCnt, nodeN = map(int, input().split())
    pair = list(map(int, input().split()))
    parent = [pair[i] for i in range(0, len(pair), 2)]
    child = [pair[i] for i in range(1, len(pair), 2)]
    nodeEdge = {}
    for i in range(1, edgeCnt + 2) :
        nodeEdge[i] = []
    for i in range(len(parent)) :
        nodeEdge[parent[i]].append(child[i])
    subNodeCnt = 0
    stack = [nodeN]
    point = nodeN
    while stack:
        v = stack.pop()
        subNodeCnt += 1
        for c in nodeEdge[v] :
            stack.append(c)
    print("#%d"%test_case, subNodeCnt)