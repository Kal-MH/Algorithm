# 문제
# 정수 배열 numbers에서 서로 다른 인덱스 2개의 수를 뽑아 더해 만들 수 있는 모든 수를 배열에 오름차순으로 담아 반환

# 제약 조건
# numbers의 길이는 2 이상 100 이하
# numbers의 모든 수는 0 이상 100 이하

# 풀이 및 시간 복잡도
# 1. 단순 2차원 반복문 -> O(n^2)
# 2. N^2를 정렬 -> O(N^2logN^2)

# 함수
# def solution(arg):
#     answer = [];
#     n = len(arg);

#     for i in range(n):
#         for j in range(n):
#             if (i == j): continue
#             answer.append(arg[i] + arg[j])

#     not_duplicated_arr = list(set(answer));
#     return sorted(not_duplicated_arr);

def solution(numbers):
    ret = [];

    for i in range(len(numbers)):
        for j in range(i + 1, len(numbers)):
            ret.append(numbers[i] + numbers[j]);

    ret = sorted(set(ret))
    return ret

# 테스트 케이스
print(solution([2, 1, 3, 4, 1])); # [2, 3, 4, 5, 6, 7]
print(solution([5, 0, 2, 7])) # [2, 5, 7, 9, 12]


