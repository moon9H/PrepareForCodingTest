INF = float('inf')
MINUS_INF = float('-inf')
def min_max_index(arr) :
    min = INF
    max = MINUS_INF
    min_index = 0
    max_index = 0
    for i in range(len(arr)) :
        if min > arr[i] :
            min = arr[i]
            min_index = i
        if max < arr[i] :
            max = arr[i]
            max_index = i
    return max, max_index, min, min_index

T = 10
for test_case in range(1, T + 1):
    dump = int(input())
    dump_cnt = 0
    histogram = list(map(int ,input().split()))
    highest, high_i, lowest, low_i = min_max_index(histogram)
    while (highest - lowest > 1 and dump_cnt < dump) :
        histogram[high_i] -= 1
        histogram[low_i] += 1
        dump_cnt += 1
        highest, high_i, lowest, low_i = min_max_index(histogram)
    
    print(f"#{test_case} {highest - lowest}")