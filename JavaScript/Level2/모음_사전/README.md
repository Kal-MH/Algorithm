### **문제 설명**

사전에 알파벳 모음 **'A', 'E', 'I', 'O', 'U'만을 사용하여 만들 수 있는, 길이 5 이하의 모든 단어**가 수록되어 있습니다. 사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA"이며, 마지막 단어는 "UUUUU"입니다.

단어 하나 word가 매개변수로 주어질 때, 이 단어가 사전에서 **몇 번째 단어**인지 return 하도록 solution 함수를 완성해주세요.

### 제한사항

- word의 길이는 1 이상 5 이하입니다.
- word는 알파벳 대문자 'A', 'E', 'I', 'O', 'U'로만 이루어져 있습니다.

---

### 입출력 예

| word | result |
| --- | --- |
| "AAAAE" | 6 |
| "AAAE" | 10 |
| "I" | 1563 |
| "EIO" | 1189 |

### 입출력 예 설명

입출력 예 #1

사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA", "AAA", "AAAA", "AAAAA", "AAAAE", ... 와 같습니다. "AAAAE"는 사전에서 6번째 단어입니다.

입출력 예 #2

"AAAE"는 "A", "AA", "AAA", "AAAA", "AAAAA", "AAAAE", "AAAAI", "AAAAO", "AAAAU"의 다음인 10번째 단어입니다.

입출력 예 #3

"I"는 1563번째 단어입니다.

입출력 예 #4

"EIO"는 1189번째 단어입니다.

### 문제 풀이

문자열에 대해서 중복을 허락해서 순서가 의미있는 나열이므로 중복 순열을 이용한 풀이라고 보면 되겠다.

```tsx
function solution(word) {
    const words = ['Empty', 'A', 'E', 'I', 'O', 'U']
    let str = []; 
    
    let count = -1;
    let answer = 0;
    function rec(k) {
        if (answer) return;
        if (k === 6) return;
        
        count++;        
        if (str.join('') === word) {
            answer = count;
            return;
        }
        
        for(let i = 1; i <= 5; i++) {
            str.push(words[i]);
            rec(k + 1);
            str.pop();
        }
    }
    
    rec(0);
    
    return answer;
}
```

- count를 설정하는 것이 힘들었다. for문 안에서 count를 설정하면 되겠다고 생각했지만 왜 그런지는 모르겠는데 값이 5에서 11로 튀어버렸다.
    
    그래서 rec를 호출하는 모든 경우에 한해서 count를 증가시키고 맨 처음에는 count를 셈하면 안되기 때문에 -1이 적용된 값으로 초기 설정을 잡았다.
    
- 시간복잡도
    - 5자릿수에 대해서 5개의 알파벳이 올 수 있으므로 O(5^5)가 된다.

[출처 - 프로그래머스]
