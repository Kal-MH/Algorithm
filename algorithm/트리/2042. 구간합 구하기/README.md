## 문제

어떤 N개의 수가 주어져 있다. 그런데 **중간에 수의 변경이 빈번히 일어나고 그 중간에 어떤 부분의 합을 구하려 한다.** 만약에 1,2,3,4,5 라는 수가 있고, 3번째 수를 6으로 바꾸고 2번째부터 5번째까지 합을 구하라고 한다면 17을 출력하면 되는 것이다. 그리고 그 상태에서 다섯 번째 수를 2로 바꾸고 3번째부터 5번째까지 합을 구하라고 한다면 12가 될 것이다.

```cpp
인덱스 트리
- 구간합과 중간값 변경이 일어날 때 사용할 수 있는 자료구조
- 포화 이진트리(perfect) 구조
1. 구간합을 구하고 중간값을 변경하는 연산에 대한 각각의 시간복잡도
- O(logN)
2. 인덱스 트리 구성하기
	1) 리프노드의 개수가 N개 이상이 되도록 트리 설정
		- 입력값보다 많은 N개가 될 때에는 적절한 초기값을 설정한다.
			(합이라면 0, 곱이라면 1, 최대값이라면 최소값, 최소값이라면 최대값)
	2) 리프노드 데이터 입력
	3) 자식노드를 참조하여 부모 노드 데이터 입력
- 최대 2N개의 노드가 되므로 2N번 입력연산이 이루어져 트리를 구현하는 시간복잡도는 O(N)
- 첫번째 리프 노드의 인덱스는 S가 된다.
```

## 입력

첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000) 가 주어진다. M은 수의 변경이 일어나는 횟수이고, K는 구간의 합을 구하는 횟수이다. 그리고 둘째 줄부터 N+1번째 줄까지 N개의 수가 주어진다. 그리고 N+2번째 줄부터 N+M+K+1번째 줄까지 세 개의 정수 a, b, c가 주어지는데, a가 1인 경우 b(1 ≤ b ≤ N)번째 수를 c로 바꾸고 a가 2인 경우에는 b(1 ≤ b ≤ N)번째 수부터 c(b ≤ c ≤ N)번째 수까지의 합을 구하여 출력하면 된다.

입력으로 주어지는 모든 수는 -2^63보다 크거나 같고, 2^63-1보다 작거나 같은 정수이다.

## 출력

첫째 줄부터 K줄에 걸쳐 구한 구간의 합을 출력한다. 단, 정답은 -263보다 크거나 같고, 263-1보다 작거나 같은 정수이다.

## 예제 입력 1

```
5 2 2
1
2
3
4
5
1 3 6
2 2 5
1 5 2
2 3 5

```

## 예제 출력 1

```
17
12
```
