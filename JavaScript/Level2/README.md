## 2022.09.15

### charAt vs index

- charAt의 경우, ECMA 5에 추가된 문법으로 오래된 브라우저에서는 작동이 안될 수 있다.
- charAt의 경우, 배열 범위를 벗어난 인덱스로 접근하는 경우, empty string을 반환한다.
- index의 경우, 배열 범위를 벗어나면 undefined를 반환한다.

```
  'hello'[NAN]; //undefined
  'hello'.charAt(NAN) // 'hello'.charAt(0) -> 'h'
  
  'hello'[undefined]; //undefined
  'hello'.charAt(undefined) // 'hello'.charAt(0) -> 'h'
  
  ...
```

[[thisthat.dev/charAt vs index]](https://thisthat.dev/string-char-at-vs-string-bracket-notation/)
