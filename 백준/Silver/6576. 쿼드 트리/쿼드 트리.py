import sys
import math
input = sys.stdin.readline

def get_next(quadtree) :
    get_next.idx += 1
    return quadtree[get_next.idx - 1]

def decompress(quadtree, y, x, size, decompressed) :
    head = get_next(quadtree)
    if head in 'BW' :
        for i in range(y, y + size) :
            decompressed[i][x : x + size] = [(head=='B' * 1)] * size
    else :
        half = size // 2
        decompress(quadtree, y, x, half, decompressed)
        decompress(quadtree, y, x + half, half, decompressed)
        decompress(quadtree, y + half, x, half, decompressed)
        decompress(quadtree, y + half, x + half, half, decompressed)


n = int(input())
quadtree = input()
decompressed = [[0] * n for _ in range(n)]
get_next.idx = 0

decompress(quadtree, 0, 0, n, decompressed)

print("#define quadtree_width", n)
print("#define quadtree_height",n)
print('static char quadtree_bits[] = {')

for i in range(len(decompressed)):
    sum = 0
    for j in range(len(decompressed[i])) :
        if j % 8 == 0:
            if j != 0 :
                hexadecimal_number = format(sum, '02x')  # 'f0'
                print('0x' + hexadecimal_number,end=',')
            sum = 0
            if decompressed[i][j] :
                sum += pow(2, j % 8)
        else :
            if decompressed[i][j] :
                sum += pow(2, j % 8)
        # print(sum, end=" ")
        # print(0 if j == False else 1 ,end=' ')
    hexadecimal_number = format(sum, '02x')  # 'f0'
    print('0x' + hexadecimal_number,end=',')
    print()
    
print('};')