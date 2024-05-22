import sys
input = sys.stdin.readline

S = input().rstrip()

alpha_list = [0] * 26


for i in S :
    alpha_list[ord(i) - 97] += 1

for i in alpha_list :
    print(i,end= ' ')
print()