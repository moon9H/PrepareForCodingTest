croatia = ['c=', 'c-', 'dz=', 'd-', 'lj', 'nj', 's=', 'z=']
s = input()

for letter in croatia :
    if letter in s :
        s = s.replace(letter,'*')

print(len(s))