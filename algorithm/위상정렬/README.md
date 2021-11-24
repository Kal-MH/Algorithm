## 개념

### 위상정렬 = 위상 + 정렬

- Directed Acyclic Graph(DAG)
    - Directed : 방향성이 존재
        - 방향성을 구분해서 차수를 계산해야 한다(Indegree / Outdegree)
    - Acyclic : 사이클이 없다
    - Graph : 정점 + 간선

### 위상정렬의 아이디어 : 정점들을 위상에 맞춰 정렬하자

1. 가장 먼저 올 수 있는 정점은 무엇인가?
    
    → Indegree가 0인 정점들
    
2. Indegree가 0인 정점들을 하나씩 정렬 & 그래프에서 삭제한다.
3. 이후에 새로 Indegree가 0인 정점들을 찾는다.

```java
1. 정점들의 indegree 계산
2. indegree == 0인 정점을 찾아 자료구조에 넣기
-> 알고리즘을 구성할 때, 자료구조를 미리 정하지 말자. 알고리즘 구현 후에 자료구조와 관련된
	 연산이 무엇인지 파악하고 이를 빨리 수행할 수 있는 자료구조를 택하자.
	 여기서는 **원소를 넣고**, 처음 넣은 **원소를 빼내는** 2가지의 연산을 빨리 할 수 있어야 한다.
	 = Queue 선택
3. 자료구조가 빌 때까지
	3.1 자료구조 D에서 원소 x를 꺼내서 정렬
	3.2 그래프에서 정점 x 제거
  3.3 새롭게 정렬 가능한 점(indegree == 0)을 찾아 D에 넣기
```

### Code

```java
while (!queue.isEmpty()) { //O(V)
	int x = queue.poll();
	for(int y : adj[x]) {//O(deg(x))
		indegree[y]--;
	}

	for(int i = 1; i <= N; i++) { //O(V)
		if (i가 새롭게 indegree[i] == 0이라면) //새롭게 0이 된 것인지 확인하기 위한 visited[]이 추가적으로 필요
			queue.add(i);
	}
}

-> 총 O(|V|^2)
```

개선된 풀이

```java
while (!queue.isEmpty()) { //O(V)
	int x = queue.poll();
	for(int y : adj[x]) {//O(deg(x))
		indegree[y]--;
		if (indegree[i] == 0)
			queue.add(i);
	}
}

-> 총 O(|V| + |E|)
```
