import sys
input = sys.stdin.readline
import math

def squaredExpr(n : int) :
    if math.sqrt(n).is_integer() :
        return 1
    
    for i in range(int(math.sqrt(n)), 0, -1) :
        if math.sqrt(n - pow(i, 2)).is_integer() :
            return 2
    
    for i in range(int(math.sqrt(n)), 0, -1) :
        for j in range(int(math.sqrt(n - pow(i, 2))), 0, -1) :
            if math.sqrt(n - pow(i, 2) - pow(j, 2)).is_integer() :
                return 3
    
    return 4
    
N = int(input())
print(squaredExpr(N))