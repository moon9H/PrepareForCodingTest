import sys
input = sys.stdin.readline

octal = input().rstrip()

def decimalToBinary(decimal) :
    binary_list = []
    i = int(decimal)
    while (i) :
        binary_list.append(str(i % 2))
        i //= 2
    binary_list.reverse()
    binary_num = ''.join(binary_list)
    
    return binary_num

def OctalToBinary(octal_num) :
    binary_list = []
    for i in octal :
        first_bin = decimalToBinary(i)
        while len(first_bin) < 3 :
            first_bin = '0'+first_bin
        binary_list.append(first_bin)
    
    return ''.join(binary_list)

binary = OctalToBinary(octal)

idx = 0

while idx < len(binary) and binary[idx] != '1':
    idx += 1

if idx == len(binary) :
    print(0)
else :
    print(binary[idx:len(binary)])