def inorder(tree, i, N) :
    global cnt
    if i > N :
        return
    inorder(tree, 2*i, N)
    tree[i] = cnt
    cnt += 1
    inorder(tree, 2*i + 1, N)

T = int(input())
for test_case in range(1, T + 1):
    N = int(input())
    tree = [0 for _ in range (N + 1)]
    cnt = 1
    inorder(tree, 1, N)
    print(f"#{test_case} {tree[1]} {tree[N//2]}")