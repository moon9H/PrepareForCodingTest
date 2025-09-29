T = int(input())

for _ in range(T) :
    floor = int(input())
    n = int(input())
    first = [i for i in range(1, n + 1)]
    for _ in range((floor + 1) // 2) : 
        second = [sum(first[0:i + 1]) for i in range(n)]
        first  = [sum(second[0:i + 1]) for i in range(n)]
    if floor % 2 == 0 :
        print(first[n - 1])
    else :
        print(second[n - 1])