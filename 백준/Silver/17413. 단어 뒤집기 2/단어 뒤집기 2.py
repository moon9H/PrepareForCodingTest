import sys
import re

input = sys.stdin.readline

S = input().rstrip()

pattern = re.compile(r' |[^<> ]+|<[^<>]+>')

tokens = pattern.findall(S)

for token in tokens :
    if '<' in token :
        print(token,end='')
    else :
        w_r = list(token)
        w_r.reverse()
        print(''.join(w_r),end='')

print()