M = 1234567891
r = 31
hash = 0
length = int(input())
s = input()

for i in range(length) :
    hash += (ord(s[i]) - 96) * r**i

print(hash % M)
