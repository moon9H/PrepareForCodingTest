a, b = map(int, input().split())

def least_common_mul(a, b) :
    divisor = []
    i = 2
    while(i <= a and i <= b) :
        if (a % i == 0) and (b % i == 0) :
            divisor.append(i)
            a = a//i
            b = b//i
        else :
            i += 1
    divisor.append(a)
    divisor.append(b)
    least_common_mul = 1
    for i in divisor :
        least_common_mul *= i
    
    return least_common_mul

def greatest_common_divisor (a, b):
    a_divisor = []
    b_divisor = []
    i = 2
    while (i <= a) :
        if a % i == 0 :
            a_divisor.append(i)
            a = a // i
        else :
            i += 1
    a_divisor.append(a)
    while (i <= b) :
        if b % i == 0 :
            b_divisor.append(i)
            b = b // i
        else :
            i += 1
    b_divisor.append(b)
    greatest_common_div = 1
    common_list = list(set.intersection(set(a_divisor), set(b_divisor)))
    for i in common_list :
        i_in_a, i_in_b = a_divisor.count(i), b_divisor.count(i)
        greatest_common_div *= i* (i_in_a if i_in_a < i_in_b else i_in_b)
    
    return greatest_common_div

print(a * b // least_common_mul(a, b))
print(least_common_mul(a, b))