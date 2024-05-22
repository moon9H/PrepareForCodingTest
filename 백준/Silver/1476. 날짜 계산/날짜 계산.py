import sys
input = sys.stdin.readline

MAX_EARTH = 16
MAX_SUN = 29
MAX_MOON = 20

E, S, M = map(int,input().split())

year = '1 1 1'
given_year = ' '.join([str(E),str(S),str(M)])

earth_year = 1
E, S, M = 1, 1, 1
while year != given_year :
    earth_year += 1
    E = (E + 1) % MAX_EARTH
    S = (S + 1) % MAX_SUN
    M = (M + 1) % MAX_MOON

    if E == 0 :
        E = 1
    if S == 0 :
        S = 1
    if M == 0 :
        M = 1

    year = ' '.join([str(E),str(S),str(M)])

print(earth_year)