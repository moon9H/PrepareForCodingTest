import sys
input = sys.stdin.readline
mul = 1
for _ in range(3) :
    mul *= int(input())

mul = str(mul)
num_cnt = [0 for _ in range(10)]

for i in mul :
    num_cnt[ord(i) - 48] += 1

for i in num_cnt :
    print(i)