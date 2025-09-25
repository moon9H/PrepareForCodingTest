hour, min = map(int, input().split())
cookingTime = int(input())
cookingHour, cookingMin = cookingTime//60 , cookingTime%60
finishHour = hour + cookingHour + ((min + cookingMin) // 60)
finishMin = (min + cookingMin) % 60

if (finishHour >= 24) :
    finishHour -= 24

print(finishHour, finishMin)
    

