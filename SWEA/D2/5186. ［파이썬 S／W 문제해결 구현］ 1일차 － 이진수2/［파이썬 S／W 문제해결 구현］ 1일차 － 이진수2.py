T = int(input())
for test_case in range(1, T + 1):
    N = float(input())
    binary = []
    i = 1
    while(N != 0 and len(binary) < 13) :
        if N >= 2**-i :
            binary.append(1)
            N -= 2**-i
        else :
            binary.append(0)
        i += 1
    result = 'overflow' if len(binary) >= 13 else ''.join(map(str, binary))
    print(f"#{test_case} {result}")