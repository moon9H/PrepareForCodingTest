from collections import deque

def bfs_shortest_path(grid, start, goal):
    # 방향 벡터 (상, 하, 좌, 우)
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    
    # 행렬의 크기
    rows, cols = len(grid), len(grid[0])
    
    # BFS를 위한 큐 초기화
    queue = deque([(start, 0)])  # (현재 위치, 현재까지의 거리)
    visited = set()  # 방문한 위치를 저장하는 집합
    visited.add(start)
    
    while queue:
        (current, distance) = queue.popleft()
        
        # 현재 위치가 목표 지점이면 거리 반환
        if current == goal:
            return distance
        
        # 상하좌우로 이동
        for direction in directions:
            next_row = current[0] + direction[0]
            next_col = current[1] + direction[1]
            next_pos = (next_row, next_col)
            
            # 다음 위치가 유효한 위치인지 확인
            if (0 <= next_row < rows and
                0 <= next_col < cols and
                next_pos not in visited and
                grid[next_row][next_col] == 1):  # 이동 가능한 위치인지 확인
                
                visited.add(next_pos)
                queue.append((next_pos, distance + 1))
    
    # 목표 지점에 도달할 수 없는 경우
    return -1

def numIntoPos(num) :
    if num == 0 :
        return (3, 1)
    else :
        if num == 1 :
            return (0, 0)
        elif num == 2 :
            return (0, 1)
        elif num == 3 :
            return (0, 2)
        elif num == 4 :
            return (1, 0)
        elif num == 5 :
            return (1, 1)
        elif num == 6 :
            return (1, 2)
        elif num == 7 :
            return (2, 0)
        elif num == 8 :
            return (2, 1)
        elif num == 9 :
            return (2, 2)


def solution(numbers, hand):
    answer = ''
    hand_use = []
    left_hand_pos = (3, 0)
    right_hand_pos = (3, 2)
    hand_pos = [[1] * 3] * 4
    for num in numbers :
        press_pos = numIntoPos(num)
        if num in {1, 4, 7} :
            hand_use.append('L')
            left_hand_pos = press_pos
        elif num in {3, 6, 9} :
            hand_use.append('R')
            right_hand_pos = press_pos
        elif num in {2, 5, 8, 0} :
            left_distance = bfs_shortest_path(hand_pos, left_hand_pos, press_pos)
            right_distance = bfs_shortest_path(hand_pos, right_hand_pos, press_pos)
            if left_distance == right_distance :
                if hand == 'right' :
                    hand_use.append('R')
                    right_hand_pos = press_pos
                elif hand == 'left' :
                    hand_use.append('L')
                    left_hand_pos = press_pos
            elif left_distance > right_distance :
                hand_use.append('R')
                right_hand_pos = press_pos
            else :
                hand_use.append('L')
                left_hand_pos = press_pos
    
    answer = ''.join(hand_use)
    return answer
