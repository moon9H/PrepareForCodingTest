T = int(input())
for test_case in range(1, T + 1):
    aGroup, bGroup = map(int, input().split())
    a = [input() for _ in range(aGroup)]
    b = [input() for _ in range(bGroup)]
    prefix = 0
    for possible_prefix in b :
        for word in a :
            if word.find(possible_prefix) == 0 :
                prefix += 1
                break
    
    print(f"#{test_case} {prefix}")