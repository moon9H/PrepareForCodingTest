T = 10
for test_case in range(1, T + 1):
    N = int(input())
    origin = list(map(int, input().split()))
    total_command = int(input())
    cmd = list(input().split())
    i = 0

    while (i < len(cmd)) :
        if cmd[i] == 'I' :
            x = int(cmd[i + 1])
            y = int(cmd[i + 2])
            insert_lst = cmd[i + 3 : i + 3 + y]
            origin = origin[0 : x] + insert_lst + origin[x : len(origin)]
            i += 3 + y

    result = " ".join(map(str, origin[0:10]))

    print(f"#{test_case} {result}")