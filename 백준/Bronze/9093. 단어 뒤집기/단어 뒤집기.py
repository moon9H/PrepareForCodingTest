import sys
input = sys.stdin.readline

num_sentence = int(input())
answer = []

for _ in range(num_sentence):
    sentence = list(map(str,input().split()))
    reverse_sentence = []
    for i in sentence :
        j = list(i)
        j.reverse()
        reverse_sentence.append(''.join(j))
    string_form = ' '.join(reverse_sentence)
    answer.append(string_form)

for i in answer :
    print(i)