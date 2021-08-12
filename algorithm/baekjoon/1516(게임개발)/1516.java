package algorithm;

import java.util.*;
import java.io.*;

public class Main {
	//variables
	
	static int N;
	static Building[] buildingList;
	static int input;
	static Queue<Integer> queue;
	static StringBuilder sb;
	
	//입력으로 들어오는 빌딩클래스
	public static class Building {
		int time;
		int indegree;
		int ans;
		//내가 먼저 지어져야 지어질 수 있는 건물들의 정보를 저장.
		ArrayList<Integer> adjList;
		
		public Building() {
			this.time = 0;
			this.indegree = 0;
			this.ans = 0;
			this.adjList = new ArrayList<>();
		}
	}
	public static void main(String args[]) throws Exception {
		//input
		System.setIn(new FileInputStream("src/algorithm/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//1) N개의 건물 정보 초기화
		N = Integer.parseInt(br.readLine());
		buildingList = new Building[N + 1];
		for(int i = 1; i <= N; i++) {
			buildingList[i] = new Building();
		}
		
		StringTokenizer st;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			buildingList[i].time = Integer.parseInt(st.nextToken());
			
			input = Integer.parseInt(st.nextToken());
			while (input != -1) {
				buildingList[i].indegree++;
				buildingList[input].adjList.add(i);
				input = Integer.parseInt(st.nextToken());
			}
		}
		
		//위상정렬 초기화
		queue = new ArrayDeque<Integer>();
		for(int i = 1; i <= N; i++) {
			if (buildingList[i].indegree == 0)
				queue.add(i);
		}
		
		//위상정렬
		while (!queue.isEmpty()) {
			//1. dequeue
			// = 현재 자기자신을 짓고 있다는 의미
			// = 자기자신을 짓는 시간을 더해준다.
			int id = queue.poll();
			buildingList[id].ans += buildingList[id].time;
			//2. 목적지인가
			//3. 갈 수 잇는 곳 순회
			// - buildingList[id].adjList = 나를 지어야 자신을 지을 수 있는 건물들의 리스트
			// - 나를 지었으니 다른 건물도 지을 수 있다고 알려준다.
			for(int adjId : buildingList[id].adjList) {
				//4. 갈 수 있는가
				//5. check in
				buildingList[adjId].indegree--;
				//buildingList[adjId].ans = adjId인 건물이 짓기 위해 선행되어야 하는 모든 건물들을 짓는 시간 합
				//buildingList[id].ans = id인 건물을 짓기 위해 id 건물을 포함해서 모든 건물들을 짓는 시간 합
				//1) 만약 id = 3일 때, adjId(4) 건물의 선행되어야 하는 건물들이 모두 선행관계가 있다면 
				// ex) 1->2->3->4 와 같은 관계
				// buildingList[adjId].ans는 1, 2를 짓는 모든 시간의 합이 되고
				// buildingList[id].ans는 1, 2, 3을 짓는 모든 시간의 합이 됨으로 max 함수를 이용해 더 큰 값을 취한다.
				//2) 만약, 모두 선행관계에 있지 않고 동시에 지어질 수 있다면,
				// ex) 1->2->4, 3->4
				// buildingList[adjId].ans는 1, 2를 짓는 모든 시간의 합
				// buildingList[id].ans는 3을 짓는 시간의 합이 되고, 이는 동시에 지어질 수 있으므로
				// 1)과 똑같이 둘 중의 max 값을 취해야 한다
				// 결국 모두 max값을 취해야 한다.
				buildingList[adjId].ans = Math.max(buildingList[adjId].ans, buildingList[id].ans);
				//6. enqueue	
				if (buildingList[adjId].indegree == 0)
					queue.add(adjId);
			}
		}
		sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			sb.append(buildingList[i].ans + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
