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
    
    operand = []
    for i in postfix :
        if i.isdigit() :
            operand.append(int(i))
        else :
            A = operand.pop()
            B = operand.pop()
            operand.append(A + B)

    print(f"#{test_case} {operand[0]}")