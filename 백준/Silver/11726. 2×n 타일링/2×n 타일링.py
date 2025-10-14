MOD = 10007
t = [1] * 1001
w = 2

while (w < 1001) :
    t[w] = (t[w -2] + t[w - 1]) % MOD
    w += 1

print(t[int(input())])