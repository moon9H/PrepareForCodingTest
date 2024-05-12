from sys import stdin

# 연결리스트의 노드 클래스
class ListNode:
    def __init__(self, val, prev, next):
        self.val = val
        self.prev = prev
        self.next = next

# 연결리스트의 head
head = ListNode('dummy', None, None)
curr = head

init = input() # 초기 문자열
for i in range(len(init)): # 초기 문자열을 연결리스트로 만들어 둠
    new_node = ListNode(init[i], None, None)
    curr.next = new_node
    new_node.prev = curr
    curr = new_node

# 명령어들 처리
for _ in range(int(input())):
    command = stdin.readline().rstrip()
    if command == 'L':
        if curr.val != 'dummy':
            curr = curr.prev
    elif command == 'D':
        if curr.next:
            curr = curr.next
    elif command == 'B':
        if curr.val != 'dummy':
            curr = curr.prev
            if curr.next.next:
                curr.next = curr.next.next
                curr.next.prev = curr
            else:
                curr.next = None
    else:
        new_node = ListNode(command[-1], None, None)
        if curr.next:
            new_node.next = curr.next
            curr.next.prev = new_node
        curr.next = new_node
        new_node.prev = curr
        curr = new_node

# head 다음 노드부터 출력
print_node = head.next
while print_node:
    print(print_node.val, end='')
    print_node = print_node.next