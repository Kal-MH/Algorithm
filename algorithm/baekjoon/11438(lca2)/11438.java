package algorithm;

import java.util.*;
import java.io.*;

public class Main {
	//variables
	static int N, M, K;

	// LCA 관련 변수
	static int[] depth;
	static int[][] parent; // parent[K][V] 정점 V의 2^K번째 조상 정점 번호
							// parent[K][V] = parent[K-1][parent[K-1][V]];

	// TREE 변수
	static ArrayList[] tree;
	public static void main(String args[]) throws Exception {
		//input
		System.setIn(new FileInputStream("src/algorithm/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		// 1. 입력 & 변수 준비
		N = Integer.parseInt(br.readLine());

		// 2^K > N인 K 찾기
		K = 0;
		for (int i = 1; i <= N; i *= 2) {
			K++;
		}
		System.out.println(K);

		// LCA 관련 변수 초기화
		depth = new int[N + 1];
		parent = new int[K][N + 1];

		// TREE 변수 초기화
		tree = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<Integer>();
		}

		int a,b;
		for (int i = 1; i <= N-1; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			// 양방향 간선
			tree[a].add(b);
			tree[b].add(a);
		}
		
		// 2. DEPTH 확인
		dfs(1, 1);
		
		// 3. 2^N 까지 parent 채우기
		fillParent();
		
		// 4. LCA 진행
		M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			sb.append(lca(a,b)+"\n");
		}

		bw.write(sb.toString());

		bw.flush();
		bw.close();
		br.close();
	}
	
	// DEPTH 확인 DFS
	static void dfs(int id, int cnt) {
		// 1. depth를 기록
		depth[id] = cnt;
		
		// 2. 자식들의 depth를 기록
		int len = tree[id].size();
		for (int i = 0; i < len; i++) {
			int next = (Integer)tree[id].get(i);
			// 아직 깊이를 모르면 → 미방문 노드
			if (depth[next] == 0) {
				dfs(next, cnt+1);	
				//next 바로 위에 있는 부모는 id이다.		
				parent[0][next] = id;		// 첫번째 부모를 기록
			}
		}
		return;
	}
	
	// 부모 채우기
	static void fillParent() {
		for (int i = 1; i<K; i++) {
			for (int j = 1; j<=N; j++) {
				parent[i][j] = parent[i-1][parent[i-1][j]];
			}
		}
	}
	
	// 최소공통조상
	static int lca(int a, int b) {
		// 1. depth[a] >= depth[b] 이도록 조정하기
		if (depth[a] < depth[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		// 2. 더 깊은 a를 2^K승 점프하여 depth를 맞추기
		for (int i = K-1; i>=0; i--) {
			if (Math.pow(2, i) <= depth[a] - depth[b]) {
				a = parent[i][a];
			}
		}
		
		// 3. depth를 맞췄는데 같다면 종료
		if (a == b) return a;
		
		// 4. a-b는 같은 depth이므로 2^K승 점프하며 공통부모 바로 아래까지 올리기
		for (int i = K-1; i >= 0; i--) {
			//트리를 벗어나서 조상을 참조할 때는 이미 0으로 초기화되었음으로 0을 반환하게 된다.
			//서로 같다가 달라진다면 달라지는 그 노드로 바로 이동하게 된다.
			//바로 위가 공통조상 (parent[0][a])
			if (parent[i][a] != parent[i][b]) {
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		return parent[0][a];
	}
}
