s = input()

while (s != '0') :
    s_reverse = s[::-1]
    if s == s_reverse :
        print('yes')
    else :
        print('no')
    s = input()