T = int(input())
for test_case in range(1, T + 1):
    T, T_end, k = map(float, input().split())
    i = 0
    while (T > T_end) :
        T*=k
        i += 1
    print(f"#{test_case} {i}")