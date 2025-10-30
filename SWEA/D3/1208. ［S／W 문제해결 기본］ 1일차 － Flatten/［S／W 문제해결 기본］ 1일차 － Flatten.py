T = 10
for test_case in range(1, T + 1):
    dump = int(input())
    histogram = list(map(int ,input().split()))

    MAX_H = 100
    freq = [0 for _ in range(MAX_H + 1)]
    mn_value = MAX_H
    mx_value = 0
    
    for h in histogram :
        freq[h] += 1
        mn_value = h if h < mn_value else mn_value
        mx_value = h if h > mx_value else mx_value

    while dump > 0 and mx_value - mn_value > 1 :
        freq[mx_value] -= 1
        freq[mx_value - 1] += 1
        if freq[mx_value] == 0 :
            mx_value -= 1
        
        freq[mn_value] -= 1
        freq[mn_value + 1] += 1
        if freq[mn_value] == 0 :
            mn_value += 1
        
        dump -= 1
    
    print(f"#{test_case} {mx_value - mn_value}")