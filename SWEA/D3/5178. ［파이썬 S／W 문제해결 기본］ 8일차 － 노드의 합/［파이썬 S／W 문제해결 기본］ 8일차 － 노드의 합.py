T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    nodeN, leafM, L = map(int, input().split())
    binary_tree = [0 for _ in range(nodeN + 1)]
    for _ in range(leafM) :
        leafNum, value = map(int, input().split())
        binary_tree[leafNum] = value
    start_index = nodeN if (nodeN % 2 == 0) else nodeN - 1
    if nodeN % 2 == 1 :
        binary_tree[start_index // 2] = binary_tree[start_index] + binary_tree[start_index + 1]
    else :
        binary_tree[start_index // 2] = binary_tree[start_index]
    for i in range(start_index - 2, 1, -2) :
        binary_tree[i // 2] = binary_tree[i] + binary_tree[i + 1]
    print('#%d'%test_case, binary_tree[L])