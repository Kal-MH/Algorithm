package day3.p1202;

 /*
  * 보석도둑
  * 1. 정답의 최대치
  * - N, k <= 300,000 -> int
  * - 무게, 가격 <= 1,000,000 -> int
  * - 가방에 담을 수 있는 무게 <= 100,000,000 -> int
  * - 최대 가격 -> 10^11 -> long
  */

import java.util.*;
import java.io.*;

public class Main {
	//variables
	static int N, K;
	static Jewelry[] jewels;
	static int[] bags;
	public static void main(String[] args) throws Exception {
		//input setting
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//input
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		jewels = new Jewelry[N];
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			jewels[n] = new Jewelry(weight, value);
		}
		
		bags = new int[K];
		for(int k = 0; k < K; k++) {
			bags[k] = Integer.parseInt(br.readLine());
		}
		
		//run
		//1. 보석 무게순 정렬(오름차순)
		Arrays.sort(jewels, Comparator.comparingInt(Jewelry::getWeight));
		//2. 가방 무게순 정렬(오름차순)
		Arrays.sort(bags);
		//3. 우선순위 큐를 이용 - 삽입하는 보석을 가치가 제일 높은 순으로 정렬
		//	- 가방 하나에 대해서 넣을 수 있는 모든 보석에 대해서 정렬
		PriorityQueue <Jewelry> pq = new PriorityQueue<>(Comparator.comparingInt(Jewelry::getValue).reversed());
		
		//4. 가장 가치가 높은 보석 꺼내오기
		long result = 0;
		int n = 0;
		for(int k = 0; k < K; k++) {
//			while (jIndex < N && jewels[jIndex].getWeight() <= bags[k]) {
//				pq.add(jewels[jIndex]);
//				jIndex++;
//			}
			for(; n < N; n++) {				
				if (bags[k] >= jewels[n].weight) {
					pq.add(jewels[n]);
				}
				else
					break ;
			}
			if (!pq.isEmpty()) {
				result += pq.poll().value;
			}
		}
		
		//output
		System.out.println(result);
	}
}

class Jewelry {
	int weight;
	int	value;
	
	public Jewelry(int weight, int value) {
		super();
		this.weight = weight;
		this.value = value;
	}
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
