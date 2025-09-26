N = int(input())
group_word = 0

for _ in range(N) :
    alphabet = [0 for _ in range(26)]
    word = input()
    isGroup = 1
    for i in range(len(word)) :
        uni = ord(word[i])
        if alphabet[uni - 97] == 0 :
            alphabet[uni - 97] += 1
        elif alphabet[uni - 97] == 1 and (word[i] != word[i-1]) :
            isGroup = 0
    if isGroup : group_word += 1

print(group_word)