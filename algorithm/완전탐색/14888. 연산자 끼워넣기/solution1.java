package day1;

import java.util.*;
import java.io.*;

public class Main {
	//variables
	static int N;
	static StringBuilder sb;
	static int[] selected;
	static int[] nums;
	static int[] operators;
	
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		//input
		input();
		//rec_func
		rec_func(1); //1번째 원소를 찾는 것부터 시작한다.
		//output
		sb.append(max + "\n" + min);
		System.out.println(sb.toString());
	}
	
	static void input() {
		FastReader scan = new FastReader();
		sb = new StringBuilder();
		N = scan.nextInt();
		selected = new int[N];
		
		nums = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			nums[i] = scan.nextInt();
		}
		
		operators = new int[5];
		for(int i = 1; i < 5; i++) {
			operators[i] = scan.nextInt();
		}
	}
	
	//각각 연산자 순서대로 계산
	static int calculator() {
		int ans = nums[1];
		
		for(int i = 1; i < N; i++) {
			if (selected[i] == 1)
				ans += nums[i + 1];
			if (selected[i] == 2)
				ans -= nums[i + 1];
			if (selected[i] == 3)
				ans *= nums[i + 1];
			if (selected[i] == 4)
				ans /= nums[i + 1];
		}
		return (ans);
	}
	
	static void rec_func(int k) {
		//1. 목적지인가
		if (k == N) {
			//반환받은 value의 값으로 min, max갱신
			int value = calculator();
			
			min = Math.min(min, value);
			max = Math.max(max, value);
		} else {
			//2. 갈 수 있는 곳 순회
			for(int i = 1; i < 5; i++) {
				if (operators[i] > 0) {
					//3. check in
					operators[i]--;
					selected[k] = i;
					rec_func(k + 1);
					operators[i]++;
					selected[k] = 0;
				}
			}
		}
	}
	
	static class FastReader {
		BufferedReader br;
		StringTokenizer st;
		
		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		public FastReader(String s) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(new File(s)));
		}
		
		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return (st.nextToken());
		}
		
		String nextList() {
			String str = "";
			
			try {
				str = br.readLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			return (str);
		}
		
		int nextInt() {
			return (Integer.parseInt(next()));			
		}
		
		long nextLong() {
			return (Long.parseLong(next()));
		}
		
		double nextDouble() {
			return (Double.parseDouble(next()));
			
		}
	}
}
