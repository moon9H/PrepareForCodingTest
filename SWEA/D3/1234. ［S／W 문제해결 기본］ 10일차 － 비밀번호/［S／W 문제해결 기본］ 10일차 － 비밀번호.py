T = 10
for test_case in range(1, T + 1):
    size, pw = input().split()
    i = 0
    while (i < len(pw) - 1) :
        if pw[i] == pw[i + 1] :
            pw = pw[0:i] + pw[i + 2 : len(pw)]
            i = 0
        else :
            i += 1

    print(f"#{test_case} {pw}")