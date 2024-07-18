def solution(A,B):
    answer = 0
    arr_len = len(A)
    A.sort()
    B.sort()
    for i in range(arr_len) :
        answer += A[i] * B[arr_len - 1 - i]
    return answer