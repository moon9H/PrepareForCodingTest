def solution(array, commands):
    answer = []
    for i in range (len(commands)) :
        work_array = array[commands[i][0] - 1 : commands[i][1]]
        work_array.sort()
        answer.append(work_array[commands[i][2] - 1])
        
    return answer