T = int(input())
for test_case in range(1, T + 1):
    apply_cnt = int(input())
    apply_list = []
    timeTable = [0 for _ in range(25)]
    possible_truck = 0
    for _ in range(apply_cnt) :
        application = list(map(int, input().split()))
        apply_list.append(application)
    apply_list.sort(key=lambda x:x[1])
    for i in apply_list :
        if sum(timeTable[i[0] : i[1]]) == 0 :
            possible_truck += 1
            timeTable[i[0] : i[1]] = [1 for _ in range(i[0], i[1])]
    print(f"#{test_case} {possible_truck}")