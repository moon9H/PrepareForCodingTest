T = int(input())
for test_case in range(1, T + 1):
    command = input()
    bracket = []
    for letter in command :
        if letter in '({':
            bracket.append(letter)
        elif letter in ')}' :
            if not bracket :
                bracket.append(letter)
                break
            left = bracket.pop()
            if letter == ')' and left != '(':
                bracket.append(letter)
                break
            elif letter == '}' and left != '{':
                bracket.append(letter)
                break
        else :
            continue
    ans = 0 if bracket else 1
    print(f"#{test_case} {ans}")