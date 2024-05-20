data_list = list(input())
data_num = len(data_list)
split_num = 0                   #답을 저장하는 변수
left_num = 0                    #여는 괄호의 수

for i in range(data_num):
    if i == 0:                  #문제 조건에 의해 처음 원소는 무조건 '(' 일 것임.
        left_num += 1
    else:
        cur_data = data_list[i]
        if cur_data == '(':     #여는 괄호이면 비교할 필요 없음
            left_num += 1
        else:                   #닫는 괄호인 경우
            if data_list[i - 1] == '(':   #레이저를 나타냄; 이전 원소가 여는 괄호  
                left_num -= 1
                split_num += left_num     #left_num만큼 잘리게 됨.
            else:                         #쇠막대기의 끝을 나타냄.
                split_num += 1  
                left_num -= 1
print(split_num)