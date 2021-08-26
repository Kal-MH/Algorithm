package day1;

import java.util.*;
import java.io.*;

public class Main {
	//variables
	static int N, M;
	static StringBuilder sb;
	static int[] selected;
	
	public static void main(String[] args) {
		//input
		input();
		//rec_func
		rec_func(1); //1번째 원소를 찾는 것부터 시작한다.
		//output
		System.out.println(sb.toString());
	}
	
	static void input() {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		N = sc.nextInt();
		M = sc.nextInt();
		selected = new int[M + 1];
	}
	
	static void rec_func(int k) {
		//1. 목적지인가
		if (k == M + 1) {
			//sb에 저장
			for(int i = 1; i <= M; i++)
				sb.append(selected[i] + " ");
			sb.append("\n");
		} else {
			//2. 갈 수 있는 곳 순회
			for(int i = 1; i <= N; i++) {				
				//3. check in
				selected[k] = i;
				//4. 간다
				rec_func(k + 1);
				//5. check out
				selected[k] = 0; 
			}
		}
	}
}
