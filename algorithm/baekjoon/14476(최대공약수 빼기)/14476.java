package algorithm;

import java.util.*;
import java.io.*;

/*
 * 1. 입력
 * - N (4 ≤ N ≤ 1,000,000) // 정수의 갯수
 * - 각각의 정수는 20억이 넘지 않는다.
 * */

public class Main {
	//variables
	static int N;
	static int[] nums;
	
	static int[] gcdLtoR;
	static int[] gcdRtoL;
	
	public static void main(String args[]) throws Exception{
		//input
		System.setIn(new FileInputStream("src/algorithm/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		gcdLtoR = new int[N];
		gcdRtoL = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		//LtoR, RtoL 초기화
		gcdLtoR[0] = nums[0];
		gcdRtoL[N - 1] = nums[N - 1];
		for(int i = 1; i < N; i++) {
			gcdLtoR[i] = gcd(nums[i], gcdLtoR[i - 1]);
		}
		for(int i = N - 2; i >= 0; i--) {
			gcdRtoL[i] = gcd(gcdRtoL[i + 1], nums[i]);
		}
		
		//calculate
		int max = 0;
		int maxIndex = 0;
		for(int i = 0; i < N; i++) {
			int gcd = 0;
			if (i == 0) {
				gcd = gcdRtoL[1];
			}
			else if (i == N - 1) {
				gcd = gcdLtoR[N - 2];
			}
			else {
				gcd = gcd(gcdLtoR[i - 1], gcdRtoL[i + 1]);
			}
			
			if (nums[i] % gcd != 0 && gcd > max) {
				max = gcd;
				maxIndex = i;
			}
		}
		
		if (max == 0) {
			System.out.println("-1");
		}
		else {
			System.out.println(max + " " + nums[maxIndex]);
		}
	}
	
	// a와 b의 인자 전달 위치는 신경쓰지 않아도 된다.
	// b > a인채로 들어온다고 해도 내부에서 a > b의 위치로 다시 조정될 것이기 때문에
	static int gcd(int a, int b) {
		while (b != 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}
}
