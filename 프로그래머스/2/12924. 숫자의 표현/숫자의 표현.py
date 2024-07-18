def solution(n):
    answer = 0
    for i in range(1, n + 1) :
        sum = 0
        cnt = i
        while (sum < n) :
            sum += cnt
            cnt += 1
        if sum == n :
            answer += 1
    return answer