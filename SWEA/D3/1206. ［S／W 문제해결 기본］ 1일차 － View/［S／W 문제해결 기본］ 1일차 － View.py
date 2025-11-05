T = 10
for test_case in range(1, T + 1):
    building_num = int(input())
    heights = list(map(int, input().split()))
    view_clear = 0
    for i in range(building_num) :
        if heights[i] == 0 : continue
        else :
            view = min(
                heights[i] - heights[i - 1],
                heights[i] - heights[i - 2],
                heights[i] - heights[i + 1],
                heights[i] - heights[i + 2]
            )
            possibleView = view if view > 0 else 0
            view_clear += possibleView

    print(f"#{test_case} {view_clear}")