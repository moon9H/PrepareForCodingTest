n = int(input().strip())
p = [tuple(map(int, input().split())) for _ in range(n)]

for i in range(n):
    rank = 1
    for j in range(n):
        if p[j][0] > p[i][0] and p[j][1] > p[i][1]:
            rank += 1
    print(rank, end=' ')