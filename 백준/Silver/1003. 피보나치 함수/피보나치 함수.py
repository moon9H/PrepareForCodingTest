import sys
input = sys.stdin.readline

def cnt_fibo(n) :
    if n == 0 :
        return 1, 0
    else :
        a, b = 1, 0
        for _ in range(n) :
            a, b = b, a + b
        return a, b
for _ in range(int(input())) :
    n = int(input())
    a, b = cnt_fibo(n)
    print(a, b)