T = int(input())
for test_case in range(1, T + 1):
    aGroup, bGroup = map(int, input().split())
    a = [input() for _ in range(aGroup)]
    b = [input() for _ in range(bGroup)]
    isPrefix = [0 for _ in range(bGroup)]
    for word in a :
        for j in range(bGroup) :
            if b[j] in word and not isPrefix[j]:
                if not word.find(b[j]) : isPrefix[j] += 1
    print(f"#{test_case} {sum(isPrefix)}")