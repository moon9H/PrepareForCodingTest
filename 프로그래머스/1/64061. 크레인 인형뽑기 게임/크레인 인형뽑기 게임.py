def solution(board, moves):
    answer = 0
    bucket = []
    board_size = len(board)
    for line in moves :
        i = 0
        while i < board_size and board[i][line - 1] == 0:
            i += 1
        
        if i < board_size :
            if len(bucket) == 0 :
                bucket.append(board[i][line - 1])
            else :
                if bucket[-1] == board[i][line - 1] :
                    bucket.pop()
                    answer += 2
                else :
                    bucket.append(board[i][line - 1])
            board[i][line - 1] = 0

    return answer