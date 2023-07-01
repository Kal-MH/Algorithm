문제 설명
첫 번째 분수의 분자와 분모를 뜻하는 numer1, denom1, 두 번째 분수의 분자와 분모를 뜻하는 numer2, denom2가 매개변수로 주어집니다. 두 분수를 더한 값을 기약 분수로 나타냈을 때 분자와 분모를 순서대로 담은 배열을 return 하도록 solution 함수를 완성해보세요.

제한사항
0 <numer1, denom1, numer2, denom2 < 1,000
입출력 예
numer1	denom1	numer2	denom2	result
1	2	3	4	[5, 4]
9	2	1	3	[29, 6]
입출력 예 설명
입출력 예 #1

1 / 2 + 3 / 4 = 5 / 4입니다. 따라서 [5, 4]를 return 합니다.
입출력 예 #2

9 / 2 + 1 / 3 = 29 / 6입니다. 따라서 [29, 6]을 return 합니다.

### 문제 풀이

```jsx
function solution(numer1, denom1, numer2, denom2) {
    const answer = [];
    
    let numer = numer1 * denom2 + numer2 * denom1;
    let denom = denom1 * denom2;
    
    const bound = numer < denom ? numer : denom;
    let maxDivisor = 1;
    for(let i = 2; i <= bound; i++) {
        if (numer % i === 0 && denom % i === 0) {
            maxDivisor = i;
        }
    }
    
    return [numer/maxDivisor, denom/maxDivisor];
}
```

- 가장 중요한 것은 최대 공약수로 나눠주는 것!
