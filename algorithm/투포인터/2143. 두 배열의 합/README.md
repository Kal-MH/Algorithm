한 배열 A[1], A[2], …, A[n]에 대해서, 부 배열은 A[i], A[i+1], …, A[j-1], A[j] (단, 1 ≤ i ≤ j ≤ n)을 말한다. 이러한 부 배열의 합은 A[i]+…+A[j]를 의미한다. 각 원소가 정수인 두 배열 A[1], …, A[n]과 B[1], …, B[m]이 주어졌을 때, **A의 부 배열의 합에 B의 부 배열의 합을 더해서 T가 되는 모든 부 배열 쌍의 개수**를 구하는 프로그램을 작성하시오.

예를 들어 A = {1, 3, 1, 2}, B = {1, 3, 2}, T=5인 경우, 부 배열 쌍의 개수는 다음의 7가지 경우가 있다.

```
T(=5) = A[1] + B[1] + B[2]
      = A[1] + A[2] + B[1]
      = A[2] + B[3]
      = A[2] + A[3] + B[1]
      = A[3] + B[1] + B[2]
      = A[3] + A[4] + B[3]
      = A[4] + B[2]

풀이전략
1. 무작정 구해보기 (최소 O(N^2))
2. 투 포인터로 구해보기
```

## 입력

첫째 줄에 T(-1,000,000,000 ≤ T ≤ 1,000,000,000)가 주어진다. 다음 줄에는 n(1 ≤ n ≤ 1,000)이 주어지고, 그 다음 줄에 n개의 정수로 A[1], …, A[n]이 주어진다. 다음 줄에는 m(1 ≤ m ≤ 1,000)이 주어지고, 그 다음 줄에 m개의 정수로 B[1], …, B[m]이 주어진다. 각각의 배열 원소는 절댓값이 1,000,000을 넘지 않는 정수이다.

```java
백준 프로그램에서 result, countA, countB에 대해서 자료형을 int를 썼을 때 
오류가 났다.
-> int * int에 대해서는 웬만하면 long을 쓰는 것이 좋겠다.
```

## 출력

첫째 줄에 답을 출력한다. 가능한 경우가 한 가지도 없을 경우에는 0을 출력한다.

## 예제 입력 1

```
5
4
1 3 1 2
3
1 3 2

```

## 예제 출력 1

```
7
```

## 풀이

```java
package day1;

import java.util.*;
import java.io.*;

/*
 * 1. 입력
 * - T(-1,000,000,000 ≤ T ≤ 1,000,000,000)
 * - n <= 1000
 * - m <= 1000
 * */

public class Main {
	//variables
	static long T;
	static int N, M;
	static long[] inputA, inputB;
	
	public static void main(String args[]) throws Exception{
		//input
		System.setIn(new FileInputStream("src/day1/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Long.parseLong(br.readLine());
		
		N = Integer.parseInt(br.readLine());
		inputA = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			inputA[i] = Long.parseLong(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		inputB = new long[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			inputB[i] = Long.parseLong(st.nextToken());
		}
		
		//2 pointers
		//1) subA, subB 만들기
		//	- ptA(합을 증가시키는 역할), ptB(합을 감소시키는 역할)을 위해 subB는 내림차순으로 정렬되어야 한다.
		List<Long> subA = new ArrayList<>();
		List<Long> subB = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			long sum = inputA[i];
			subA.add(sum);
			for(int j = i + 1; j < N; j++) {
				sum += inputA[j];
				subA.add(sum);
			}
		}
		
		for(int i = 0; i < M; i++) {
			long sum = inputB[i];
			subB.add(sum);
			for(int j = i + 1; j < M; j++) {
				sum += inputB[j];
				subB.add(sum);
			}
		}
		
		Collections.sort(subA);
		Collections.sort(subB, Comparator.reverseOrder());
		//2) 2 pointers 연산
		// - T와 같다면 같은 값을 가지는 항목이 더 있는 지 확인
		
		int ptA = 0, ptB = 0;
		long result = 0;
		
		while (ptA < subA.size() && ptB < subB.size()) {
			long currentA = subA.get(ptA);
			long target = T - currentA;
			
			if (target == subB.get(ptB)) {
				long countA = 0;
				long countB = 0;
				while (ptA < subA.size() && currentA == subA.get(ptA)) {
					countA++;
					ptA++;
				}
				while (ptB < subB.size() && target == subB.get(ptB)) {
					countB++;
					ptB++;
				}
				result += (countA * countB);
			} else if (target < subB.get(ptB)) {
				ptB++;
			} else {
				ptA++;
			}
		}
		//output
		System.out.println(result);
	}
}
```
