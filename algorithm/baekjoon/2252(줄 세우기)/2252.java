package day1;

import java.util.*;
import java.io.*;

/*
 * 1. 그래프로 추상화
 * - 사람은 정점, 키 작은 사람에서 키 큰 사람으로 방향이 있는 간선을 그리면 그래프로 추상화할 수 있다.
 * */

public class Main {
	//variables
	static int N, M, cnt;
	
	static int[] indegree;
	static ArrayList[] edge; //간선배열을 2차원으로 선언
	static Queue<Integer> queue;
	static int[] answer;
	
	public static void main(String args[]) throws Exception {
		//input
		System.setIn(new FileInputStream("src/day1/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		indegree = new int[N + 1];
		answer = new int[N + 1];
		
		//간선 초기화
		edge = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			edge[i] = new ArrayList<Integer>();
		}
		queue = new LinkedList<Integer>();
		
		// - 그래프 그리기
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			edge[a].add(b);
			indegree[b]++;
		}
		
		//위상정렬
		// - BFS와 비슷한 면을 가진다.
		//1. 초기화 : indegree가 0인 정점을 큐에 집어넣는다.
		for(int i = 1; i <= N; i++) {
			if (indegree[i] == 0)
				queue.add(i);
		}
		//BFS
		while (!queue.isEmpty()) {
			//1. dequeue
			int id = queue.poll();
			
			answer[cnt] = id;
			cnt++;
			//2. 목적지인가
			//3. 갈 수 있는 곳 순회
			int size = edge[id].size();
			//4. 갈 수 있는가
			for(int i = 0; i < size; i++) {
				//5. check in
				int target = (int)edge[id].get(i);
				indegree[target]--;
				//6. enqueue
				if (indegree[target] == 0)
					queue.add(target);
			}
		}
		
		for(int i = 0; i < cnt; i++) {
			bw.write(String.valueOf(answer[i]) + " ");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
