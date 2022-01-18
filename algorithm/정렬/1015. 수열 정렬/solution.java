import java.util.*;
import java.io.*;

public class Main {
	//variables
	static FastReader scan;
	static StringBuilder sb;
	
	static int N;
	static int[] A, P;
	
	static void input() {
		scan = new FastReader();
		sb = new StringBuilder();
		
		N = scan.nextInt();
		A = new int[N];
		P = new int[N];
		
		for(int i = 0; i < N; i++)
			A[i] = scan.nextInt();
	}
	
	static void pro() {
		//P배열 채우기
		//1. A의 가장 작은 원소 찾기
		//2. P[A의 인덱스] = i;
		for(int i = 0; i < N; i++) {
			int min = Integer.MAX_VALUE;
			int minIndex = 0;
			for(int j = 0; j < N; j++) {
				if (min > A[j]) {
					min = A[j];
					minIndex = j;
				}
			}
			//P배열 채우기
			P[minIndex] = i;
			A[minIndex] = Integer.MAX_VALUE;
		}
		
		//output
		for(int i = 0; i < N; i++)
			System.out.print(P[i] + " ");
	}
	
	public static void main(String[] args) {
		//input
		input();
		//run
		pro();
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
			try {
				while (st == null || !st.hasMoreElements()) {
					st = new StringTokenizer(br.readLine());
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return (st.nextToken());
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
		
		String nextLing() {
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
