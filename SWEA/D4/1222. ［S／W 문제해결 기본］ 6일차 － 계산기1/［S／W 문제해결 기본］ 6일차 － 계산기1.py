for test_case in range(1, 11):
    len = int(input())
    expression = input()
    postfix = []
    operator = []
    for i in expression :
        if i.isdigit() :
            postfix.append(i)
        else :
            if not operator :
                operator.append(i)
            else :
                while operator :
                    postfix.append(operator.pop())
                operator.append(i)
    while operator : 
        postfix.append(operator.pop())
    
    sum = 0
    for i in postfix :
        if i.isdigit() :
            sum += int(i)

    print(f"#{test_case} {sum}")