from collections import deque
def decTobin(n) :
    binary = deque()
    while (n > 0) :
        binary.appendleft(str(n % 2))
        n //= 2
    return ''.join(binary)

def solution(n):
    answer = 0
    binary_n = decTobin(n)
    one_cnt = binary_n.count('1')
    i = 1
    bigger_n = decTobin(n + i)
    while(bigger_n.count('1') != one_cnt) :
        i = i + 1
        bigger_n = decTobin(n + i)
    answer = n + i
    return answer
