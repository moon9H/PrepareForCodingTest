def solution(n):
    answer = 0
    for i in range(0, n + 1, 2) :
        print(i)
        answer += i
    return answer