package day1;

import java.util.*;
import java.io.*;

/*
 * 
 * */
public class Main {
	static int N, M;
	static int[] parent;
	static long[] dist;
	static String type;
	static int a, b, w;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/day1/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();

		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// N, M이 0, 0 이면 종료
		for (; N != 0 && M != 0;) {
			// Union Find를 위한 parent 초기화
			parent = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				parent[i] = i;
			}
			dist = new long[N + 1];

			System.out.println("-------------------------");
			for(int j = 1; j <= N; j++) {
				System.out.print(dist[j] + " (" + parent[j] + ")");
			}
			System.out.println("");
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				type = st.nextToken();

				// 1. 무게를 쟀을 경우 - 판단 가능한 그룹으로 union
				if (type.equals("!")) {
					a = Integer.parseInt(st.nextToken());
					b = Integer.parseInt(st.nextToken());
					w = Integer.parseInt(st.nextToken());

					union(a, b, w);
				}
				// 2. 무게를 판단하는 경우
				else {
					a = Integer.parseInt(st.nextToken());
					b = Integer.parseInt(st.nextToken());

					// 2-1. 무게를 판단할수 없는 경우 UNKNOWN
					if (find(a) != find(b)) {
						sb.append("UNKNOWN\n");
					}
					// 2-2. 무게를 판단할 수 있는 경우
					else {
						sb.append((dist[b] - dist[a]) + "\n");
					}
				}
				System.out.println("-------------------------");
				for(int j = 1; j <= N; j++) {
					System.out.print(dist[j] + " (" + parent[j] + ")");
				}
				System.out.println("");
			}

			// 다음 테스트 케이스의 N, M 입력받기
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
		}

		bw.write(sb.toString());

		bw.flush();
		bw.close();
		br.close();
	}

	static int find(int id) {
		// 1. root인 경우 기존 union find 동일
		if (parent[id] == id)
			return id;
		// 2. root가 아닌 경우 root와의 거리를 구해서 갱신
		//union을 통해서 parent가 root로 바로 설정되어 있기 때문에 find를 여러 번 호출하더라도 0이 더해져서 dist값이 변하진 않는다.
		//만약, 루트가 변하는 경우에는 새로운 루트로부터 기존 루트 사이의 차이를 새로 더하고 새로운 루트로 다시 바꿔준다.
		int pId = find(parent[id]);
		dist[id] += dist[parent[id]];
		return parent[id] = pId;
	}

	static void union(int a, int b, long diff) {
		int pa = find(a);
		int pb = find(b);

		// 이미 같은 그룹이면 거리 계산이 되어있으므로 종료
		if (pa == pb)
			return;

		// parent를 변경하고, 무게 차이를 갱신
		dist[pb] = dist[a] - dist[b] + diff;
		parent[pb] = pa;

		return;
	}
}
