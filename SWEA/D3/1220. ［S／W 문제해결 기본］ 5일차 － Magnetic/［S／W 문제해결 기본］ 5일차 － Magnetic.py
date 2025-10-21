T = 10
for test_case in range(1, T + 1):
    square_len = int(input())
    deadlock = 0
    table = [list(map(int, input().split())) for _ in range(100)]
    transposed_table = [list(row) for row in zip(*table)]

    for line in transposed_table :
        isN = False
        for i in line :
            if i == 1 :
                isN = True
            if i == 2 :
                if isN :
                    isN = False
                    deadlock += 1

    print(f"#{test_case} {deadlock}")