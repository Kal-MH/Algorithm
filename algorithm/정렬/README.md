## 정렬 조건(java 기준)

### 1. 정렬 조건이 필요하다

```java
static class Elem implements Comparable<Elem> {
	...

	@Override
	public int compareTo(Elem other) {
		return (num - other.num);
	}
}
```

나를 기준으로 정렬이 이뤄진다고 생각하자. 정렬된 데이터를 놓기 위해 나와 other 원소가 있다고 할 때, compareTo()함수의 반환값이,

- 음수라면, **내**가 놓이게 된다.
- 양수라면, **other**가 놓이게 된다.
- 0이라면, 누가 놓이든 상관이 없다(동급이니까)

### 2. 시간 복잡도는 약 O(NlogN)

- Primitive 원소 정렬
    
    ```java
    - Dual-Pivot Quick Sort를 사용
    - 최선 O(N)
    - 최악 O(N^2)
    - 평균 O(NlogN)
    ```
    
- Object 원소 정렬
    
    ```java
    - Time Sort를 사용
    - 최선 O(N)
    - 최악 O(NlogN)
    - 평균 O(NlogN)
    ```
    

### 3. In-place/ Stable 여부를 알아야 한다.

- In-place란,
    
    정렬 과정에서 N에 비해 충분히 무시할 만한 메모리 공간을 추가적으로 사용한다면 In-place하다고 한다. 만약 N이 10만일 때, 10의 메모리 공간을 추가적으로 더 사용한다면 In-place하다고 말할 수 있다.
    
    ex) primitive 정렬할 때 사용되는 Dual-Pivot Quick Sort는 In-place하다.
    
- Stable
    
    동등한 계급(위상)의 원소들의 순서관계가 보존되는 것을 말한다.  예를 들어, 1번, 2번자리에 모두 값이 5인 원소가 들어있다면 정렬이 끝났을 때, 1번자리에 있던 5가 2번자리에 있던 5보다 더 앞에 있을 때 해당 정렬은 Stable하다고 말한다.
    
    ex) Object 원소 정렬할 때 사용되는 Tim sort의 경우, selection, merge sort를 사용하기 때문에 Stable하다.
    

## 정렬 특성

1. 같은 정보들은 서로 인접해있다.
2. 각 원소마다, 현재 자기 자신과 가장 가까운 값의 원소는 양 옆에 있다.
