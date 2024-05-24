# 문제
# 2차원 행렬 arr1과 arr2를 입력받아서 행렬의 곱을 반환하는 solution 함수하라

# 제약 조건
# - 행렬 arr1, arr2의 행과 열의 길이는 2이상 100이하
# - 행렬 arr1, arr2의 데이터는 -10이상 20이하 자연수
# - 곱할 수 있는 배열만 주어진다.

# 풀이 및 시간 복잡도
# 1. 행렬의 곱 {nxm} x {mxr} = {nxr}
#     1. 각 입력 행렬의 길이를 알아내서, 2차원 배열을 만들어야겠다.
# 2. arr[i][j] x arr[j][r] ⇒ arr[i][r]
#  ex)
#   for n in arr1의 행
#       for r in arr2의 열
#           for m in arr1의 열
#               arr1[n][m] x arr2[m][r] = arr[n][r]
#   -> 총 o(N^3)
#

# 함수
# def solution(arr1, arr2):
#     nLen = len(arr1); mLen = len(arr1[0]); rLen = len(arr2);

#     arr = [[0 for j in range(rLen)] for i in range(nLen)]

#     for n in range(nLen):
#         for r in range(rLen):
#             for m in range(mLen):
#                 arr[n][m] = arr1[n][m] * arr2[m][r]
#     return arr;

def solution(arr1, arr2):
    r1, c1 = len(arr1), len(arr1[0]);
    r2, c2 = len(arr2), len(arr2[0]);

    ret = [[0] * c2 for _ in range(r1)]

    for i in range(r1):
        for j in range(c2):
            for k in range(c1):
                ret[i][j] += arr1[i][k] * arr2[k][j]
    return ret;

# 테스트 케이스
print(solution([[1, 4], [3, 2], [4, 1]], [[3, 3], [3, 3]])); # [[15, 15], [15, 15], [15, 15]]
print(solution([[2, 3, 2], [4, 2, 4], [3, 1, 4]], [[5, 4, 3], [2, 4, 1], [3, 1, 1]])) # [[22, 22, 11], [36, 28, 18], [29, 20, 14]]


