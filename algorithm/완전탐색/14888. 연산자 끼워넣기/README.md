## 문제

N개의 수로 이루어진 수열 A1, A2, ..., AN이 주어진다. 또, 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자가 주어진다. 연산자는 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)으로만 이루어져 있다.

우리는 수와 수 사이에 연산자를 하나씩 넣어서, 수식을 하나 만들 수 있다. 이때, 주어진 수의 순서를 바꾸면 안 된다.

예를 들어, 6개의 수로 이루어진 수열이 1, 2, 3, 4, 5, 6이고, 주어진 연산자가 덧셈(+) 2개, 뺄셈(-) 1개, 곱셈(×) 1개, 나눗셈(÷) 1개인 경우에는 총 60가지의 식을 만들 수 있다. 예를 들어, 아래와 같은 식을 만들 수 있다.

- 1+2+3-4×5÷6
- 1÷2+3+4-5×6
- 1+2÷3×4-5+6
- 1÷2×3-4+5+6

식의 계산은 연산자 우선 순위를 무시하고 앞에서부터 진행해야 한다. 또, 나눗셈은 정수 나눗셈으로 몫만 취한다. 음수를 양수로 나눌 때는 C++14의 기준을 따른다. 즉, 양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것과 같다. 이에 따라서, 위의 식 4개의 결과를 계산해보면 아래와 같다.

- 1+2+3-4×5÷6 = 1
- 1÷2+3+4-5×6 = 12
- 1+2÷3×4-5+6 = 5
- 1÷2×3-4+5+6 = 7

N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성하시오.

```c
N - 1개의 연산자 중에서 **중복 없이 순서 있게 나열하기**

1. 시간복잡도
- O(nPm)
- 최대 연산횟수 10! < 1억

2. 구현스케치
최대 연산횟수가 작기 때문에 모든 경우를 탐색하는 완전탐색기법을 사용할 수 있겠다.
	1) variables
		- static int min, max
		- static int[] nums, operators
		- static int[] orders -> 연산자들의 순서를 저장할 배열

	2) 함수
// order[1...N-1] 에 연산자들이 순서대로 저장될 것이다.
    static void rec_func(int k, int value) {
        if (k == N) {
            // 완성된 식에 맞게 계산을 해서 정답에 갱신하는 작업
        } else {
            // k 번째 연산자는 무엇을 선택할 것인가?
						//1. 갈 수 있는 곳 순회(1 ~ 4, 4개의 연산자)
						//2. check in -> 연산자 갯수 감소
						//3. go
						//4. check out -> 연산자 갯수 증가
        }
    }
```

## 입력

첫째 줄에 수의 개수 N(2 ≤ N ≤ 11)가 주어진다. 둘째 줄에는 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 100) 셋째 줄에는 합이 N-1인 4개의 정수가 주어지는데, 차례대로 덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수이다.

## 출력

첫째 줄에 만들 수 있는 식의 결과의 최댓값을, 둘째 줄에는 최솟값을 출력한다. **연산자를 어떻게 끼워넣어도 항상 -10억보다 크거나 같고, 10억보다 작거나 같은 결과가 나오는 입력만 주어진다. 또한, 앞에서부터 계산했을 때, 중간에 계산되는 식의 결과도 항상 -10억보다 크거나 같고, 10억보다 작거나 같다.**

int형 변수를 사용해도 될 것

## 예제 입력 1

```
2
5 6
0 0 1 0

```

## 예제 출력 1

```
30
30

```

## 예제 입력 2

```
3
3 4 5
1 0 1 0

```

## 예제 출력 2

```
35
17

```

## 예제 입력 3

```
6
1 2 3 4 5 6
2 1 1 1

```

## 예제 출력 3

```
54
-24
```

## Solutions

### Solution1.java

첫번째 solution에서는 완전탐색의 틀에 맞게 구현하였다.

다만, 목적지에 도달했을 때 (K == N, 모든 연산자 배열을 만들었을 때)  연산자에 맞게 결과값을 계산하는 부분은 rec_func의 길이를 길게 하기 때문에 calculator()라는 함수를 따로 만들어 모듈화했다.

solution1.java의 경우, **calculator() 함수 안에서 for문이 돌기 때문에 모든 경우에 대해서 O(nPm)의 시간복잡도에 연산결과를 계산하는 for문에 대한 시간복잡도도 더해진다.**

```c
//각각 연산자 순서대로 계산
	static int calculator() {
		int ans = nums[1];
		
		for(int i = 1; i < N; i++) {
			if (selected[i] == 1)
				ans += nums[i + 1];
			if (selected[i] == 2)
				ans -= nums[i + 1];
			if (selected[i] == 3)
				ans *= nums[i + 1];
			if (selected[i] == 4)
				ans /= nums[i + 1];
		}
		return (ans);
	}
	
	static void rec_func(int k) {
		//1. 목적지인가
		if (k == N) {
			//반환받은 value의 값으로 min, max갱신
			int value = calculator();
			
			min = Math.min(min, value);
			max = Math.max(max, value);
		} else {
			//2. 갈 수 있는 곳 순회
			for(int i = 1; i < 5; i++) {
				if (operators[i] > 0) {
					//3. check in
					operators[i]--;
					selected[k] = i;
					rec_func(k + 1);
					operators[i]++;
					selected[k] = 0;
				}
			}
		}
	}
```

### Solution2.java

calculator() 안에서의 반복을 얻애기 위해서 rec_func함수를 호출할 때마다 K번째까지 계산한 결과값을 인자로 받도록 했다.

매 연산자를 선택할 때마다 해당 연산자에 대한 연산도 같이 수행된다.

```java
//각각 연산자 순서대로 계산
	static int calculator(int operand1, int operator, int operand2) {
		if (operator == 1)
			return (operand1 + operand2);
		else if (operator == 2)
			return (operand1 - operand2);
		else if (operator == 3)
			return (operand1 * operand2);
		else
			return (operand1 / operand2);
	}
	
	static void rec_func(int k, int value) {
		//1. 목적지인가
		if (k == N) {
			//반환받은 value의 값으로 min, max갱신			
			min = Math.min(min, value);
			max = Math.max(max, value);
		} else {
			//2. 갈 수 있는 곳 순회
			for(int i = 1; i < 5; i++) {
				if (operators[i] > 0) {
					//3. check in
					operators[i]--;
					int new_value = calculator(value, i, nums[k + 1]);
					rec_func(k + 1, new_value);
					operators[i]++;
				}
			}
		}
	}
```
