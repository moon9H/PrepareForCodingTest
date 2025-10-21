for tc in range(1, 11):
    N = int(input().strip())
    board = [input().strip() for _ in range(8)]             # 8줄
    cols  = [''.join(col) for col in zip(*board)]           # 전치

    def count_palins(lines, L):
        cnt = 0
        for s in lines:
            for i in range(8 - L + 1):
                seg = s[i:i+L]
                if seg == seg[::-1]:
                    cnt += 1
        return cnt

    ans = count_palins(board, N) + count_palins(cols, N)
    print(f"#{tc} {ans}")