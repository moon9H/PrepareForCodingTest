def checkPalindrome(puzzle, palinLen) :
    palinCnt = 0
    for w in puzzle :
        wordLen = len(w)
        for i in range(wordLen - palinLen + 1) :
            word = w[i : i + palinLen]
            if word == word[::-1] :
                palinCnt += 1
    
    return palinCnt

T = 10
for test_case in range(1, T + 1):
    tc = int(input())
    wordPuzzle = [input() for _ in range(100)]
    revWordPuzzle = [list(x) for x in zip(*wordPuzzle)]

    hor_longestP, vert_longestP = 100, 100
    hor_p, vert_p = checkPalindrome(wordPuzzle, hor_longestP), checkPalindrome(revWordPuzzle, vert_longestP)
    while (hor_p == 0) :
        hor_longestP -= 1
        hor_p = checkPalindrome(wordPuzzle, hor_longestP)
    
    while (vert_p == 0) :
        vert_longestP -= 1
        vert_p = checkPalindrome(revWordPuzzle, vert_longestP)
    
    longestP = hor_longestP if hor_longestP > vert_longestP else vert_longestP
    print(f"#{tc} {longestP}")