package day1;

import java.util.*;
import java.io.*;

public class Main {
	//variables
	static int N, M;
	static StringBuilder sb;
	static int[] selected, used;
	
	public static void main(String[] args) {
		//input
		input();
		//rec_func
		rec_func(1); //1번째 원소를 찾는 것부터 시작한다.
		//output
		System.out.println(sb.toString());
	}
	
	static void input() {
		FastReader scan = new FastReader();
		sb = new StringBuilder();
		N = scan.nextInt();
		M = scan.nextInt();
		selected = new int[M + 1];
		used = new int[N + 1];
	}
	
	static void rec_func(int k) {
		//1. 목적지인가
		if (k == M + 1) {
			for(int i = 1; i <= M; i++)
				sb.append(selected[i] + " ");
			sb.append("\n");
		} else {
			//2. 갈 수 있는 곳 순회
			int start = selected[k - 1];
			for(int i = start + 1; i <= N; i++) {
				selected[k] = i;
				rec_func(k + 1);
				selected[k] = 0;
			}
		}
	}
	
	static class FastReader {
		BufferedReader br;
		StringTokenizer st;
		
		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		
		public FastReader(String s) throws Exception {
			br = new BufferedReader(new FileReader(new File(s)));
		}
		
		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
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
		
		String nextLine() {
			String str = "";
			
			try {
				str = br.readLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			return (str);
		}
	}
}
