from itertools import permutations
T = int(input())
for test_case in range(1, T + 1):
    N = int(input())
    minimum_cost = float('inf')
    battery_map = [list(map(int, input().split())) for _ in range(N)]
    area_num = [i for i in range(2, N+1)]
    perm_list =  list(permutations(area_num))
    for i in perm_list :
        possible_path = 0
        for j in range(len(i) - 1) :
            possible_path += battery_map[i[j] - 1][i[j+1] - 1]
        possible_path += battery_map[0][i[0] - 1] + battery_map[i[-1] - 1][0]
        minimum_cost = possible_path if possible_path < minimum_cost else minimum_cost
    print(f"#{test_case} {minimum_cost}")