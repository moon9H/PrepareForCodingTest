import sys
input = sys.stdin.readline

T = int(input())
out = []
for _ in range(T):
    H, W, N = map(int, input().split())
    floor = (N - 1) % H + 1      # 1 ~ H
    room  = (N - 1) // H + 1     # 1 ~ W
    out.append(str(floor * 100 + room))

print("\n".join(out))