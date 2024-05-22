import sys
input = sys.stdin.readline

S = input().rstrip()

prefix_list = []
for i in range(len(S)) :
    prefix_list.append(S[i:len(S)])

prefix_list.sort() 
for i in prefix_list :
    print(i)