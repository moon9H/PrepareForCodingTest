def pow(a, b) :
    if b == 1 :
        return a
    else :
        return a * pow(a, b - 1)
for test_case in range(1, 11):
    test_num = int(input())
    N, M = map(int, input().split())
    print("#%d"%test_num, pow(N, M))