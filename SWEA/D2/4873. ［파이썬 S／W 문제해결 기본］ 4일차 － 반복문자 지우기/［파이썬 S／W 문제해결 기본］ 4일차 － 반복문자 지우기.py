T = int(input())
for test_case in range(1, T + 1):
    s = input()
    isRepeated = True
    while isRepeated :
        origin_len = len(s)
        for i in range(len(s) - 1) :
            if s[i] == s[i + 1] :
                s = s[0 : i] + s[i + 2 : len(s)]
                break
        if len(s) == origin_len :
            isRepeated = False
    print("#%d"%test_case, len(s))