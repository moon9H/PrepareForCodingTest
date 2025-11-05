lang = {"ZRO" : 0, "ONE" : 1, "TWO" : 2, 
        "THR" : 3, "FOR" : 4, "FIV" : 5, 
        "SIX" : 6, "SVN" : 7, "EGT" : 8, "NIN" : 9}
T = int(input())
for test_case in range(1, T + 1):
    _, num = input().split()
    num = int(num)
    interprete = list(input().split())
    for i in range(num) : 
        interprete[i] = lang[interprete[i]]
    interprete.sort()
    print(f"#{test_case}")
    key = list(lang.keys())
    for i in interprete : print(key[i], end=" ")
    print()