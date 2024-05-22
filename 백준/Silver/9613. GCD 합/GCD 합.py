import sys
input = sys.stdin.readline

num_test = int(input())
gcd_sum = [0] * num_test

def gcd(m,n):
    if m < n :
        m, n = n, m

    if n == 0 :
        return m
    
    if m % n == 0 :
        return n

    else :
        while n != 0 :
            t = m % n
            (m,n) = (n,t)
        return abs(m)

for i in range(num_test) :
    test_case = list(map(int,input().split()))
    num = test_case[0]
    for j in range(1, num) :
        for k in range(j+1, num + 1) :
            gcd_sum[i] += gcd(test_case[j],test_case[k])

for i in gcd_sum :
    print(i)