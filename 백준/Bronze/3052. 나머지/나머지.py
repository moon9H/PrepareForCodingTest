remain = [0 for _ in range(42)]
ans = 0
for _ in range(10) :
    num = int(input())
    remain[num % 42] += 1

for i in remain :
    if i != 0 :
        ans += 1
print(ans)