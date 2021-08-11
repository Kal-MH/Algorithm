import java.util.*;
import java.io.*;

public class Main {
	//variables
	
	static int N, M, K;
	static int[][] dp = new int[201][201];
	
	public static void main(String args[]) throws Exception{
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if (K > combination(N + M, M)) {
			System.out.println("-1");
		} else {
			StringBuilder sb = new StringBuilder();
			query(N, M, K, sb);
			System.out.println(sb.toString());
		}
	}
	
	static void query(int n, int m, int k, StringBuilder sb) {
		//종료조건 : n , m이 모두 0이 되었을 때
		if (n + m == 0) {
			return;
		}
		//모든 z글자 다 뽑고 남은 'a'글자 합침
		else if (m == 0) {
			sb.append("a");
			query(n - 1, m, k, sb);
		}
		//n == 0이 되는 경우
		//1) n, m이 모두 0 (모든 쿼리 마치고 종료)
		//2) n == 0, m == 1 (마지막으로 합쳐야 할 글자가 'a'가 됨)
		else if (n == 0) {
			sb.append("z");
			query(n, m - 1, k, sb);
		}
		else {
			// 전체 수 : n + m
			// a의 개수 : n
			// z의 개수 : m
			// 따라서 'a'를 붙었는지, 'z'를 붙었는 지에 따라 n - 1, m - 1이 달라진다.
			int leftCount = combination(n + m - 1, m);
			if (leftCount >= k) {
				sb.append("a");
				query(n - 1, m, k, sb);
			} else {
				sb.append("z");
				query(n, m - 1, k - leftCount, sb);
			}
		}
	}
	
	static int combination(int n, int r) {
		// nCn, nC0인 경우,
		if (n == r || r == 0) {
			return (1);
		}
		//이미 채워져 잇는 값은 계산할 필요없이 바로 출력 
		else if (dp[n][r] != 0) {
			return dp[n][r];
		}
		else {
			return dp[n][r] = Math.min((int)1e9, combination(n - 1, r - 1) + combination(n - 1, r));
		}
	}
}
