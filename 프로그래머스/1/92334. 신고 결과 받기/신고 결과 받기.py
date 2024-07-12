class User:
    def __init__(self, name) :
        self.name = name
        self.reportNum = 0
        self.whoReported = []
    
    def beingReportedBy(self, name) :
        if name not in self.whoReported :
            self.reportNum += 1
            self.whoReported.append(name)
    
    def showUpInfo(self) :
        print(self.name, self.reportNum, self.whoReported)

def solution(id_list, report, k):
    receive_info = {}
    user_info = {}
    answer = []
    for id in id_list :
        receive_info[id] = 0
        user_info[id] = User(id)
    for event in report :
        reporter, reported = event.split()
        user_info[reported].beingReportedBy(reporter)
    
    for user in user_info :
        if user_info[user].reportNum >= k :
            for name in user_info[user].whoReported :
                receive_info[name] += 1
    
    answer = list(receive_info.values())

    return answer