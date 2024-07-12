def solution(N, stages):
    stay_stages = [0] * (N + 1)
    for i in stages :
        stay_stages[i - 1] += 1
    failure_percent = [0] * (N + 1)
    for i in range(N + 1) :
        if sum(stay_stages[i:N + 1]) :
            failure_percent[i] = stay_stages[i] / sum(stay_stages[i : N + 1])
        else :
               failure_percent[i] = 0
    failure_dict = {i + 1: failure_percent[i] for i in range(0, N)}
    answer = sorted(failure_dict, key=failure_dict.get, reverse=True)
    return answer