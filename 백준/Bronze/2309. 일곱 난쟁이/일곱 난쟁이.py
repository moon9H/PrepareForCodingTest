import sys
input = sys.stdin.readline

dwarfs = []

for i in range(9) :
    dwarfs.append(int(input()))

dwarfs.sort()

for i in range(8) :
    dwarf_sum = 0
    for j in range(i + 1, 9) :
        dwarf_sum = sum(dwarfs)
        dwarf_sum = dwarf_sum - dwarfs[i] - dwarfs[j]
        if dwarf_sum == 100 :
            dwarfs.pop(j)
            dwarfs.pop(i)
            break
    if dwarf_sum == 100 :
        break
        
for height in dwarfs :
    print(height)