def combination(n, a) :
    if a == 1 or a == n - 1:
        return n
    elif a == 0 or a == n:
        return 1
    else :
        mom = 1
        bro = 1
        for i in range(n, n - a, -1) :
            mom *= i
        for i in range(a, 0, -1) :
            bro *= i
        return mom // bro
    
T = int(input())
for test_case in range(1, T + 1):
    n, a, b = map(int, input().split())
    small = a if a < b else b
    print(f"#{test_case} {combination(n, small)}")