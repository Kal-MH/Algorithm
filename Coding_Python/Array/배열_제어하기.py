# 문제
# 배열의 중복값을 제거하고 내림차순으로 정렬해서 반환하라.

# 제약 조건
# 1. 배열의 길이는 2 이상 1000 이하이다.
# 2. 배열의 원소의 값은 -1000,000 이상 1000,000 이하이다.

# 풀이 및 시간 복잡도
# - 배열 길이가 1000이므로 O(n^2)도 가능하다.

# 1. set 함수를 이용해서 중복값을 제거한다. -> 해시 알고리즘 사용으로 시간복잡도는 O(n)
# 2. sorted 함수를 이용해서 내림차순으로 정렬한다. -> 정렬 알고리즘 사용으로 시간복잡도는 O(nlogn)

# 전체 시간복잡도 : O(nlogn)

# 함수
def solution(arg):
    arr = list(set(arg)) # 원본은 보존, 반환값은 객체 튜플
    sorted_arr = sorted(arr, reverse=True)

    return sorted_arr;
    # return sorted(list(set(arg)), reverse=True);

# 테스트 케이스
print(solution([4, 2, 2, 1, 3, 4]));
print(solution([2, 1, 1, 3, 2, 5, 4]));
print(solution([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]));
print(solution([1, 1, 1, 1, 1, 1, 1, 1, 1, 1]));

