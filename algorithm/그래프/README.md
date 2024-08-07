## 개념

### 그래프란,

자료구조로써의 그래프 = 정점(Vertex) + 간선(Edge)

1. 간선의 종류
    1. 무방향
    2. 방향
    3. (무방향 / 방향) + 가중치
2. degree(차수)
    
    deg(x)란, 정점 x의 차수(degree)를 말한다. 즉, 정점x에 연결된 간선의 수를 의미한다.
    
    <aside>
    💡 모든 정점의 차수 합  = 간선 x 2
    
    </aside>
    

### 그래프를 저장하는 2가지 방법

1. 인접행렬 (Adjacency Matrix)
    
    ```jsx
    int[][] adj = new int[V][V];
    ```
    
    - A에서 B로 이동이 가능한가? (+ 가중치는 얼마인가?) : O(1)
    - 정점 A에서 갈 수 있는 모든 정점은? 혹은 갯수는? : O(V)
    - 공간복잡도
        
        인접행렬의 경우, O(V^2)만큼의 공간 필요하기 필요하기 때문에 만약, V = 10만이라고 하면 10만 x 10만의 공간(10기가)이 필요하고 만약 int형 배열이라면, 총 40기가의 공간이 필요함으로 **공간낭비**가 생길 수 있다.
        
2. 인접리스트 (Adjacency List)
    
    ```jsx
    ArrayList<ArrayList<Integer>> adj;
    ```
    
    - A에서 B로 이동이 가능한가? (+ 가중치는 얼마인가?) : O(min(deg(A), deg(B)))
        
        (차수가 더 작은 정점에서 탐색을 진행하면 된다. )
        
    - 정점 A에서 갈 수 있는 정점은? : O(deg(A))
    - 공간복잡도
        
        O(E)만큼의 공간이 필요하다. 만약 V = 10만, E = 50만이라고 한다면 500K만큼의 공간만 사용하면 된다.
        

### 그래프 문제의 핵심

- 정점, 간선에 대한 정확한 정의
- 간선 저장 방식을 확인하기(정하기)

### 그래프에서의 탐색이란?

그래프 상에서의 탐색이란, **시작점**에서 간선을 0개 이상 사용해서 갈 수 있는 **정점들은 무엇인지** 순회하는 것을 말한다.

(시간복잡도에 대해서, 인접리스트로 구현할 경우, O(E)인지, O(V+E)인지 일치하지 않는 부분이 있어 추가로 블로그 링크를 남긴다.[[링크]](https://adela.love/posts/dfs-and-bfs)[[링크2]](https://velog.io/@kjh107704/%EA%B7%B8%EB%9E%98%ED%94%84-BFS%EC%99%80-DFS))

1. 깊이 우선 탐색(DFS)
    
    ```java
    // x 를 갈 수 있다는 걸 알고 방문한 상태
    static void dfs(int x) {
    	//x를 방문함
    	visited[x] = true;
    	
    	//x에서 갈 수 있는 곳 모두 방문
    	for(int y: x에서 갈 수 있는 점들) {
    		if (visited[y])
    			continue;
    		//간다.
    		dfs(y);
    	}
    }
    ```
    
    - 시간복잡도
        - 모든 정점 x에 대해서 각각은 한 번만 재귀호출이 이뤄진다 : O(V)
        - for문을 통해서 갈 수 있는 곳을 방문한다. : O(V) - 인접행렬 / O(deg(x)) - 인접리스트
        - 총 시간복잡도 : O(V^2) - 인접행렬 / O(V + E) - 인접리스트
            - for문을 통해서 정점 x에 대해서 갈 수 있는 곳을 모두 순회하기 때문에 인접행렬보다는 인접리스트가 더 적합한 자료구조가 될 수 있다고 말할 수 있다.
2. 너비 우선 탐색(BFS)
    
    ```java
    // start에서 시작해서 갈 수 있는 정점들 모두 탐색
    static void bfs(int x) {
    	Queue<Integer> que = new LinkedList<>();
    
    	//start는 방문 가능한 점이므로 큐에 넣어둔다.
    	que.add(start);
    	visited[x] = true;
    	
    	//x에서 갈 수 있는 곳 모두 방문
    	while(!que.isEmpty()) {
    		int x = que.poll(); //모든 정점이 한 번씩 큐에서 enqueue된다 : O(V)
    		for(int y: x에서 갈 수 있는 점들) {//O(V) - 인접행렬 / O(deg(x)) - 인접리스트
    			if (visited[y])
    				continue;
    			//간다.
    			que.add(y);
    			visited[y] = true; //visit를 기록해두지 않으면 같은 정점들이 여러번 큐에 등록될 수 있다.
    	}
    }
    ```
    
    - 큐의 의미
        - 큐에 정점이 남아있다 = 아직 방문 가능한 점이 남아있다 or 탐색중이다
        - 큐가 비었다 = 시작점에서 갈 수 있는 모든 점을 찾아냈다 or 탐색이 끝났다.
    - 총 시간복잡도 : O(V^2) - 인접행렬 / O(V + E) - 인접리스트
