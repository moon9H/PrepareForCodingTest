def solution(today, terms, privacies):
    answer = []
    clauses = {}
    IsExpired = [0] * len(privacies)
    today_year, today_month, today_day = map(int, today.split('.'))
    for clause in terms :
        name, duration = clause.split()
        clauses[name] = int(duration)
    
    index = 0
    for case in privacies :
        start_date, kind = case.split()
        start_year, start_month, start_day = map(int,start_date.split('.'))
        duration_inDays = clauses[kind] * 28
        i = 0
        while (i < duration_inDays - 1) :
            start_day += 1
            if start_day == 29 :
                start_day = 1
                start_month += 1
                if start_month == 13 :
                    start_month = 1
                    start_year += 1
            i += 1
        if start_year < today_year :
            IsExpired[index] = 1
        elif start_year == today_year :
            if start_month < today_month :
                IsExpired[index] = 1
            elif start_month == today_month :
                if start_day < today_day :
                    IsExpired[index] = 1
        index += 1
            
    for i in range (len(IsExpired)) :
        if IsExpired[i] :
            answer.append(i + 1)
    return answer