T = int(input())
for test_case in range(1, T + 1):
    grid = [[0 for _ in range(10)] for _ in range(10)]
    N = int(input())
    for _ in range(N) :
        r1, c1, r2, c2, color = map(int, input().split())
        for row in range(r1, r2 + 1) :
            for col in range(c1, c2 + 1) :
                toPaint = grid[row][col]
                if toPaint == 0 :
                    grid[row][col] = color
                else :
                    if toPaint == color :
                        continue
                    else :
                        grid[row][col] = 3
        
    result = sum(row.count(3) for row in grid)
    print(f"#{test_case} {result}")