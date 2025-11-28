import sys
input = sys.stdin.readline

T = int(input())

for _ in range(T) :
    N = int(input())
    fashionItem = {}
    for _ in range(N) :
        item,kind = input().strip().split()
        if kind not in fashionItem :
            fashionItem[kind] = 1
        else :
            fashionItem[kind] += 1
    ans = 1
    for cnt in fashionItem.values() :
        ans *= (cnt + 1)
    
    print(ans - 1)