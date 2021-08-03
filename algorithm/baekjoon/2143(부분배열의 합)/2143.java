package day1;

import java.util.*;
import java.io.*;

/*
 * 1. 입력
 * - T(-1,000,000,000 ≤ T ≤ 1,000,000,000)
 * - n <= 1000
 * - m <= 1000
 * */

public class Main {
	//variables
	static long T;
	static int N, M;
	static long[] inputA, inputB;
	
	public static void main(String args[]) throws Exception{
		//input
		System.setIn(new FileInputStream("src/day1/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Long.parseLong(br.readLine());
		
		N = Integer.parseInt(br.readLine());
		inputA = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			inputA[i] = Long.parseLong(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		inputB = new long[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			inputB[i] = Long.parseLong(st.nextToken());
		}
		
		//2 pointers
		//1) subA, subB 만들기
		//	- ptA(합을 증가시키는 역할), ptB(합을 감소시키는 역할)을 위해 subB는 내림차순으로 정렬되어야 한다.
		List<Long> subA = new ArrayList<>();
		List<Long> subB = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			long sum = inputA[i];
			subA.add(sum);
			for(int j = i + 1; j < N; j++) {
				sum += inputA[j];
				subA.add(sum);
			}
		}
		
		for(int i = 0; i < M; i++) {
			long sum = inputB[i];
			subB.add(sum);
			for(int j = i + 1; j < M; j++) {
				sum += inputB[j];
				subB.add(sum);
			}
		}
		
		Collections.sort(subA);
		Collections.sort(subB, Comparator.reverseOrder());
		//2) 2 pointers 연산
		// - T와 같다면 같은 값을 가지는 항목이 더 있는 지 확인
		
		int ptA = 0, ptB = 0;
		long result = 0;
		
		while (ptA < subA.size() && ptB < subB.size()) {
			long currentA = subA.get(ptA);
			long target = T - currentA;
			
			if (target == subB.get(ptB)) {
				long countA = 0;
				long countB = 0;
				while (ptA < subA.size() && currentA == subA.get(ptA)) {
					countA++;
					ptA++;
				}
				while (ptB < subB.size() && target == subB.get(ptB)) {
					countB++;
					ptB++;
				}
				result += (countA * countB);
			} else if (target < subB.get(ptB)) {
				ptB++;
			} else {
				ptA++;
			}
		}
		//output
		System.out.println(result);
	}
}
