num_list = []
digitInList = False
digitIndex = 0
digit = 0
next_num = 0

for i in range(3) :
    num = input()
    if num.isdigit() :
        digitInList = True
        digitIndex = i
        digit = int(num)
    num_list.append(num)

if digitInList :
    next_num = digit + (3 - digitIndex)
    if (next_num % 3) == 0 and (next_num % 5) == 0 :
        print('FizzBuzz')
    elif (next_num % 3) == 0 :
        print('Fizz')
    elif next_num % 5 == 0 :
        print('Buzz')
    else  :
        print(next_num)