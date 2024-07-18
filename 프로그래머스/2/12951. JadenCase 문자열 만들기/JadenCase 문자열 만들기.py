def solution(s):
    sen = s.split(" ")
    answer = []
    for w in sen:
        if w:
            if w[0].isdigit():
                word = w[0] + w[1:].lower()
            else:
                word = w.capitalize()
        else:
            word = w

        answer.append(word)

    return ' '.join(answer)