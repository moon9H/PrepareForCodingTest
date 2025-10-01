T = int(input())
for test_case in range(1, T + 1):
    alphabet_cnt_in1 = [False for _ in range(26)]
    alphabet_true_in2 = [0 for _ in range(26)]
    mostAlphabet = float('-inf')
    str1 = input()
    str2 = input()
    for letter in str1 :
        alphabet_cnt_in1[ord(letter) - 65] = True
    for letter in str2 :
        alphabet_true_in2[ord(letter) - 65] += 1
    for i in range(26) :
        if alphabet_cnt_in1[i] and alphabet_true_in2[i] :
            if alphabet_true_in2[i] >= mostAlphabet :
                mostAlphabet = alphabet_true_in2[i]
    print("#%d"%test_case, mostAlphabet)