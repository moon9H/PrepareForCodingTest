def solution(msg):
    answer = []
    alphabet_dict = {}
    alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
    added_num = 27
    for i in range(len(alphabet)):
        alphabet_dict[alphabet[i]] = i + 1
    index = 0
    while (index < len(msg)) :
        i = 1
        while msg[index : index + i] in alphabet_dict:
            i += 1
            if index + i > len(msg) :
                break
        if i != 1 :
            answer.append(alphabet_dict[msg[index : index + i - 1]])
        alphabet_dict[msg[index : index + i]] = added_num
        added_num += 1
        index += i - 1
        
    return answer