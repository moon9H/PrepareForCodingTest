import math
def solution(brown, yellow):
    answer = []
    carpet_size = brown + yellow
    i = 1
    while (i <= math.sqrt(yellow)) :
        if yellow % i == 0 :
            first = i
            second = yellow // i
            need_brown = (first + 2) * (second + 2) - yellow
            if need_brown == brown :
                answer.append(second + 2)
                answer.append(first + 2)
        i += 1

    return answer