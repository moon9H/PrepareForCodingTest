import sys

N = int(sys.stdin.readline())
num_list = []

for _ in range(N) :
    num_list.append(int(sys.stdin.readline().strip()))

num_list.sort()
# print(num_list)
num_dic = {}
for i in num_list :
    if i in num_dic :
        num_dic[i] += 1
    else :
        num_dic[i] = 1
# print(num_dic)
mode = []
for i in range(len(num_dic.values())) :
    if list(num_dic.values())[i] == max(num_dic.values()) :
        mode.append(i)
# print(mode)
if sum(num_list) > 0 :
    print(int((sum(num_list) /len(num_list)) + 0.5))
else :
    print(int((sum(num_list) /len(num_list)) - 0.5))
print(num_list[N//2])
if len(mode) == 1 :
    print(list(num_dic.keys())[mode[0]])
else :
    print(list(num_dic.keys())[mode[1]])
print(max(num_list) - min(num_list))
