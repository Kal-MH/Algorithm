package day1;

import java.util.*;
import java.io.*;

/*
 * 1. 부분합이 되는 최소 길이 찾기
 * 2. 2 pointers 전략
 * 	- 합보다 크면 왼쪽 포인터 이동
 *  - 합보다 작으면 오른쪽 포인터 이동
 * 3. 최소 길이를 minLength 변수에 저장한다.
 * */

public class Main {
	static int N, M;
	static int nums[];
	
	public static void main(String args[]) throws Exception {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		//2 pointers
		
		int left = 0, right = 0, sum = 0, minLength = Integer.MAX_VALUE;
		sum = nums[0];
		while (true) {
			//terminated condition
			if (right == N)
				break;
			//sum >= M
			if (sum >= M) {
				minLength = Math.min(right - left + 1, minLength);
				sum -= nums[left++];
			}
			//sum < M
			else if (sum < M) {
				sum += nums[++right];
			}
		}
		
		//output
		if (minLength == Integer.MAX_VALUE)
			System.out.println("0");
		else
			System.out.println(minLength);
	}
}
