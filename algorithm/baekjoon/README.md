## 좋은 습관

1. 문제를 올바른 순서로 푼다.

    ```c
    1) 읽기
    - 시간, 메모리 제한, 문제 전체의 Goal을 꼼꼼히
    2) 이해하기
    - 제공되는 정보(변수들) 정리
    - 예제 데이터에 대한 이해
    3) 파악하기
    - 가능한 최대, 최소 정답에 맞는 데이터를 직접 생성
    - 키워드가 되는 단어 파악
    ```

2. 시간과 공간 복잡도를 계산한다.
    1. 시간, 공간 복잡도를 미리 계산함으로 구현할 때 사용할 알고리즘을 미리 걸러낼 수 있다.

        시간 아끼기, 짤 가치가 있는 알고리즘인지 

        ```c
        N이 10만
        - O(N^2)의 시간 복잡도가 소요된다고 할 때, 총 100억으로 시간초과 발생할 수 있음
        - O(NlogN)으로 약 180만의 연산 소요, 짤 가치가 있는 알고리즘이라고 생각할 수 있음. 
        ```

3. 함수화
4. 부분점수를 생각한다.