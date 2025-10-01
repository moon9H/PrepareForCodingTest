T = int(input())
for test_case in range(1, T + 1):
    arr_len, add_cnt, final_index = map(int, input().split())
    arr = list(map(int, input().split()))
    for _ in range(add_cnt) :
        index, value = map(int, input().split())
        arr.insert(index, value)
    print("#%d"%test_case, arr[final_index])