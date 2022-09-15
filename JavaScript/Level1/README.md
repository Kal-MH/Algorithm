# Level 1.

### 배열 채워넣기

```jsx
function solution(n, x) {
  return Array(n).fill(x).map((v, i) => (i + 1) * v);
}
```

### split()으로 문자열 배열 만들기

- 숫자를 문자열로 → 빈문자열 더하기
- 문자열을 숫자로 → 단항연산자 + 이용하기

```jsx
function solution(n) {
    var answer = 0;
    
    var n_str = n + '';
    var newStr = n_str.split('')
                    .sort()
                    .reverse()
                    .join('');
    return +newStr;
}
```

### 문자열 배열이 때로는 특정 문자의 숫자가 된다.

```jsx
function numPY(s){
  //함수를 완성하세요
    return s.toUpperCase().split("P").length === s.toUpperCase().split("Y").length;
}
```

### transpose

```jsx
const transpose = matrix =>
    matrix.reduce(
        (result, row) => row.map((_, i) => [...(result[i] || []), row[i]]),
        []
    );

const solution = (board, moves) => {
    const stacks = transpose(board).map(row =>
        row.reverse().filter(el => el !== 0)
    );
}
```
