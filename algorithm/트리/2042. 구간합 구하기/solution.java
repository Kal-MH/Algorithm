package algorithm;

import java.util.*;
import java.io.*;

/*
 * 1. 입력
 * - N(1 ≤ N ≤ 1,000,000, 전체 숫자 갯수)
 * - M(1 ≤ M ≤ 10,000, 수의 변경 횟수)
 * - K(1 ≤ K ≤ 10,000, 구간합 구하는 횟수)
 * */

public class Main {
	//variables
	static int N, M, K;
	
	static long[] nums;
	static int S;
	static long[] tree;
	
	public static void main(String args[]) throws Exception {
		//input
		System.setIn(new FileInputStream("src/algorithm/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		nums = new long[N];
		for(int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}
		
		//tree 초기화
		S = 1;
		while (S < N)
			S *= 2;
		tree = new long[S * 2];
		
		initBU();
		
		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			//update
			if (num == 1) {
				int b = Integer.parseInt(st.nextToken());
				long c = Long.parseLong(st.nextToken());
				
				//long diff = c - tree[S + b - 1];
				//update(1, S, 1, b, diff);
				
				updateBU(b, c);
			}
			//query
			else if (num == 2) {
				int queryLeft = Integer.parseInt(st.nextToken());
				int queryRight = Integer.parseInt(st.nextToken());
				//bw.write(query(1, S, 1, queryLeft, queryRight) + "\n");
				bw.write(queryBU(queryLeft, queryRight) + "\n");
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void updateBU(int target, long value) {
		//leat에서 target노드를 찾음
		//- 값을 반영함
		int node = S + target - 1;
		tree[node] = value;
		
		//부모노드로 루트까지 거슬러 올라가면서 합을 수정함
		node /= 2;
		while (node > 0) {
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
			node /= 2;
		}
	}
	
	static long queryBU(int queryLeft, int queryRight) {
		//leaf에서 left, right 인덱스 찾음
		int left = S + queryLeft - 1;
		int right = S + queryRight - 1;
		long sum = 0;
		
		//left, right가 서로 겹쳐지지 않을 때까지 반복문 돌림
		while (left <= right) {
			//하나의 자식만 포함하는 경우 sum에 합침
			//1) 왼쪽 자식이 홀수일 때 -> 합치고 오른쪽으로 이동
			if (left % 2 == 1) {
				sum += tree[left++];
			}
			//2) 오른쪽 자식이 짝수일 때 -> 합치고 왼쪽으로 이동
			if (right % 2 == 0) {
				sum += tree[right--];
			}
			//부모로 이동
			left /= 2;
			right /= 2;
		}
		return (sum);
	}
	
	static void initBU() {
		//leaf값 반영
		for(int i = 0; i < N; i++) {
			tree[S + i] = nums[i];
		}
		//부모 노드 값 반영(내부 노드 채움)
		//root노드는 1번부터 시작이다 -> query를 호출할 때도 첫번 노드는 1번!
		for(int i = S - 1; i > 0; i--) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	//중간값 바꾸기
	static void update(int left, int right, int node, int target, long diff) {
		//target이 구간에 들어오지 않음
		if (target < left || right < target)
			return ;
		//1. target이 구간에 들어옴
		// - 현재 노드에 diff를 반영
		// - 구간에 속하는 자식노드로 내려감
		else {
			//1). 현재 노드에 diff반영
			tree[node] += diff;
			//2). 자식에게 diff 전달 단 리프 노드 전까지
			//target 그 자체가 아님 = target(leaf)노드가 아닌 부모 노드
			if (left != right) {				
				int mid = (left + right) / 2;
				update(left, mid, node * 2, target, diff);
				update(mid + 1, right, node * 2 + 1, target, diff);
			}
		}
	}
	
	//구간합 구하기
	static long query(int left, int right, int node, int queryLeft, int queryRight) {
		//노드 구간이 구하고자 하는 범위에 들어오지 않음
		//등호 포함되면 안됨 = queryRight = left인 것도 구간합이 범위에 살짝 걸쳐 있다고 볼 수 있으므로
		if (queryRight < left || right < queryLeft)
			return (0);
		//노드 구간이 구하고자 하는 범위에 들어옴
		else if (queryLeft <= left && right <= queryRight) {
			return (tree[node]);
		}
		//노드 구간이 구하고자 하는 구간에 살짝 걸쳐있음(판단 불가)
		else {
			int mid = (left + right) / 2;
			long leftResult = query(left, mid, node * 2, queryLeft, queryRight);
			long rightResult = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
			
			return (leftResult + rightResult);
		}
	}
}
