codeInterpret = {'0001101' : 0, '0011001' : 1, '0010011' : 2, '0111101': 3, '0100011': 4, 
                 '0110001': 5, '0101111' : 6, '0111011' : 7, '0110111' : 8, '0001011' : 9}

T = int(input())
for test_case in range(1, T + 1):
    N, M = map(int, input().split())
    screenshot = [input() for _ in range(N)]
    code = ''
    for i in screenshot :
        first_1 = 0
        last_1 = 0
        for j in range(M) :
            if i[j] == '1' and not first_1 :
                first_1 = j
            elif i[j] == '1' and first_1 :
                last_1 = j
        if first_1 != last_1 :
            code = i[first_1 : last_1 + 1]
            break
    front_zero_padd = 56 - (last_1 - first_1 + 1)
    code = '0'*front_zero_padd + code
    code_sum = 0
    sum = 0
    for i in range(8) :
        interpret = codeInterpret[code[i * 7 :i * 7 + 7]]
        sum += interpret
        if i % 2 == 0 :
            code_sum += interpret * 3
        else :
            code_sum += interpret
    result = 0 if code_sum % 10 != 0 else sum
    
    print(f"#{test_case} {result}")