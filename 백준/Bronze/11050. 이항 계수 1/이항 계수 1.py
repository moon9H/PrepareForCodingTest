import sys
input = sys.stdin.readline

N, K = map(int,input().split(' '))

def factorial(n) :
    if n == 1 or n == 0:
        return 1
    else :
        return n * factorial(n-1)

def permutation(n,k) :
    return factorial(n) // factorial(n-k)
def combination(n,k) :
    return permutation(n,k) // factorial(k)

print(combination(N,K))