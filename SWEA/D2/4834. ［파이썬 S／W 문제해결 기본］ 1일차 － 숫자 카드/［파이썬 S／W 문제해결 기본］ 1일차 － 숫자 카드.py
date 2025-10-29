T = int(input())
for test_case in range(1, T + 1):
    numCard = int(input())
    cards = input()
    cardList = [0 for _ in range(10)]
    cnt = float('-inf')
    max_num = 0
    for num in cards :
        cardList[ord(num) - 48] += 1
    for i in range(10) :
        if cardList[i] >= cnt :
            cnt = cardList[i]
            max_num = i
    print(f"#{test_case} {max_num} {cnt}")