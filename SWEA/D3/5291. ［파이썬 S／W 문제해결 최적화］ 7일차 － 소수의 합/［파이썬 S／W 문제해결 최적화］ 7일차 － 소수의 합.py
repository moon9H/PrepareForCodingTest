primeSieve = [False, False] + [True] * 999999
for i in range(2, 1000000) :
    if primeSieve[i] :
        for j in range(2 * i, 1000000, i) :
            primeSieve[j] = False

T = int(input())
for test_case in range(1, T + 1):
    a, b = map(int, input().split())
    ans = 0
    
    for i in range(a + 1, b) :
        if primeSieve[i] : ans+= i
    print(f"#{test_case} {ans}")