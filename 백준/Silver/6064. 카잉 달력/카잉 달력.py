def calculate(M,N,x,y):  
    k=x
    while k<=M*N:
        if (k-x)%M==0 and (k-y)%N==0:
            return k
        k+=M
    return -1                
        
    

T=int(input())
for _ in range(T):
    M,N,x,y=list(map(int,input().split()))
    print(calculate(M,N,x,y))