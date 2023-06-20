### 문제 설명

양의 정수 `n`이 주어집니다. 이 숫자를 `**k`진수로 바꿨을 때**, 변환된 수 안에 아래 조건에 맞는 소수(Prime number)가 몇 개인지 알아보려 합니다.

- `0P0`처럼 소수 양쪽에 0이 있는 경우
- `P0`처럼 소수 오른쪽에만 0이 있고 왼쪽에는 아무것도 없는 경우
- `0P`처럼 소수 왼쪽에만 0이 있고 오른쪽에는 아무것도 없는 경우
- `P`처럼 소수 양쪽에 아무것도 없는 경우
- 단, `P`는 각 자릿수에 0을 포함하지 않는 소수입니다.
    - 예를 들어, 101은 `P`가 될 수 없습니다.

예를 들어, 437674을 3진수로 바꾸면 `211`0`2`01010`11`입니다. 여기서 찾을 수 있는 조건에 맞는 소수는 왼쪽부터 순서대로 211, 2, 11이 있으며, 총 3개입니다. (**211, 2, 11을 `k`진법으로 보았을 때가 아닌, 10진법으로 보았을 때 소수여야 한다는 점에 주의합니다.)** 211은 `P0` 형태에서 찾을 수 있으며, 2는 `0P0`에서, 11은 `0P`에서 찾을 수 있습니다.

정수 `n`과 `k`가 매개변수로 주어집니다. `n`을 `k`진수로 바꿨을 때, 변환된 수 안에서 찾을 수 있는 **위 조건에 맞는 소수**의 개수를 return 하도록 solution 함수를 완성해 주세요.

---

### 제한사항

- 1 ≤ `n` ≤ 1,000,000
- 3 ≤ `k` ≤ 10

---

### 입출력 예

| n | k | result |
| --- | --- | --- |
| 437674 | 3 | 3 |
| 110011 | 10 | 2 |

---

### 입출력 예 설명

**입출력 예 #1**

문제 예시와 같습니다.

**입출력 예 #2**

110011을 10진수로 바꾸면 110011입니다. 여기서 찾을 수 있는 조건에 맞는 소수는 11, 11 2개입니다. 이와 같이, 중복되는 소수를 발견하더라도 모두 따로 세어야 합니다.

### 문제 상황

조건을 보면 여러 개 있다고 하더라도 결국에는 0을 기준으로 사이에 있는 숫자를 살펴보는 것을 알 수 있다.

1. 진법을 바꾼다
2. 0을 기준으로 split한다.
3. 소수인지 판단한다.

```tsx
//풀이 1
function solution(n, k) {
    function binary (num, k) {
        const answer = [];
        
        while (num) {
            answer.push(num % k);
            
            num = Math.floor(num / k);
        }
        
        return answer.reverse().join('');
    }
    
    function determinPrime(n) {
        const num = n * 1;
        
        if (num === 1 || n === '') return false;
        if (num === 2) return true;

        for(let i = 2; i <= Math.sqrt(num); i++) {
            if (num % i === 0) return false;
        }
        return true;
    }
    
    const binaryStr = binary(n, k).split("0");
    let count = 0;
    
    for(const b of binaryStr) {
        if (determinPrime(b)) count++;
    }
    
    return count;
    
}
```

```jsx
// 풀이 2
function isPrime(num){
    if(!num || num===1) return false;
    for(let i=2; i<=+Math.sqrt(num); i++){
        if(num%i===0) return false;
    }
    return true;
}

function solution(n, k) {    
    // k진법으로 나눈 후 split
    const candidates = n.toString(k).split('0');
    // 소수 개수 세기
    return candidates.filter(v=>isPrime(+v)).length;
}
```

[출처 - 프로그래머스]
