triangle = list(map(int, input().split()))

while (sum(triangle) != 0) :
    triangle.sort()
    if triangle[0]**2 + triangle[1]**2 == triangle[2]**2 :
        print('right')
    else :
        print('wrong')
    triangle = list(map(int, input().split()))