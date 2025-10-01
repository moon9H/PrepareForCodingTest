T = int(input())
for test_case in range(1, T + 1):
    aSize, bSize = map(int, input().split())
    a = list(map(int, input().split()))
    b = list(map(int, input().split()))
    print("#%d"%test_case, len(set(a).intersection(set(b))))