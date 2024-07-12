class Node :
    def __init__(self, name) :
        self.name = name
        self.in_degree = 0
        self.out_degree = 0
        self.giveList = {}
        self.receiveList = {}
    
    def makeList(self, name_list) :
        for name in name_list :
            if name != self.name :
                self.giveList[name] = 0
                self.receiveList[name] = 0
    def giveGiftTo(self, name) :
        self.out_degree += 1
        self.giveList[name] += 1

    def receiveGiftFrom(self, name) :
        self.in_degree += 1
        self.receiveList[name] += 1
        
    def printInforms(self) :
        print(self.name, self.giveList, self.receiveList, self.calGiftFigure())
    
    def calGiftFigure(self) :
        return self.out_degree - self.in_degree

def solution(friends, gifts):
    answer = 0
    
    name_list = {}
    for name in friends :
        name_list[name] = Node(name)
        name_list[name].makeList(friends)

    for detail in gifts :
        giver, receiver = detail.split(' ')
        name_list[giver].giveGiftTo(receiver)
        name_list[receiver].receiveGiftFrom(giver)

    gift_num_list = {}

    for friend in name_list :
        gift_num_list[friend] = 0
        for i in name_list[friend].giveList :
            if name_list[friend].giveList[i] > name_list[friend].receiveList[i] :
                gift_num_list[friend] += 1
            elif name_list[friend].giveList[i] == name_list[friend].receiveList[i] :
                if name_list[friend].calGiftFigure() > name_list[i].calGiftFigure() :
                    gift_num_list[friend] += 1

    answer = max(gift_num_list.values())

    return answer