T = int(input())
for test_case in range(1, T + 1):
    first = input()
    second = input()
    isIn = 0
    if first in second : isIn = 1
    else : isIn = 0
    print(f"#{test_case} {isIn}")