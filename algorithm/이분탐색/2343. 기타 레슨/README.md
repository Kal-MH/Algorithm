## 문제

강토는 자신의 기타 강의 동영상을 블루레이로 만들어 판매하려고 한다. 블루레이에는 총 N개의 강의가 들어가는데, 블루레이를 녹화할 때, 강의의 순서가 바뀌면 안 된다. 순서가 뒤바뀌는 경우에는 강의의 흐름이 끊겨, 학생들이 대혼란에 빠질 수 있기 때문이다. 즉, i번 강의와 j번 강의를 같은 블루레이에 녹화하려면 i와 j 사이의 모든 강의도 같은 블루레이에 녹화해야 한다.

강토는 이 블루레이가 얼마나 팔릴지 아직 알 수 없기 때문에, 블루레이의 개수를 가급적 줄이려고 한다. 오랜 고민 끝에 강토는 **M개의 블루레이에 모든 기타 강의 동영상을 녹화하기로 했다. 이때, 블루레이의 크기(녹화 가능한 길이)를 최소**로 하려고 한다. 단, M개의 블루레이는 모두 같은 크기이어야 한다.

강토의 각 강의의 길이가 분 단위(자연수)로 주어진다. 이때, 가능한 블루레이의 크기 중 최소를 구하는 프로그램을 작성하시오.

```
접근 - 파라메트릭 서치
1. 녹화 가능 길이가 어떻게 되어야 M개의 블루레이를 만들 수 있는가 (yes/no 결정 문제)
	- 녹화 가능 길이를 범위로 yes/no 정렬된 결과를 얻을 수 있다.
2. 시간복잡도
	- 녹화 가능 길이 이분 탐색 : logT
  - N개의 기타 강의 동영상 길이 계산 및 비교 : N
	: O(N * logT) = 10,000 * log(10,000 * 100,000) = 10,000 * 30

3. 고려할 점.
  1) A배열은 정렬되면 안됨(강의 순서가 정해져 있음)
  2) L과 R의 범위.
		- 최소한 L이 제일 긴 강의시간은 되어야 함.
  3) determination의 참이 되는 조건
    - count <= M
    - count가 M보다 적다는 것은 현재 블루레이 길이가 충분히 크다는 것으로
			블루레이 길이를 좀 더 줄여도 된다는 것을 의미하여 참의 경우에 속한다.
```

## 입력

첫째 줄에 강의의 수 N (1 ≤ N ≤ 100,000)과 M (1 ≤ M ≤ N)이 주어진다. 다음 줄에는 강토의 기타 강의의 길이가 강의 순서대로 분 단위로(자연수)로 주어진다. 각 강의의 길이는 10,000분을 넘지 않는다.

## 출력

첫째 줄에 가능한 블루레이 크기중 최소를 출력한다.

```
정답의 최대치
1. 강의의 수 N < 100,000 : int
2. 블루레이 수 M < 100,000 : int
3. 각 강의의 길이 < 10,000 : int
4. 각 강의 길이 합산 100,000 * 10,000 : int
5. 블루레이 크기 중 최소 < 100,000 * 10,000 : int
```

## 예제 입력 1

```
9 3
1 2 3 4 5 6 7 8 9

```

## 예제 출력 1

```
17
```

---

### determination 다른 풀이

```java
static boolean determination(int len) {
        int count = 0;
        
        int sum = 0;
        for(int i = 1; i <= N; i++) {
        	if (sum + A[i] < len) {
        		sum += A[i];
        		continue;
        	}
        	count++;
        	if (sum + A[i] == len)
        		sum = 0;
        	else
        		sum = A[i];
        }
        if (sum != 0)
        	count++;
        
        return count <= M;
    }
```
