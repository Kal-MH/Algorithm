## 문제

정수 A가 B로 나누어 떨어지면, B는 A의 약수이고 A는 B의 배수이다.

최대공약수란 정수의 공통된 약수 중 가장 큰 수를 말한다. 예를 들어, 12와 8의 공통된 약수 1, 2, 4 중에서 가장 큰 것은 4이기 때문에 12와 8의 최대공약수는 4이다.

**N개의 정수 중에서 임의의 수 K를 뺐을 때, 나머지 N-1개의 최대공약수가 가장 커지는** 것을 찾는 프로그램을 작성하시오. **이때, 최대공약수는 K의 약수가 되면 안 된다.**

예를 들어, 정수 8, 12, 24, 36, 48에서 8을 빼면 나머지 12, 24, 36, 48의 최대공약수는 12가 되고, 12는 빠진 수 8의 약수가 아니기 때문에 정답이 될 수 있다. 이때, 다른 수를 빼도 최대공약수가 8보다 커질 수 없다.

하지만, 8, 12, 20, 32, 36의 경우에는 그 무엇을 빼더라도 나머지 수의 최대공약수가 K의 약수가 되기 때문에, 정답을 구할 수 없다.

N개의 수가 주어졌을 때, 정수 하나를 빼서 만들 수 있는 가장 큰 최대공약수를 구하는 프로그램을 작성하시오.

```cpp
2 pointer를 응용해보자.

num | 8 | 12 | 24 | 36 | 48
LtoR| 8 | 4  | 4  | 4  | 4   //왼쪽부터 오른쪽으로 이동하며 모든 숫자들의 최대공약수
RtoL| 4 | 12 | 12 | 12 | 48  //오른쪽부터 왼쪽으로 이동하며 모든 숫자들의 최대공약수

만약, 임의의 수 8을 뺐다면, RtoL[1]이 답이 될 것이다.
만약, 임의의 수 12를 뺐다면, gcd(LtoR[0], RtoL[2])가 답이 될 것이다.
```

## 입력

첫째 줄에 정수의 개수 N (4 ≤ N ≤ 1,000,000)이 주어진다.

둘째 줄부터 N개의 줄에는 N개의 수가 주어진다. 각각의 수는 20억을 넘지 않는 자연수이다.

## 출력

첫째 줄에 정수 하나를 빼서 만들 수 있는 가장 큰 최대공약수를 출력하고, 공백을 출력한 다음 뺀 수를 출력한다.

뺀 수를 K라고 했을 때, 나머지 수의 최대공약수가 K의 약수가 되면 안 된다.

만약 정답이 없는 경우에는 -1을 출력한다.

## 예제 입력 1

```
5
8 12 24 36 48

```

## 예제 출력 1

```
12 8

```

## 예제 입력 2

```
5
8 12 20 32 36

```

## 예제 출력 2

```
-1
```

## 풀이

### solution

```java
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] nums;
    static int[] gcdLtoR;
    static int[] gcdRtoL;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        gcdLtoR = new int[N];
        gcdRtoL = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        gcdLtoR[0] = nums[0];
        gcdRtoL[N - 1] = nums[N - 1];
        for (int i = 1; i < N; i++) {
            gcdLtoR[i] = gcd(gcdLtoR[i - 1], nums[i]);
        }
        for (int i = N - 2; i >= 0; i--) {
            gcdRtoL[i] = gcd(gcdRtoL[i + 1], nums[i]);
        }

        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < N; i++) {
            int gcd = 0;
            if (i == 0) {
                gcd = gcdRtoL[1];
            } else if (i == N - 1) {
                gcd = gcdLtoR[N - 2];
            } else {
                gcd = gcd(gcdLtoR[i - 1], gcdRtoL[i + 1]);
            }

            if (nums[i] % gcd != 0 && gcd > max) {
                max = gcd;
                maxIndex = i;
            }
        }
        if (max == 0) {
            System.out.println(-1);
        } else {
            System.out.println(max + " " + nums[maxIndex]);
        }
    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
```

### 주석

```cpp
package algorithm;

import java.util.*;
import java.io.*;

/*
 * 1. 입력
 * - N (4 ≤ N ≤ 1,000,000) // 정수의 갯수
 * - 각각의 정수는 20억이 넘지 않는다.
 * */

public class Main {
	//variables
	static int N;
	static int[] nums;
	
	static int[] gcdLtoR;
	static int[] gcdRtoL;
	
	public static void main(String args[]) throws Exception{
		//input
		System.setIn(new FileInputStream("src/algorithm/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		gcdLtoR = new int[N];
		gcdRtoL = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		//LtoR, RtoL 초기화
		gcdLtoR[0] = nums[0];
		gcdRtoL[N - 1] = nums[N - 1];
		for(int i = 1; i < N; i++) {
			gcdLtoR[i] = gcd(nums[i], gcdLtoR[i - 1]);
		}
		for(int i = N - 2; i >= 0; i--) {
			gcdRtoL[i] = gcd(gcdRtoL[i + 1], nums[i]);
		}
		
		//calculate
		int max = 0;
		int maxIndex = 0;
		for(int i = 0; i < N; i++) {
			int gcd = 0;
			if (i == 0) {
				gcd = gcdRtoL[1];
			}
			else if (i == N - 1) {
				gcd = gcdLtoR[N - 2];
			}
			else {
				gcd = gcd(gcdLtoR[i - 1], gcdRtoL[i + 1]);
			}
			
			if (nums[i] % gcd != 0 && gcd > max) {
				max = gcd;
				maxIndex = i;
			}
		}
		
		if (max == 0) {
			System.out.println("-1");
		}
		else {
			System.out.println(max + " " + nums[maxIndex]);
		}
	}
	
	// a와 b의 인자 전달 위치는 신경쓰지 않아도 된다.
	// b > a인채로 들어온다고 해도 내부에서 a > b의 위치로 다시 조정될 것이기 때문에
	static int gcd(int a, int b) {
		while (b != 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}
}
```
