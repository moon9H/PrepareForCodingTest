import sys
input = sys.stdin.readline

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

N, S = map(int,input().split())

bro_pos = list(map(int,input().split()))

if N == 1 :
    print(abs(bro_pos[0] - S))

else :
    dist_subin = []
    for i in range(len(bro_pos)) :
        dist_subin.append(abs(bro_pos[i] - S))
    dist_subin.sort()
    
    c = dist_subin[0]
    for i in range(1, len(dist_subin)) :
        c = gcd(dist_subin[i], c)
    print (c)