import sys
from collections import deque
input = sys.stdin.readline

dx = [-1, -2, -2, -1, 1, 2, 1, 2]
dy = [-2, -1, 1, 2, -2, -1, 2, 1]


def BFS(board, visit, start_x, start_y, end_x, end_y, board_size):
    dq = deque()
    dq.append((start_x, start_y))
    board[start_x][start_y] = 1
    visit[start_x][start_y] = True
    while dq:
        now = dq.popleft()
        
        if now[0] == end_x and now[1] == end_y :
            return board[now[0]][now[1]] - 1

        for i in range(8):
            x = now[0] + dx[i]
            y = now[1] + dy[i]
            if (x >= 0 and x < board_size and y >= 0 and y < board_size):
                if (not visit[x][y]):
                    visit[x][y] = True
                    board[x][y] = board[now[0]][now[1]] + 1
                    dq.append((x, y))
        
    return False

N = int(input())
answer_list = []

for i in range(N) :
    board_size = int(input())
    board = [[0] * board_size for _ in range(board_size)]
    visit = [[False] * board_size for _ in range(board_size)]
    start_x, start_y = map(int,input().split())
    end_x, end_y = map(int, input().split())
    answer_list.append(BFS(board, visit, start_x, start_y, end_x, end_y, board_size))

for i in answer_list :
    print(i)