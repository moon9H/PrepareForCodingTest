def solution(dartResult):
    answer = 0
    i = 0
    result_len = len(dartResult)
    while (i < result_len) :
        if dartResult[i].isdigit() :
            if dartResult[i + 1].isdigit() :
                score = int(dartResult[i]) * 10 + int(dartResult[i + 1])
                i += 2
            else :
                score = int(dartResult[i])
                i += 1
        else :
            bonus = dartResult[i]
            if bonus == 'S' :
                score = score ** 1
            elif bonus == 'D' :
                score = score ** 2
            elif bonus == 'T' :
                score = score ** 3
            
            i += 1

            if i >= result_len :
                answer += score
                before_score = score
                break
            else :
                if dartResult[i] == '*' :
                    if i == 2 :
                        score *= 2
                        answer += score
                        before_score = score
                    else :
                        answer += before_score
                        score *= 2
                        answer += score
                        before_score = score
                    i = i + 1
                elif dartResult[i] == '#' :
                    score = -score
                    answer += score
                    before_score = score
                    i = i + 1
                else :
                    answer += score
                    before_score = score
            
    return answer