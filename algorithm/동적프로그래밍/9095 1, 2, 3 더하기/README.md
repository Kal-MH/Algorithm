## Solution

### 풀고 싶은 가짜 문제 정의

진짜 문제를 먼저 써보고, 가짜 문제로 바꿔보자.

- 진짜 문제 : 주어진 N에 대해서 1, 2, 3의 합으로 표현하는 경우의 수
- 가짜 문제 : j에 대해서 1, 2, 3의 합으로 표현하는 경우의 수

### 가짜 문제를 풀면 진짜 문제를 풀 수 있는가

배열을 가득 채운다고 한다면 주어진 N에 대한 1, 2 3의 합으로 표현하는 경우의 수는 D[N]의 값과 같다.

### 초기값

![초기값](https://user-images.githubusercontent.com/59648372/164449749-cc2d9c47-3d49-4225-9f52-de41a6d384cb.png)


### 점화식

<aside>
💡 D[i] = D[i - 3] + D[i - 2] + D[i - 1]

</aside>

![경우](https://user-images.githubusercontent.com/59648372/164449803-95e6b9cb-3743-4288-b333-5acdcfe803ed.png)


(출처 - 패스트캠퍼스 류호석 알고리즘)
