import sys
input = sys.stdin.readline

num_list = list(map(int, input().split()))

i = 0
ascending = False
descending = False
while (i < 7) :
    if i == 0 :
        if num_list[i] + 1 == num_list[i + 1] :
            ascending = True
        elif num_list[i] - 1 == num_list[i + 1] :
            descending = True
        else :
            print('mixed')
            break
        i += 1
    else :
        if ascending :
            if num_list[i] + 1 != num_list[i + 1] :
                print('mixed')
                break
        if descending :
            if num_list[i] - 1 != num_list[i + 1] :
                print('mixed')
                break
        i += 1

if i == 7 :
    if ascending :
        print('ascending')
    if descending :
        print('descending')