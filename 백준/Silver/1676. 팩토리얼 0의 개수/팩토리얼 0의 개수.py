n = int(input())
facto_n = 1
for i in range(1, n + 1) :
    facto_n *= i

fact_list = list(map(int, str(facto_n)[::-1]))
zero_cnt = 0
while(fact_list[zero_cnt] == 0) :
    zero_cnt += 1
print(zero_cnt)