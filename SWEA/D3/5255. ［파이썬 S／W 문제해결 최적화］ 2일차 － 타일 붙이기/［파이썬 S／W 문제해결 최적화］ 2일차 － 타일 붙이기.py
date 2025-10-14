cache = [-1 for _ in range(31)]
def tiling(width) :
    global cache
    if (0 <= width <= 1) : return 1
    elif width < 0 : return 0
    if (cache[width] != -1) : return cache[width]
    cache[width] = tiling(width - 1) + 2*tiling(width - 2) + tiling(width - 3)
    return cache[width]

T = int(input())
for test_case in range(1, T + 1):
    N = int(input())
    print(f"#{test_case} {tiling(N)}")