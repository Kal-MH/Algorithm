package algorithm;

import java.util.*;
import java.io.*;

public class Main {
	//variables

	static int N;
	static int[] nums;
	static long[] fact = new long[21];
	
	static long[][] dp;
	static boolean[] visited;
	
	public static void main(String args[]) throws Exception{
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//20까지 팩토리얼 값 저장
		fact[0] = 1;
		for(int i = 1; i <= 20; i++) {
			fact[i] = fact[i - 1] * i;
		}
		
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int command = Integer.parseInt(st.nextToken());
		
		if (command == 1) {
			//1 <= k <=N! 이므로 long을 써야 한다.
			long target = Long.parseLong(st.nextToken());
			StringBuilder sb = new StringBuilder();
			for(int i= 0; i < N; i++) {
				//1, 2, 3, 4,...,N을 선택했는가를 기준으로 target(k)가 속하는 지 확인하는 반복문
				for(int j = 1; j <= N; j++) {
					if (visited[j])
						continue;
					if (target > fact[N - i - 1]) {
						target -= fact[N - i - 1];
					} else {
						sb.append(j);
						sb.append(" ");
						visited[j] = true;
						break;
					}
				}
			}
			System.out.println(sb.toString());
		}
		else if (command == 2) {
			//수열 입력받아 초기화
			nums = new int[N];
			for(int i = 0; i <N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			long result = 0;
			// 1. (N - 1)!, (N - 2)! 로 1씩 작아지면서 result에 더하게 된다.
			// 2. 내 앞선 순열의 갯수를 더하는 것, 나까지의 순서를 더하는 게 아니므로 j <= nums[i]이 아닌
			//		j < nums[i]가 된다.
			for(int i = 0; i < N; i++) {
				for(int j = 1; j < nums[i]; j++) {
					//1부터 nums[i]까지 즉, nums[i] - 1개의 숫자가 존재하는데
					//이미 선택된 숫자를 제외하고(visited[j] == true)
					//남은 숫자들에 대해 factorial 즉, 일괄적인 경우의 수를 더한다는 의미
					//nums[i] - 1 : 내 앞에 존재하는 숫자들의 갯수
					//visited[j] == false : 이미 선택된 숫자들은 제외하고 남은 숫자들에 대해
					//result += fact[N - i - 1] : 일괄적으로 현재의 경우의 수를 남은 숫자들의 갯수만큼 더한다. 
					if (visited[j] == false) {
						result += fact[N - i - 1];
					}
				}
				//내가 선택한 수를 전체에서 빼기 위해
				visited[nums[i]] = true;
			}
			System.out.println(result + 1);
		}
	}
}
