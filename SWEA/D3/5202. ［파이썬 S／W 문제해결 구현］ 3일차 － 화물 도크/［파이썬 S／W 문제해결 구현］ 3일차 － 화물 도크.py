T = int(input())
for test_case in range(1, T + 1):
    apply_cnt = int(input())
    apply_list = []
    possible_truck = 1
    for _ in range(apply_cnt) :
        application = list(map(int, input().split()))
        apply_list.append(application)
    apply_list.sort(key=lambda x:x[1])
    before_end = apply_list[0][1]
    for i in range(1, apply_cnt) :
        if before_end <= apply_list[i][0] :
            possible_truck += 1
            before_end = apply_list[i][1]
    print(f"#{test_case} {possible_truck}")