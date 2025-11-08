T = int(input())
for test_case in range(1, T + 1):
    N, hexa = input().split()
    N = int(N)
    binary = bin(int(hexa, 16))[2 : ].zfill(N * 4)
    print(f"#{test_case} {binary}")