import sys
input = sys.stdin.readline

from collections import deque

N = int(input())
heap = deque([0])
for _ in range(N) :
    x = int(input())
    if x == 0 :
        if len(heap) <= 1 :
            print(0)
        else :
            print(heap[1])
            last = heap.pop()
            if len(heap) == 1 :
                continue
            heap[1] = last
            parent = 1
            size = len(heap) - 1
            while True :
                left = parent * 2
                right = parent * 2 + 1
                smallest = parent

                if left <= size and heap[left] < heap[smallest] :
                    smallest = left
                if right <= size and heap[right] < heap[smallest] :
                    smallest = right
                
                if smallest == parent :
                    break
                
                heap[parent], heap[smallest] = heap[smallest], heap[parent]
                parent = smallest
    else :
        heap.append(x)
        size = len(heap)
        child = size - 1
        parent = child // 2
        while (heap[parent] > heap[child]) :
            heap[parent], heap[child] = heap[child], heap[parent]
            child = parent
            parent //= 2