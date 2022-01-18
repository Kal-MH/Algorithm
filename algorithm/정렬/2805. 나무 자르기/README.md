## 문제

상근이는 나무 M미터가 필요하다. 근처에 나무를 구입할 곳이 모두 망해버렸기 때문에, 정부에 벌목 허가를 요청했다. 정부는 상근이네 집 근처의 나무 한 줄에 대한 벌목 허가를 내주었고, 상근이는 새로 구입한 목재절단기를 이용해서 나무를 구할것이다.

목재절단기는 다음과 같이 동작한다. 먼저, 상근이는 절단기에 **높이 H**를 지정해야 한다. 높이를 지정하면 톱날이 땅으로부터 H미터 위로 올라간다. 그 다음, 한 줄에 연속해있는 나무를 모두 절단해버린다. 따라서, 높이가 H보다 큰 나무는 H 위의 부분이 잘릴 것이고, 낮은 나무는 잘리지 않을 것이다. 예를 들어, 한 줄에 연속해있는 나무의 높이가 20, 15, 10, 17이라고 하자. 상근이가 높이를 15로 지정했다면, 나무를 자른 뒤의 높이는 15, 15, 10, 15가 될 것이고, 상근이는 길이가 5인 나무와 2인 나무를 들고 집에 갈 것이다. (총 7미터를 집에 들고 간다) 절단기에 설정할 수 있는 높이는 양의 정수 또는 0이다.

상근이는 환경에 매우 관심이 많기 때문에, **나무를 필요한 만큼만 집으로 가져가려고 한다**. 이때, **적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값**을 구하는 프로그램을 작성하시오.

## 입력

첫째 줄에 나무의 수 N과 상근이가 집으로 가져가려고 하는 나무의 길이 M이 주어진다. (1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000)

둘째 줄에는 나무의 높이가 주어진다. 나무의 높이의 합은 항상 M보다 크거나 같기 때문에, 상근이는 집에 필요한 나무를 항상 가져갈 수 있다. 높이는 1,000,000,000보다 작거나 같은 양의 정수 또는 0이다.

## 출력

적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값을 출력한다.

## 예제 입력 1

```
4 7
20 15 10 17

```

## 예제 출력 1

```
15

```

## 예제 입력 2

```
5 20
4 42 40 26 46

```

## 예제 출력 2

```
36
```

## 풀이

```java
import java.util.*;
import java.io.*;

/*
 * 1. 목표
 * 	- 적어도 M미터의 나무를 집에 가져갈 수 있는 절단기에 설정할 수 있는 높이의 최댓값
 *  - 적어도 M미터라는 것은 M이상이 되게 하는 높이 H의 값을 구한다는 의미가 됨.
 * 2. 입력
 * - 1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000 -> long 사용
 * */

public class Main {
	static int N, M;
	static int[] trees;
	
	public static void main(String args[]) throws Exception{
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		trees = new int[N];
		st = new StringTokenizer(br.readLine());
		long max = 0;
		for(int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, trees[i]);
		}
		
		//3 pointers
		// 1) start, mid, end pointer 활용
		// 	- 나무 합(M)보다 크면 end = mid + 1;
		//	 - M보다 작으면 start = mid - 1;
		// s, e, mid result의 경우, trees배열의 인덱스를 가리키는 것이 아니라 
		// 나무의 길이를 가리키는 포인터이기 때문에 long 자료형을 쓴다.
		long s = 0;
		long e = max;
		long mid = 0;
		long result = 0;
		while (true) {
			if (s > e)
				break;
			mid = (s + e) / 2;
			long sum = calc(mid);
			if (sum == M) {
				result = mid;
				break;
			} else if (sum > M) {
				result = mid;
				s = mid + 1;
			} else if (sum < M) {
				e = mid - 1;
			}
		}
		//output
		System.out.println(result);
		
	}
	
	static long calc(long value) {
		long result = 0;

		for (int tree : trees) {
			if (tree > value)
				result += tree - value;
		}
		return result;
	}
}
```

## 풀이 팁

1. 처음 절반을 잘른다고 했을 때, 얼마만큼의 나무를 얻을 수 있는 지 보자
    1. 만약 원하는 나무보다 크다면 start포인터를 mid으로 이동
        
        → mid를 다시 범위에 중간으로 이동
        
    2. 만약 원하는 나무보다 작다면 end포인터를 mid으로 이동
        
        → mid를 다시 범위에 중간으로 이동
        

## 바이너리 서치 vs 파라메트릭 서치

바이너리 서치

- 값을 만족하는 정확한 값을 찾는다.

파라메트릭 서치

- 조건을 만족하면 되는 것인가. 즉, 조건을 만족하는 값이 여러 개가 존재할 수 있다.

사실 둘 사이에 큰 차이는 없다.

### 내 풀이

```
int	main(void)
{
	int N;
	int M;
	long	*arr;
	int i;
	int start, mid, end;
	int sum;
	int value;
	
	scanf("%d %d", &N, &M);
	if (!(arr = (long *)malloc(sizeof(long) * N)))
		return (0);
		
	start = 0;
	end = 0;
	for(i = 0;i < N; i++){
		scanf("%d", &arr[i]);
		end = (end < arr[i] ? arr[i] : end);
	}

	while (1) {
		sum = 0;
		mid = (start + end) / 2;
		for(i = 0;i < N;i++) {
			if (arr[i] > mid) {
				sum += arr[i] - mid;
			}
		}
		if (sum >= M) {
			value = mid;
			start = mid;
		} else if (sum < M) {
			if (start == value || end == value)
				break ;
			end = mid;
		}
	}
	printf("%d", value);	
	free(arr);
	return (0);
}
```

### 개선된 내 풀이

```
#include <stdio.h>
#include <stdlib.h>

int	main(void)
{
	int N;
	int M;
	long	*arr;
	int i;
	int start, mid, end;
	int sum;
	int value;
	
	scanf("%d %d", &N, &M);
	if (!(arr = (long *)malloc(sizeof(long) * N)))
		return (0);
		
	start = 0;
	end = 0;
	for(i = 0;i < N; i++){
		scanf("%ld", &arr[i]);
		end = (end < arr[i] ? arr[i] : end);
	}

	while (1) {
		sum = 0;
		mid = (start + end) / 2;
		for(i = 0;i < N;i++) {
			if (arr[i] > mid) {
				sum += arr[i] - mid;
			}
		}
		if (sum >= M) {
			start = mid + 1;
			value = mid;
		}else if (sum < M) {
			end = mid - 1;
		}
		if (start > end) {
			break ;
		}
	}
	printf("%d", value);	
	free(arr);
	return (0);
}
```
