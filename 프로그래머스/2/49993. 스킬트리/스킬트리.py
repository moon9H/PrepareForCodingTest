def solution(skill, skill_trees):
    answer = 0
    for tree in skill_trees :
        i = 0
        allowed = True
        for j in range(len(tree)) :
            if tree[j] in skill :
                if tree[j] == skill[i] :
                    i += 1
                else :
                    allowed = False
                    break
        if allowed :
            if j + 1 == len(tree) :
                answer += 1
    
    return answer