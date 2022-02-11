import java.util.*;

import java.io.*;


public class Main {
	static class Info implements Comparable<Info> {
		int node;
		int distance;
		
		public Info(int node, int distance) {
			this.node = node;
			this.distance = distance;
		}

		@Override
		public int compareTo(Info o) {
			return (distance - o.distance);
		}
	}
	
	static int V, E;
	static int start;
	static ArrayList<Info>[] map;
	//static int[] distance;
	static final int INF = Integer.MAX_VALUE;
	
	static boolean[][] isShortest;
	static ArrayList<Integer>[] edges;
	static int destination;
	
	static PriorityQueue<Integer>[] distance;
	static int K;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/input.txt"));
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[V + 1];
		distance = new PriorityQueue[V + 1];
		for(int i = 1; i <= V; i++) {
			map[i] = new ArrayList<>();
			distance[i] = new PriorityQueue<>(Collections.reverseOrder());
		}
		
		int a, b, c;
		for(int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			map[a].add(new Info(b, c));
		}
		
		findShortestPath(1);
		for(int i = 1; i <= V; i++) {
			if (distance[i].size() == K) {
				bw.write(distance[i].peek() + "\n");
			} else
				bw.write("-1\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	static void findShortestPath(int start) {
		PriorityQueue<Info> pq = new PriorityQueue<Main.Info>();
		distance[start].add(0);
		pq.add(new Info(start, 0));
		
		while (!pq.isEmpty()) {
			Info now = pq.poll();
			
			if (now.distance > distance[now.node].peek())
				continue;
			
			for(Info next : map[now.node]) {
				if (distance[next.node].size() < K) {
					distance[next.node].add(now.distance + next.distance);
					pq.add(new Info(next.node, now.distance + next.distance));					
				} else {
					if (distance[next.node].peek() > now.distance + next.distance) {
						distance[next.node].poll();
						distance[next.node].add(now.distance + next.distance);
						pq.add(new Info(next.node, now.distance + next.distance));
					}
				}
			}
		}
	}
}
