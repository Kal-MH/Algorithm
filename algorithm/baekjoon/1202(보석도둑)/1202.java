package algorithm;

import java.util.*;
import java.io.*;

/*
 * 1. 조건 정리
 * - 보석은 무게와 가격이 있다.
 * - 가방에는 한 개의 보석만 넣을 수 있다.
 * - 가방은 여러 개 가져갈 수 있다.
 * 2. 해결
 * - 각 가방에 넣을 수 있는 최대 가치의 보석 한 개를 택해야 한다.
 * - 단순 최대힙은 사용할 수 없다.
 * 		- 힙은 무게에 맞춰서 요소를 뒤로 넘기는 일이 가능하지 않다.
 * 		- 배열을 사용하게 된다면 만약, 해당하는 무게를 찾을 수 없어 매번 N번의 연산을 반복해야 한다면
 * 		  30만 x 30만의 연산이 발생해 시간초과가 날 수 있다.
 * - 따라서 우선순위큐를 이용, 들어갈 수 잇는 무게의 보석 중에서 가장 가치가 큰 보석을 골라낸다.
 * */

public class Main {
	//variables
	static int N, K;
	static Jewelry[] jewelries;
	static int[] bags;
	
	public static void main(String args[]) throws Exception {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		jewelries = new Jewelry[N];
		bags = new int[K];
		
		//초기화
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jewelries[i] =
					new Jewelry(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		for(int i = 0; i < K; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}
		
		//sorting
		//1. 가방 무게 오름차순
		Arrays.sort(bags);
		//2. 보석 무게 오름차순
		Arrays.sort(jewelries, Comparator.comparingInt(Jewelry::getWeight));
		//우선순위 큐
		PriorityQueue<Jewelry> pq =
				new PriorityQueue<Jewelry>(Comparator.comparingInt(Jewelry::getValue).reversed());
		//1. 가방 하나 꺼내온다.
		//2. 가방에 들어갈 수 있는 보석 모두 우선순위 큐에 넣는다.
		//3. 가치가 가장 큰 보석만 넣는다.
		int jIndex = 0;
		long result = 0;
		for(int i = 0; i < bags.length; i++) {
			while (jIndex < N && jewelries[jIndex].weight <= bags[i]) {
				pq.add(jewelries[jIndex++]);
			}
			if (!pq.isEmpty())
				result += pq.poll().value;
		}
		System.out.println(result);
	}
}

class Jewelry {
	int weight;
	int value;
	
	public int getWeight() {
		return weight;
	}

	public int getValue() {
		return value;
	}


	public Jewelry (int weight, int value) {
		super();
		this.weight = weight;
		this.value = value;
	}
}
