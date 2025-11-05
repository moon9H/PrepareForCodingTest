T = 10
for test_case in range(1, T + 1):
    tc = int(input())
    arr = [list(map(int, input().split())) for _ in range(100)]
    transpos_arr = [list(row) for row in zip(*arr)]
    maxRow = max([sum(i) for i in arr])
    maxCol = max([sum(i) for i in transpos_arr])
    cross_sum = 0
    reverse_cross_sum = 0
    for i in range(100) :
        cross_sum += arr[i][i]
        reverse_cross_sum += arr[i][99 - i]
    mx_val = max(maxRow, maxCol, cross_sum, reverse_cross_sum)
    print(f"#{test_case} {mx_val}")