import sys
import math
import itertools as itt
input = sys.stdin.readline

def is_prime_num(n):
    arr = [True] * (n + 1)
    arr[0] = False
    arr[1] = False

    for i in range(2, int(math.sqrt(n)+1)):
        if arr[i] == True:
            j = 2

            while (i * j) <= n:
                arr[i*j] = False
                j += 1

    return arr

def three_prime(n) :
    raw_primeList = is_prime_num(n)
    prime_list = []
    for i in range(n) :
        if raw_primeList[i]:
            prime_list.append(i)
    for comb in itt.combinations_with_replacement(prime_list, 3) :
        if sum(comb) == n :
            return comb
    
    return 0

for _ in range(int(input())):
    K = int(input())
    for i in three_prime(K) :
        print(i, end=' ')
    print()
