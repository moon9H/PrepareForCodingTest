def checkPalindrome(puzzle, palinLen) :
    for w in puzzle :
        wordLen = len(w)
        for i in range(wordLen - palinLen + 1) :
            word = w[i : i + palinLen]
            if word == word[::-1] :
                return True
    
    return False

T = 10
for test_case in range(1, T + 1):
    tc = int(input())
    wordPuzzle = [input() for _ in range(100)]
    revWordPuzzle = [list(x) for x in zip(*wordPuzzle)]
    ans = 1
    for pal_len in range(100, 0, -1):
        if checkPalindrome(wordPuzzle, pal_len) or checkPalindrome(revWordPuzzle, pal_len):
            ans = pal_len
            break

    print(f"#{tc} {ans}")