import java.util.*;
import java.io.*;

/*
 * 1. 목표
 * - N개로 된 수열
 * - i부터 j까지 원소를 모두 합해 M이 되는 경우의 수 구하기
 * 
 * 2. 입력
 * - N <= 10,000
 * - M <= 300,000,000
 * 
 * 3. 2 pointers
 * - one pointer : 합을 줄이는 역할
 * - two pointer : 합을 늘리는 역할
 * */
public class Main{
	//variables
	static int N, M;
	static int[] nums;
	
	public static void main(String args[]) throws Exception {
		//input		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(st.nextToken());
		//run
		int left = 0, right = 0, count = 0;
		long sum = 0;
		
		sum = nums[0];
		while (true) {
			if (right == N)
				break;
			if (sum == M) {
				count++;
				sum -= nums[left++];
			}
			else if (sum > M) {
				sum -= nums[left++];
			}
			else {
				sum += nums[++right];
			}
		}
		//output
		System.out.println(count);
	}
}
