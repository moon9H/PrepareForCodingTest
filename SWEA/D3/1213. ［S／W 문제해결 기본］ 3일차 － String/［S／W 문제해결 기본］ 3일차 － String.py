T = 10
for test_case in range(1, T + 1):
    test_num = int(input())
    search = input()
    text = input()
    string_cnt = text.count(search)
    print(f"#{test_case} {string_cnt}")