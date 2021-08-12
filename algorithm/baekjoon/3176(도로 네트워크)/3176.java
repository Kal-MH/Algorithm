package algorithm;

import java.util.*;
import java.io.*;

/*
 * 1. 입력
 * - N : 도시 수 (2 ≤ N ≤ 100,000)
 * - 도로의 길이 <= 1,000,000
 * - K : 출력개수 <= 100,000
 * 2. 시간복잡도
 * - 만약 하나의 노드에 N - 1개의 최대 간선이 있다고 할 때, 완전탐색을 하게 된다면
 *   매번 N개를 살펴보는 연산을 K번 반복해야 된다. 따라서 최대 10만 x 10만이 됨으로 시간초과 될 수 있음.
 * - LCA 사용
 * 		1) O(logN)으로 줄어듦
 * 
 * */

public class Main {
	//variables
	static int N, M, K;
	
	static int[] depth;
	static int[][] parent;
	
	static ArrayList[] tree;
	
	static int[][] minDist;
	static int[][] maxDist;
	
	static int min, max;
	
	static class Edge{
		int target, cost;
		
		public Edge(int target, int cost) {
			this.target = target;
			this.cost = cost;
		}
	}
	
	public static void main(String args[]) throws Exception {
		//input
		System.setIn(new FileInputStream("src/algorithm/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		K = 0;
		for(int i = 1; i <= N; i *= 2)
			K++;
		
		depth = new int[N + 1];
		parent = new int[K][N + 1];
		
		minDist = new int[K][N + 1];
		maxDist = new int[K][N + 1];
		
		tree = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<Edge>();
		}
		
		int a, b, c;
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			tree[a].add(new Edge(b, c));
			tree[b].add(new Edge(a, c));
		}
		
		//1. parent[][] 0번째 줄 초기화
		dfs(1, 1);
		//2. parent[][] 나머지 부분 초기화
		fillParent();
		
		//LCA 진행
		M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			lca(a, b);
			sb.append(min + " " + max + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void dfs(int id, int cnt) {
		depth[id] = cnt;
		
		int len = tree[id].size();
		for(int i = 0; i < len; i++) {
			Edge next = (Edge)tree[id].get(i);
			
			//Edge next는 현재 tree[id]의 자식이라고 보면 된다.
			// next.target은 tree[id]의 현재 자식 이름이라고 생각할 수 있겠다.
			if (depth[next.target] == 0) {//현재 자식이 depth가 기록되지 않았다면
				dfs(next.target, cnt + 1);
				//next 바로 위에 있는 부모는 id이다.
				parent[0][next.target] = id;
				
				//현재 자식의 바로 위에 있는 부모(tree[id])와의 cost는 각각 next.cost가 된다.(초기화)
				minDist[0][next.target] = next.cost;
				maxDist[0][next.target] = next.cost;
			}
		}
	}
	
	static void fillParent() {
		for(int i = 1; i < K; i++) {
			for(int j = 1; j <= N; j++) {
				parent[i][j] = parent[i - 1][parent[i - 1][j]];
				
				//j번째 노드에서 2^i번째에 있는 조상에게 가기까지 가장 짧은 cost와 큰 cost를 기록하고 있는 중
				// j번째 -(1)- 2^(i - 1)번째 조상 -(2)- 2^i번째 조상 이라고 했을 때
				// minDist[i - 1][j] = (1)
				//					 = j번째 노드부터 s^(i - 1)번째 조상에게 가기까지 가장 짧은 cost
				// minDist[i - 1][minDist[i - 1][j]] = (2)
				//					 = 2^(i - 1)번째 조상으로부터 2^i번째 조상까지 가장 짧은 cost
				minDist[i][j] = Math.min(minDist[i - 1][j], minDist[i - 1][parent[i - 1][j]]);
				maxDist[i][j] = Math.max(maxDist[i - 1][j], maxDist[i - 1][parent[i - 1][j]]);
			}
		}
		
	}
	
	static void lca(int a, int b) {
		if (depth[a] < depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		min = Integer.MAX_VALUE;
		max = -1;
		
		for(int i = K - 1; i >= 0; i--) {
			if (Math.pow(2, i) <= depth[a] - depth[b]) {
				//a의 자리가 바뀌기 전에 최소값, 최댓값을 저장해둔다.
				min = Math.min(min, minDist[i][a]);
				max = Math.max(max, maxDist[i][a]);
				
				a = parent[i][a];
			}
		}
		
		//a == b가 된다면
		// b가 a의 조상이었다는 의미가 된다.
		// = 따라서 a가 b의 자리로 이동하기 까지의 min, max가 그대로 구하고자 하는 값이 된다.
		if (a == b) return ;
		
		for(int i = K - 1; i >= 0 ; i--) {
			if (parent[i][a] != parent[i][b]) {
				//a, b의 자리를 이동하기 전 
				//a, b의 cost 중 가장 작은값(큰 값)을 가지고서 현재 min, max와 비교해 값을 갱신한다.
				min = Math.min(min, Math.min(minDist[i][a], minDist[i][b]));
				max = Math.max(max, Math.max(maxDist[i][a], maxDist[i][b]));
				
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		
		//현재 a, b가 공통조상 바로 아래까지 와 있는 상태
		//마지막으로 공통조상과의 cost를 기존 min, max와 비교한다.
		min = Math.min(min, Math.min(minDist[0][a], minDist[0][b]));
		max = Math.max(max, Math.max(maxDist[0][a], maxDist[0][b]));
		
		return ;
	}
}
