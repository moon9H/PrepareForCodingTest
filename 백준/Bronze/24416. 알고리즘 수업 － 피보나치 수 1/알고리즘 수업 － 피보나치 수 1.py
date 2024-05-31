import sys
input = sys.stdin.readline

recur_cnt = 0
dp_cnt = 0

def recur_fibo(N) :
    global recur_cnt
    if (N == 1 or N == 2) :
        recur_cnt += 1
        return 1
    else :
        return recur_fibo(N - 1) +  recur_fibo (N - 2)

def dp_fibo(N) :
    global dp_cnt
    F = [0, 1, 1]
    if N == 0 :
        return F[0]
    elif N == 1 or N == 2:
        return F[1]
        
    for i in range(3, N + 1):
        dp_cnt += 1
        f_i = F[i - 1] + F[i - 2]
        F.append(f_i)
    
    return F[N]

n = int(input())

print(dp_fibo(n), dp_cnt)