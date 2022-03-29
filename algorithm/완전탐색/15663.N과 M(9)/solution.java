import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] nums;
	
	static int[] selected;
	static boolean[] visit;
	static StringBuilder sb = new StringBuilder();
	 
	public static void rec_func(int k) {
		if (k == M + 1) {
			for(int i = 1; i <= M; i++)
				sb.append(selected[i]).append(' ');
			sb.append('\n');
		} else {
			int last_cand = 0;
			for(int i = 1; i <= N; i++) {
				if (visit[i])
					continue;
				if (nums[i] == last_cand)
					continue;
				visit[i] = true;
				last_cand = nums[i];
				selected[k] = nums[i];
				
				rec_func(k + 1);
				
				visit[i] = false;
				selected[k] = 0;
			}
		}
	}
	
	public static void main(String args[]) throws Exception {
//		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//input
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[N + 1];
		selected = new int[M + 1];
		visit = new boolean[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++)
			nums[i] = Integer.parseInt(st.nextToken());
		
		//run
		Arrays.sort(nums, 1, N + 1);
		rec_func(1);
		
		//output
		System.out.println(sb.toString());
	}
}
