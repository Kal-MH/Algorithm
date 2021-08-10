package algorithm;

import java.util.*;
import java.io.*;

/*
 * 1. 입력
 * - P(4 ≤ P ≤ 10100)와 K (2 ≤ K ≤ 106)
 * */

public class Main {
	//variables
	static int MAX = 1000000;
	static int K;
	static char[] P;
	static boolean[] checked;
	static List<Integer> primes = new ArrayList<>();
	
	public static void main(String args[]) throws Exception{
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		P = st.nextToken().toCharArray();
		K = Integer.parseInt(st.nextToken());
		
		//에라토스테네스의 체 만들기
		checked = new boolean[MAX + 1];
		
		for(int i = 2; i < MAX; i++) {
			if (checked[i] == false) {
				primes.add(i);
				for(int j = i * 2; j <= MAX; j += i) {
					checked[j] = true;
				}
			}
		}
		
		String answer = "";
		for(int prime : primes) {
			if (prime >= K)
				break;
			if (checkIsBad(prime)) {
				if (prime < K) {
					answer += "BAD " + prime;
					break;
				}
			}
		}
		
		if (answer.equals(""))
			System.out.println("GOOD");
		else
			System.out.println(answer);
		
	}
	static boolean checkIsBad(int x) {
		int ret = 0;
		for(int i = 0; i < P.length; i++) {
			ret = (ret * 10 + (P[i] - '0')) % x;
		}
		//x로 나누어 떨어진다면
		if (ret == 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
