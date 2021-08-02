package day1;

import java.util.*;
import java.io.*;

/*
 * 2 pointers 전략
 * - 정렬된 배열이라는 것을 기본 전제로 한다.
 * - 합이 크면 가장 왼쪽에 있는 원소를 제거하기 위해 left 포인터를 하나 증가시킨다.
 * - 합이 작으면 가장 오름쪽에 있는 원소를 포함시키기 위해 right 포인터를 하나 증가시킨다.
 * */

public class Main {
	static int N, M;
	static int[] nums;
	
	public static void main(String args[]) throws Exception {
		//BufferedReader가 Scanner보다 빠르다.
		//한 줄의 문자열을 빠르게 읽고 StringTokenizer로 넘겨주어 공백을 기준으로 숫자 하나를 받는다.
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
		int left = 0, right = 0, count = 0, sum = 0;
		while (true) {
			//terminate
			if (right == N)
				break;
			//sum == M
			if (sum == M) {
				count++;
				sum -= nums[left++];
			}
			//sum > M
			else if (sum > M) {
				sum -= nums[left++];
			}
			//sum < M
			else if (sum < M) {
				sum += nums[++right];
			}
		}
		//output
		System.out.println(count);
	}
}
