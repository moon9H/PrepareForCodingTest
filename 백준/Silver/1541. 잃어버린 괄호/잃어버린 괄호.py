import sys
input = sys.stdin.readline

expression = input().strip()

operand = ''
minus_stack = 0
ans = 0
minusOn = False
for i in expression :
    if i.isdigit() :
        operand += i
    else :
        if i == '+' :
            if minusOn :
                minus_stack += int(operand)
            else :
                ans += int(operand)
            operand = ''
        elif i == '-' :
            if minusOn :
                ans -= minus_stack
                minus_stack = 0
                minus_stack += int(operand)

            else :
                ans += int(operand)
                minusOn = True
            operand = ''

if minusOn :
    minus_stack += int(operand)
    ans -= minus_stack
else :
    ans += int(operand)
print(ans)