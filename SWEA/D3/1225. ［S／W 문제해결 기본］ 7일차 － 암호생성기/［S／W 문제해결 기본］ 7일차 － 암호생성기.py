from collections import deque as dq
T = 10
for test_case in range(1, T + 1):
    test_num = int(input())
    pw = dq(map(int, input().split()))
    i = 1
    while(pw[-1] > 0) :
        first = pw.popleft()
        i = 1 if i > 5 else i
        first -= i
        i += 1
        pw.append(first)
    pw[-1] = 0
    print(f"#{test_case}", *pw)