def findPalindrome(puzzle, palinLen) :
    wordLen = len(puzzle[0])
    for w in puzzle :
        for i in range(wordLen - palinLen + 1) :
            word = w[i : i + palinLen]
            if word == word[::-1] :
                return word
    return False
                

T = int(input())
for test_case in range(1, T + 1):
    N, M = map(int, input().split())
    wordPuzzle = [input() for _ in range(N)]
    colPuzzle = [''.join(x) for x in zip(*wordPuzzle)]

    ans = findPalindrome(wordPuzzle, M) if findPalindrome(wordPuzzle, M) else findPalindrome(colPuzzle, M)
    print(f"#{test_case} {ans}")