import math
class parkingState :
    def __init__(self, num, time) :
        self.car_num = num
        self.stayed_time = 0
        self.in_time = time
        self.itsParked = True
    
    def comeAgain(self, time) :
        self.in_time = time
        self.itsParked = True
    
    def leaveParkinglot(self, time) :
        in_time_hr, in_time_min = map(int, self.in_time.split(':'))
        out_time_hr, out_time_min = map(int, time.split(':'))
        if out_time_hr == in_time_hr :
            self.stayed_time += out_time_min - in_time_min
        else :
            self.stayed_time += (out_time_hr - in_time_hr) * 60 + (out_time_min - in_time_min)
        self.itsParked = False
        
def calculate_fee(default_time, default_fee, per_time, per_fee, stayed_time) :
    if int(stayed_time) <= default_time :
        return default_fee
    else :
        return default_fee + math.ceil(((int(stayed_time) - default_time) / per_time)) * per_fee
        
def solution(fees, records):
    answer = []
    parked_list = {}
    for rec in records :
        time, car_num, InOrOut = rec.split(' ')
        if InOrOut == 'IN' :
            if car_num in parked_list :
                parked_list[car_num].comeAgain(time)
            else :
                parked_list[car_num] = parkingState(car_num, time)
        else :
            parked_list[car_num].leaveParkinglot(time)
    parked_list = dict(sorted(parked_list.items()))
    for i in parked_list :
        if parked_list[i].itsParked :
            parked_list[i].leaveParkinglot("23:59")
    for i in parked_list :
        answer.append(calculate_fee(fees[0], fees[1], fees[2], fees[3], parked_list[i].stayed_time))
        
    return answer