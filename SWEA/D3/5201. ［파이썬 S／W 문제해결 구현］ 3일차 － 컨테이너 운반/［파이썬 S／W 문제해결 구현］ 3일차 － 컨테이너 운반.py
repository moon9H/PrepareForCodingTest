T = int(input())
for test_case in range(1, T + 1):
    containerCnt, truckCnt = map(int, input().split())
    weight = list(map(int, input().split()))
    capacity = list(map(int, input().split()))
    weight.sort()
    capacity.sort()
    maximumWeight = 0
    i = containerCnt - 1
    j = truckCnt - 1
    while weight and capacity :
        if capacity[j] >= weight[i] :
            capacity.pop()
            maximumWeight += weight.pop()
            j -= 1
            i -= 1
        else :
            weight.pop()
            i -= 1
    print("#%d"%test_case, maximumWeight)