import java.util.*;
import java.io.*;

/*
 * 1. 목표
 * 	- 적어도 M미터의 나무를 집에 가져갈 수 있는 절단기에 설정할 수 있는 높이의 최댓값
 *  - 적어도 M미터라는 것은 M이상이 되게 하는 높이 H의 값을 구한다는 의미가 됨.
 * 2. 입력
 * - 1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000 -> long 사용
 * */

public class Main {
	static int N, M;
	static int[] trees;
	
	public static void main(String args[]) throws Exception{
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		trees = new int[N];
		st = new StringTokenizer(br.readLine());
		long max = 0;
		for(int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, trees[i]);
		}
		
		//3 pointers
		// 1) start, mid, end pointer 활용
		// 	- 나무 합(M)보다 크면 end = mid + 1;
		//	 - M보다 작으면 start = mid - 1;
		long s = 0;
		long e = max;
		long mid = 0;
		long result = 0;
		while (true) {
			if (s > e)
				break;
			mid = (s + e) / 2;
			long sum = calc(mid);
			if (sum == M) {
				result = mid;
				break;
			} else if (sum > M) {
				result = mid;
				s = mid + 1;
			} else if (sum < M) {
				e = mid - 1;
			}
		}
		//output
		System.out.println(result);
		
	}
	
	static long calc(long value) {
		long result = 0;

		for (int tree : trees) {
			if (tree > value)
				result += tree - value;
		}
		return result;
	}
}
