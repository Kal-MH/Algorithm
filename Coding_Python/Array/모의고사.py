# 문제
# 1번 문제부터 마지막 문제까지 정답이 순서대로 저장된 배열 answers가 주어질 때,
# 가장 많은 정답을 맞힌 사람이 누구인지 배열에 담아 반환하는 solution을 작성하라.

# 1번 수포자 : 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
# 2번 수포자 : 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
# 3번 수포자 : 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, ...

# 제약 조건
# - 시험은 최대 10,000문제
# - 문제의 정답은 1, 2, 3, 4, 5 중에 하나
# - 가장 높은 점수를 받은 사람이 여럿이라면 오름차순으로 정렬

# 풀이 및 시간 복잡도

# 함수
# def solution(arg):
#     oneAnswer = [1, 2, 3, 4, 5];
#     twoAnswer = [2, 1, 2, 3, 2, 4, 2, 5];
#     threeAnswer = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5];

#     one = 0; two = 0; three = 0; maxValue = -1;

#     for i in range(len(arg)):
#         if (oneAnswer[i % len(oneAnswer)] == arg[i]) : one += 1
#         if (twoAnswer[i % len(twoAnswer)] == arg[i]) : two += 1
#         if (threeAnswer[i % len(threeAnswer)] == arg[i]) : three += 1

#         maxValue = max(one, two, three, maxValue)

#     arr = [one, two, three]
#     retArr = [];
#     for i in range(len(arr)):
#         if (arr[i] == maxValue):
#             retArr.append(i + 1);
#     return sorted(retArr);

def solution(answers):
    patterns = [
        [1, 2, 3, 4, 5],
        [2, 1, 2, 3, 2, 4, 2, 5],
        [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    ]

    scores = [0] * 3

    for i, answer in enumerate(answers):
        for j, pattern in enumerate(patterns):
            if (answer == pattern[i % len(pattern)]):
                scores[j] += 1;
    max_score = max(scores);

    highest_scores = []
    for i, score in enumerate(scores):
        if score == max_score:
            highest_scores.append(i + 1);

    return highest_scores;

# 테스트 케이스
print(solution([1, 2, 3, 4, 5]));  # [1]
print(solution([1, 3, 2, 4, 2]));  # [1, 2, 3]


