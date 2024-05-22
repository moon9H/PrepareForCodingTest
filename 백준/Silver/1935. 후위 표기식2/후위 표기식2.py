import sys
input = sys.stdin.readline

stack = []

def alphaToInt(alphabet) :
    if isinstance(alphabet, int):
        return alphabet
    else :    
        return ord(alphabet) - 65

def calPostfixExpr(expr) :
    for i in expr :
        if i in '*+/-' :
            oper2 = float(stack.pop())
            oper1 = float(stack.pop())
            if i == '*' :
                stack.append(oper1 * oper2)
            elif i == '+' :
                stack.append(oper1 + oper2)
            elif i == '/' :
                stack.append(oper1 / oper2)
            elif i == '-' :
                stack.append(oper1 - oper2)
            
        else :
            stack.append(i)

num_operands = int(input())
operand_list = [0] * num_operands

expression = list(map(str,input().rstrip()))

for i in range(num_operands) :
    operand_list[i] = int(input())

for i in range(len(expression)) :
    if expression[i] in '*+/-' :
        continue
    else :
        expression[i] = str(operand_list[alphaToInt(expression[i])])

calPostfixExpr(expression)            

print('%.2f' %stack[0])