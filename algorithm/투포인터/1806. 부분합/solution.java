import java.util.*;
import java.io.*;

public class Main
{
	//variables
	static int N, S;
	static int[] nums;
	
	public static void main(String args[]) throws Exception {
		//input setting
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//input
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		nums = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(st.nextToken());
		
		//run
		// 1. sum >= S -> 길이 저장 & low++
		// 2. sum < S -> high++;
		int low = 0, high = 0;
		int minLength = Integer.MAX_VALUE;
		long sum = nums[0];
		
		while (true) {
			if (high == N)
				break;
			if (sum >= S) {
				minLength = Math.min(minLength, high - low + 1);
				sum -= nums[low++];
			} else {
				sum += nums[++high];
			}
 		}
		
		
		//output
		if (minLength == Integer.MAX_VALUE)
			minLength = 0;
		System.out.println(minLength);
	}
}
