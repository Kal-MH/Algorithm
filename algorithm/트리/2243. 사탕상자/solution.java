import java.util.*;
import java.io.*;

public class Main {
	//variables
	static int N;
	static int[] candies;
	static int[] tree;
	static int S;
	static final int MAX = 1000000;

	public static void main(String[] args) throws Exception {
		// input setting
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//input
		N = Integer.parseInt(br.readLine());
		candies = new int[N];
		
		//initialize
		S = 1;
		while (S < MAX)
			S *= 2;
		tree = new int[2 * S];
		
		//run
		int command, value1, value2;
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			command = Integer.parseInt(st.nextToken());
			
			if (command == 1) {
				value1 = Integer.parseInt(st.nextToken());
				
				bw.write(query(1, S, 1, value1) + "\n");
			} else if (command == 2) {
				value1 = Integer.parseInt(st.nextToken());
				value2 = Integer.parseInt(st.nextToken());
				
				update(1, S, 1, value1, value2);
			}
		}
		
		//output
		bw.flush();
		bw.close();
		br.close();
	}
	static void update(int left, int right, int node, int target, int diff) {
		if (target < left || right < target)
			return ;
		else  {
			
			tree[node] += diff;
			
			if (left != right) {
				int mid = (left + right) / 2;
				update(left, mid, node * 2, target, diff);
				update(mid + 1, right, node * 2 + 1, target, diff);
			}
		}
	}

	static int query(int left, int right, int node, int target) {
		if (left == right) {
			// 사탕 수 1개씩 모두 감소
			update(1, S, 1, left, -1);
			//target번째 사탕의 맛 인덱스를 반환
			return (left);
		}
		//왼쪽에 먼저 물어봄
		// - value가 target보다 크다면 그대로 왼쪽에 물어본다.
		// - value가 target보다 작다면 target - query left value 해서 오른쪽으로 물어본다.
		int mid = (left + right) / 2;

		int leftValue = tree[node * 2];
		if (leftValue >= target) {
			return query(left, mid, node * 2, target);
		} else {
			return (query(mid + 1, right, node * 2 + 1, target - leftValue));
		}
	}
}
