# 정수 배열을 정렬해서 반환하는 solution 함수 완성
# 제약 조건
# 1, 정수 배열 길이는 2이상 10^5이하
# 2. 정수 배열 원소의 값은 -100,000 이상 100, 000이하

# 풀이
# 1. 리스트 정렬 함수를 이용 : O(NlogN)
# 2. 버블 정렬을 이용 : O(N^2) -> 10만 입력값으로 시간 초과

# 리스트 원본갑 자체를 변경
# def solution(arr): 
#     arr.sort();
#     return arr;

import time;

# 리스트 원본값을 변경하지 않고 정렬된 값을 반환
def solution(arr):
    result = list(sorted(arr));
    return result;

def solution_2(arr):
    n = len(arr);
    for i in range(n):
        for j in range(n - i - 1):
            if (arr[j] > arr[j + 1]):
                arr[j], arr[j + 1] = arr[j + 1], arr[j];
    return arr;

def measure_time(func, arr):
    start_time = time.time();
    result = func(arr);
    end_time = time.time();
    return result, end_time - start_time;

# 테스트 케이스
print(solution([1, -5, 2, 4, 3]))
print(solution([2, 1, 1, 3, 2, 5, 4]))
print(solution([6, 1, 7]))

# 시간 측정

arr = list(range(10000))

bubble_result, bubble_time = measure_time(solution_2, arr);
print(format(bubble_time, ".10f"));

sort_result, sort_time = measure_time(solution, arr);
print(format(sort_time, ".10f"));