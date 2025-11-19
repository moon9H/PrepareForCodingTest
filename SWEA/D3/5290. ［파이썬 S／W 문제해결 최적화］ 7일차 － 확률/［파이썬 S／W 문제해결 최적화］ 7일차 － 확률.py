T = int(input())
for test_case in range(1, T + 1):
    N = int(input())
    mom = 9 * pow(10, N - 1)
    son = 9
    for i in range(N - 1) :
        son *= (9 - i)
    
    print(f"#{test_case} %.5f"%(son / mom))