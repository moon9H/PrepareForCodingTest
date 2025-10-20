for test_case in range(1, 10 + 1):
    check_len = int(input())
    palindrome_cnt = 0
    word_puzzle = []
    for _ in range(8) :
        line = list(input())
        word_puzzle.append(line)
    
    for w in word_puzzle :
        for j in range(0, 8 - check_len + 1) :
            word = ''.join(w[j : j + check_len])
            if word == word[::-1] :
                palindrome_cnt += 1
    
    for i in range(8) :
        w = []
        for j in range(8) :
            w.append(word_puzzle[j][i])
        for j in range(0, 8 - check_len + 1) :
            word = ''.join(w[j : j + check_len])
            if word == word[::-1] :
                palindrome_cnt += 1
    
    print(f"#{test_case} {palindrome_cnt}")