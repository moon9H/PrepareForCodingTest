from collections import deque
def decodeInToBinary(n, size) :
    binary_ver = deque()
    while (n > 0) :
        binary_ver.appendleft(n % 2)
        n //= 2
    while ( len(binary_ver) < size ) :
        binary_ver.appendleft(0)
    return binary_ver

def solution(n, arr1, arr2):
    answer = []
    decoded_map1 = []
    decoded_map2 = []
    for i in arr1 :
        decoded_map1.append(list(decodeInToBinary(i,n)))
    for i in arr2 :
        decoded_map2.append(list(decodeInToBinary(i,n)))
    temp_answer = []
    for i in range(len(decoded_map1)) :
        temp_col = []
        for j in range(len(decoded_map1[0])) :
            if decoded_map1[i][j] + decoded_map2[i][j] >= 1 :
                temp_col.append(1)
            else :
                temp_col.append(0)
        temp_answer.append(temp_col)
    for i in temp_answer :
        for j in range(len(i)) :
            if i[j] == 1 :
                i[j] = '#'
            else :
                i[j] = ' '
        answer.append(''.join(i))
    
    return answer
