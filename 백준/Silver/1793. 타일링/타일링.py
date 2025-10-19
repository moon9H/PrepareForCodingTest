import sys
input = sys.stdin.readline
t = [1] * 251

w = 2

while (w < 251) :
    t[w] = 2 * t[w - 2] + t[w - 1]
    w += 1

while True :
    try : print(t[int(input())])
    except : break