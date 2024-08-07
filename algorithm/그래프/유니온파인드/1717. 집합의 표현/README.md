## 문제

초기에 **{0}, {1}, {2}, ... {n}** 이 각각 n+1개의 집합을 이루고 있다. 여기에 합집합 연산과, 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산을 수행하려고 한다.

집합을 표현하는 프로그램을 작성하시오.

```java
서로소 집합으로 볼 수 있다.
```

### 입력

첫째 줄에 n(1 ≤ n ≤ 1,000,000), m(1 ≤ m ≤ 100,000)이 주어진다. m은 입력으로 주어지는 연산의 개수이다. 다음 m개의 줄에는 각각의 연산이 주어진다. 합집합은 0 a b의 형태로 입력이 주어진다. 이는 a가 포함되어 있는 집합과, b가 포함되어 있는 집합을 합친다는 의미이다. 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산은 1 a b의 형태로 입력이 주어진다. 이는 a와 b가 같은 집합에 포함되어 있는지를 확인하는 연산이다. a와 b는 n 이하의 자연수 또는 0이며 같을 수도 있다.

### 출력

1로 시작하는 입력에 대해서 한 줄에 하나씩 YES/NO로 결과를 출력한다. (yes/no 를 출력해도 된다)

### 예제 입력

```jsx
7 8
0 1 3
1 1 7
0 7 6
1 7 1
0 3 7
0 4 2
0 1 1
1 1 1
```

### 예제 출력

```jsx
NO
NO
YES
```

## 풀이

1. 목표 
    
    집합에 대해 합집합과 두 개의 원소가 같은 집합에 포함되어 있는 지 확인하는 연산 프로그램 작성하기
    
2. 조건
    1. 문제
        1. {0}, {1}, ... {n} 개의 집합
    2. 입력
        1. 1 ≤ n(집합 개수) ≤ 1,000,000, 1 ≤ m(연산 개수) ≤ 100, 000
        2. 0 a b의 형태로 입력
        3. a, b는 n 이하의 자연수 또는 0이며 같을 수도 있다.
    3. 출력
        1. 1 연산에 대해서는 YES/NO로 결과를 출력한다.
    4. 예외처리
3. 해석
    1. N이 100만, M이 10만이기 때문에 각각의 집합을 링크드리스트로 구현하게 되면 편향된 트리 모양으로 **O(NM)**의 시간복잡도가 소요, time out이 날 수가 있다.
    2. {0}, {1}, ... {n} 개의 집합이라고 명시되어 있기 때문에 서로소 집합이라고 할 수 있다.따라서 서로소 집합에 대한 union, find 연산을 생각할 수 있다.
        1. 1 ~ N까지 1차원 배열에 자신의 숫자를 각각의 세긴다.
        2. 재귀함수를 이용, find 연산을 수행한다.
        3. union연산의 경우, 각각의 조상(부모)숫자를 찾아 하나로 통일한다.

---

## Union-Find

서로소 집합(교집합이 공집합인 집합). 서로소 집합의 정보를 **확인(find)**하고 **조작(Union)**할 수 있는 자료구조. 

```java
주의사항
 1. self-loop가 있는 곳까지 탐색 - self-loop끼리 그룹으로 이어준다.
 2. 경로 압축
		ex) return parent[a] = find(parent[a]);
```
