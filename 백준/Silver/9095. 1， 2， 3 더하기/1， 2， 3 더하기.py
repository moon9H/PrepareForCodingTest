import sys
input = sys.stdin.readline

def count(n : int) :
    if n == 1 :
        return 1
    if n == 2 :
        return 2
    if n == 3 :
        return 4
    return count(n - 3) + count(n - 2) + count(n - 1)

T = int(input())
for _ in range(T) :
    n = int(input())
    print(count(n))