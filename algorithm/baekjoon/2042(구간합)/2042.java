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
		
		//N이상이 되는 리프 노드 개수 구하기
		S = 1;
		while (S < N) {
			S *= 2;
		}
		//리프노드의 2배가 되는 포화 이진트리 만들기(꽉 찬 삼각형)
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
	
	static void initBU() {
		//leaf 값 반영
		for(int i = 0; i < N; i++) {
			tree[S + i] = nums[i];
		}
		//내부노드 채움
		for(int i = S - 1; i > 0; i--) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	//구간합을 구하기 위한 함수
	//1. left, right, node
	// - 함수 시작할 때 지정된 node의 left, right 범위
	//2. queryLeft, queryRight
	// - 합을 구하고자 하는 구간
	static long query(int left, int right, int node, int queryLeft, int queryRight) {
		//연관이 없음 -> 결과에 영향이 없는 값 return
		if (queryRight < left || right < queryLeft)
			return 0;
		//판단가능 -> 현재 노드 값 return
		else if (queryLeft <= left && right <= queryRight) {
			return tree[node];
		}
		//판단불가, 자식에게 위임, 자식에게 올라온 합을 return
		else {
			int mid = (left + right) / 2;
			long leftResult = query(left, mid, node * 2, queryLeft, queryRight);
			long rightResult = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
			return (leftResult + rightResult);
		}
	}
	
	//target node에 diff를 반영하는 함수
	static void update(int left, int right, int node, int target, long diff) {
		//연관 없음
		if (target < left || right < target) {
			return ;
		}
		//연관있음 -> 현재 노드에 diff 반영-> 자식에게 diff전달
		else {
			tree[node] += diff;
			if (left != right) {
				int mid = (left + right) / 2;
				update(left, mid, node * 2, target, diff);
				update(mid + 1, right, node * 2 + 1, target, diff);
			}
		}
	}
	static long queryBU (int queryLeft, int queryRight) {
		//left에서 left, right설정
		long sum = 0;
		int left = S + queryLeft - 1;
		int right = S + queryRight - 1;
		while (left <= right) {
			//좌픅노드가 홀수이거나 우측 노드가 짝수인 것은 둘 중 하나의 자식만 포함하고 있는 경우를 말한다.
			//leaf노드이건, 자식 값이 합쳐진 내부노드(부모)이건, left, right는 반복문을 거치면서
			//부모쪽으로 이동하고 범위가 점점 좁혀지게 되는데 이동하기 전에 값이 소실되지 않도록 미리 저장한다. 
			//좌측 노드가 홀수이면 현재 노드 값 사용하고 한칸 옆으로
			if (left % 2 == 1) {
				sum += tree[left++];
			}
			//우측 노드가 짝수이면 현재 노드 값 사용하고 한칸 옆으로
			if (right % 2 == 0) {
				sum += tree[right--];
			}
			//좌측, 우측 모두 부모로 이동
			left /= 2;
			right /= 2;
		}
		return (sum);
	}
	
	static void updateBU(int target, long value) {
		//leaf에서 target을 찾음
		int node = S + target - 1;
		//value반영
		tree[node] = value;
		//root에 도달할 때까지 부모에 값 반영
		node /= 2;
		while (node > 0) {
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
			node /= 2;
		}
	}
}
