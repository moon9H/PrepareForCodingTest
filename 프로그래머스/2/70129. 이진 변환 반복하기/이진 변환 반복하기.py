from collections import deque
def decTobin(n) :
    binary = deque()
    while (n > 0):
        binary.appendleft(n % 2)
        n //= 2
    binary = list(map(str,binary))
    binary = ''.join(binary)
    
    return binary

def solution(s):
    answer = []
    removed_zero = 0
    tried = 0
    while(s != '1') :
        before_len = len(s)
        s = s.replace('0','')
        after_len = len(s)
        removed_zero += before_len - after_len
        tried += 1
        s = decTobin(after_len)
    
    answer.append(tried)
    answer.append(removed_zero)

    return answer