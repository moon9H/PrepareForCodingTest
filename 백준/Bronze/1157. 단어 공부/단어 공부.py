alphabet = [0 for _ in range(26)]
maxUsed = float('-inf')
maxUsedAlphabet = float('-inf')
s = input()

for i in s :
    uni = ord(i)
    if 65 <= uni <=90 :
        alphabet[uni - 65] += 1
    elif 97 <= uni <= 122 :
        alphabet[uni - 97] += 1

for i in range(26) :
    if alphabet[i] > maxUsed :
        maxUsed = alphabet[i]
        maxUsedAlphabet = i

maxNumCnt = 0
for i in alphabet :
    if i == maxUsed :
        maxNumCnt += 1

if maxNumCnt >= 2 :
    print('?')
else :
    print(chr(maxUsedAlphabet + 65))