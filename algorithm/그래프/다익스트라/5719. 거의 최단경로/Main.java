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
	static int[] distance;
	static final int INF = Integer.MAX_VALUE;
	
	static boolean[][] isShortest;
	static ArrayList<Integer>[] edges;
	static int destination;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/input.txt"));
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			if (V == 0 && E == 0)
				break;
			
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			destination = Integer.parseInt(st.nextToken());
			
			map = new ArrayList[V + 1];
			distance = new int[V + 1];
			edges = new ArrayList[V + 1];
			isShortest = new boolean[V + 1][V + 1];
			
			for(int i = 0; i < V; i++) {
				map[i] = new ArrayList<>();
			}
			int a, b, c;
			for(int i = 1; i <= E; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				
				map[a].add(new Info(b, c));
			}
			
			findShortestPath(start);
			if (distance[destination] == INF) {
				bw.write("-1\n");
				continue;
			}
			findShortestEdge(start, destination);
			findShortestPath(start);
			
			if (distance[destination] == INF) {
				bw.write("-1\n");
			} else
				bw.write(distance[destination] + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	static void findShortestEdge(int start, int end) {
		if (start == end)
			return ;
		for(int preNext : edges[end]) {
			if (isShortest[preNext][end] == false) {
				isShortest[preNext][end] = true;
				findShortestEdge(start, preNext);
			}
		}
	}
	static void findShortestPath(int start) {
		for(int i = 0; i < V; i++) {
			edges[i] = new ArrayList<>();
		}
		Arrays.fill(distance, INF);
		
		PriorityQueue<Info> pq = new PriorityQueue<Main.Info>();
		distance[start] = 0;
		pq.add(new Info(start, 0));
		
		while (!pq.isEmpty()) {
			Info now = pq.poll();
			
			if (now.distance > distance[now.node])
				continue;
			
			for(Info next : map[now.node]) {
				if (isShortest[now.node][next.node])
					continue;
				if (distance[next.node] > distance[now.node] + next.distance) {
					edges[next.node].clear();
					edges[next.node].add(now.node);
					distance[next.node] = distance[now.node] + next.distance;
					pq.add(new Info(next.node, distance[next.node]));
				}
				if (distance[next.node] == distance[now.node] + next.distance)
					edges[next.node].add(now.node);
			}
		}
	}
}
