T = int(input())

for _ in range(T) :
    R, S = map(str, input().split())
    R = int(R)
    P = ""
    for i in S :
        P += i*R
    print(P)