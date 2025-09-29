N = int(input())
tshirts = list(map(int, input().split()))
T, P = map(int, input().split())
buy = 0

for i in tshirts :
    buy += i // T
    if i % T != 0 :
        buy += 1

print(buy)
print(N // P, N % P)