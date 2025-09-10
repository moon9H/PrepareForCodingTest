from itertools import permutations

def solution(babbling):
    answer = 0
    can_pronun = ["aya", "ye", "woo", "ma"]
    perm_can_pronun = []
    for i in list(permutations(can_pronun, 2)) :
        perm_can_pronun.append("".join(i))
    for i in list(permutations(can_pronun, 3)) :
        perm_can_pronun.append("".join(i))
    for i in list(permutations(can_pronun, 4)) :
        perm_can_pronun.append("".join(i))
    for pronun in babbling :
        if pronun in can_pronun :
            answer += 1
        elif pronun in perm_can_pronun :
            answer += 1
    return answer