T = int(input())

for test_case in range(1, T + 1):
    aWord, bWord = map(int, input().split())
    aBook = []
    bBook = []
    for _ in range(aWord) :
        aBook.append(input())
    for _ in range(bWord) :
        bBook.append(input())
    print("#%d"%test_case, len(set(aBook)&set(bBook)))