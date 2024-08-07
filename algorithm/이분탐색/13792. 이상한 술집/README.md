## 문제

프로그래밍 대회 전날, 은상과 친구들은 이상한 술집에 모였다. 이 술집에서 막걸리를 시키면 주전자의 용량은 똑같았으나 안에 들어 있는 막걸리 용량은 랜덤이다.  즉 한 번 주문에 막걸리 용량이 802ml 이기도 1002ml가 나오기도 한다.  은상은 막걸리 N 주전자를 주문하고, 자신을 포함한 친구들 K명에게 막걸리를 똑같은 양으로 나눠주려고 한다.  그런데 은상과 친구들은 다른 주전자의 막걸리가 섞이는 것이 싫어서, **분배 후 주전자에 막걸리가 조금 남아 있다면 그냥 막걸리를 버리기로 한다.**  (즉, 한 번 주문한 막걸리에 남은 것을 모아서 친구들에게 다시 주는 경우는 없다.  예를 들어 5명이 3 주전자를 주문하여 1002, 802, 705 ml의 막걸리가 각 주전자에 담겨져 나왔고, 이것을 401ml로 동등하게 나눴을 경우 각각 주전자에서 200ml, 0m, 304ml 만큼은 버린다.) 이럴 때 K명에게 최대한의 많은 양의 막걸리를 분배할 수 있는 용량 ml는 무엇인지 출력해주세요.

```java
접근
1. 파라메트릭 서치
- 얼마의 용량을 정해야 K명의 친구들과 나눠먹을 수 있는가 (yes/no)문제
- yes - / - no 로 답이 정렬되어 있음
2. 시간복잡도
- 막걸리 용량 정하기 : 이분탐색 O(logT)
		- 막걸리 용량 범위
			L = 1, R = 막걸리 주전자에서 가장 큰 용량
- 막걸리 주전자에 대해서 계산, 판단하기 : O(N)
```

## 입력

첫째 줄에는 은상이가 주문한 막걸리 주전자의 개수 N, 그리고 은상이를 포함한 친구들의 수 K가 주어진다. 둘째 줄부터 N개의 줄에 차례로 주전자의 용량이 주어진다. N은 10000이하의 정수이고, K는 1,000,000이하의 정수이다. 막걸리의 용량은 231 -1 보다 작거나 같은 자연수 또는 0이다. 단, 항상 **N ≤ K** 이다. 즉, 주전자의 개수가 사람 수보다 많을 수는 없다.

## 출력

첫째 줄에 K명에게 나눠줄 수 있는 최대의 막걸리 용량 ml 를 출력한다.

```java
정답의 최대치
1. 막걸리 주전자의 개수(N) : 10000 -> int
2. 친구들의 수(K) : 1000000 -> int
3. 막걸리의 용량 : 2^31 - 1 -> int
4. 최대 막거리 용량 ml 
: 한 번 주문한 막걸리에 남은 것을 모아서 다시 주는 경우는 없으므로
  친구들이 먹을 막걸리 용량은 막걸리 최대 용량보다 작다. 2^31 - 1 ->int

5. 계산 과정에서의 막걸리 용량 ->long
```

## 예제 입력 1

```
2 3
702
429

```

## 예제 출력 1

```
351

```

## 예제 입력 2

```
4 11
427
541
774
822

```

## 예제 출력 2

```
205
```
