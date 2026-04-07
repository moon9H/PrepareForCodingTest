import sys
input = sys.stdin.readline

def factorial(n) :
    if n == 1 or n == 0:
        return 1
    else :
        return n * factorial(n-1)

def permutation(n,k) :
    return factorial(n) // factorial(n-k)
def combination(n,k) :
    return permutation(n,k) // factorial(k)

test_cases = int(input())

answer_list = []
for _ in range(test_cases) :
    N, M = map(int,input().split())
    answer_list.append(combination(M,N))

for i in answer_list :
    print(i)