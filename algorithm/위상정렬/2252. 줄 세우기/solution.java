import java.util.*;

import java.io.*;

public class Main {
	//variables
	static int N, M;
	static ArrayList<Integer>[] map;
	static boolean[] visited;
	static int[] indegree;

	public static void main(String[] args) throws Exception{
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		indegree = new int[N + 1];
		for(int i = 1; i <= N; i++)
			map[i] = new ArrayList<>();
		
		int from, to;
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			
			map[from].add(to);
			indegree[to]++;
		}
		//run
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		
		//indegree initialize
		for(int i = 1; i <= N; i++) {
			if (indegree[i] == 0)
				dq.addLast(i);
		}
		
		while (!dq.isEmpty()) {
			int now = dq.pollLast();
			
			bw.write(now + " ");
			for(int next : map[now]) {
				if (indegree[next] > 0) {
					indegree[next]--;
					if(indegree[next] == 0)
						dq.addLast(next);
				}
			}
		}
		
		
		//output
		bw.flush();
		bw.close();
	}
}
