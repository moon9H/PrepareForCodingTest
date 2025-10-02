for test_case in range(1, 11):
    length = int(input())
    expression = input()
    result = 0
    opeartor = []
    postfix = []
    for i in expression :
        if 0 <= ord(i) - 48 <= 9 :
            postfix.append(i)
        else :
            if not opeartor :
                opeartor.append(i)
            else :
                if i == '*' :
                    while opeartor and opeartor[-1] != "+":
                        postfix.append(opeartor.pop())
                    opeartor.append(i)
                else :
                    while opeartor :
                        postfix.append(opeartor.pop())
                    opeartor.append(i)
    while opeartor :
        postfix.append(opeartor.pop())
    operand = []
    for i in postfix :
        if 0 <= ord(i) - 48 <= 9 :
            operand.append(i)
        else :
            if i == '*' :
                A = int(operand.pop())
                B = int(operand.pop())
                operand.append(A*B)
                result += A*B
            else :
                A = int(operand.pop())
                B = int(operand.pop())
                operand.append(A + B)
    print("#%d"%test_case, operand[0])