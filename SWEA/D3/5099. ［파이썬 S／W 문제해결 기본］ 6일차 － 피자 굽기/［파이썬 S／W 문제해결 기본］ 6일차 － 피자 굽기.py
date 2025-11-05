from collections import deque

T = int(input())
for test_case in range(1, T + 1):
    capacity, numPizza = map(int, input().split())
    pizza = list(map(int, input().split()))
    for i in range(numPizza) :
        pizza[i] = (pizza[i], i + 1)
    firepit = deque(pizza[0 : capacity])
    next_pizza = capacity

    while(len(firepit) > 1) :
        cheese, idx = firepit.popleft()
        cheese //= 2
        if cheese == 0 :
            if next_pizza < numPizza :
                firepit.append(pizza[next_pizza])
                next_pizza += 1
        else :
            firepit.append((cheese, idx))

    print(f"#{test_case} {firepit[0][1]}")