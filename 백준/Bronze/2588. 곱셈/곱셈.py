a = int(input())
b = int(input())

num_3 = a * (b%10)
num_4 = a * ((b//10)%10)
num_5 = a * (b//100)

print(num_3)
print(num_4)
print(num_5)
print(a*b)