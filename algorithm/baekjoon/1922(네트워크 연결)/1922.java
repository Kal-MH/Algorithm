package day1;

import java.util.*;
import java.io.*;

/*
 * 1. 입력
 * - 컴퓨터의 수 : N (1 ≤ N ≤ 1000)
 * - 연결할 수 있는 선의 수 : M (1 ≤ M ≤ 100,000)
 * */

public class Main {
	//variables
	static class Info implements Comparable<Info> {
		int start, end, cost;

		public Info(int start, int end, int cost) {
			super();
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		@Override
		public int compareTo(Info o) {
			return cost - o.cost; //오름차순 정렬
		}
	}
	
	static int N, M, ans, eCnt;
	static int[] parent;
	static PriorityQueue<Info> pq;
	
	public static void main(String args[]) throws Exception {
		//input
		System.setIn(new FileInputStream("src/day1/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		//union-find를 위한 초기화
		parent = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		//크루스칼 MST 알고리즘
		//1. 우선순위 큐 초기화(최소비용 순서로 정렬)
		pq = new PriorityQueue<Info>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			pq.add(new Info(start, end, cost));
		}
		
		ans = 0;
		eCnt = 0;
		while (!pq.isEmpty()) {
			//3. 간선 갯수 N - 1인지 체크
			if (eCnt == N - 1)
				break;
			//2. enqueue
			// - 연결되어 있으면 continue;
			// - 연결되어 있지 않으면 연결시키고 비용 합산
			Info cur = pq.poll();
			if (cur.start == cur.end)
				continue;
			if (find(cur.start) != find(cur.end)) {
				eCnt++;
				union(cur.start, cur.end);
				ans += cur.cost;
			}
		}
		
		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int find(int id) {
		if (parent[id] == id)
			return id;
		return parent[id] = find(parent[id]);
	}
	
	//연결시킨다 = 두 원소를 하나의 집합에 포함시킨다.
	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		parent[pa] = pb;
	}
}
