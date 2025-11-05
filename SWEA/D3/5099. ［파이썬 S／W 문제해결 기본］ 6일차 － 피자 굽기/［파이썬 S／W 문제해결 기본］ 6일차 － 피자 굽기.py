from collections import deque

T = int(input())
for test_case in range(1, T + 1):
    capacity, numPizza = map(int, input().split())
    cheeseTopping = list(map(int, input().split()))
    for i in range(numPizza) :
        cheeseTopping[i] = (cheeseTopping[i], i + 1)
    firepit = deque(cheeseTopping[0 : capacity])
    remain = numPizza - capacity
    
    while(len(firepit) > 1) :
        cheese, idx = firepit.popleft()
        cheese //= 2
        if cheese == 0 :
            if remain :
                firepit.append(cheeseTopping[numPizza - remain])
                remain -= 1
        else :
            firepit.append((cheese, idx))

    print(f"#{test_case} {firepit[0][1]}")