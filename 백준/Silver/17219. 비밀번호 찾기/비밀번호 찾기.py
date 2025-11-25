import sys
input = sys.stdin.readline

N, M = map(int, input().split())
sitePW = {}
for _ in range(N) :
    site, pw = input().split()
    sitePW[site] = pw
for _ in range(M) :
    site = input().strip()
    print(sitePW[site])