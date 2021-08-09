package algorithm;

import java.util.*;
import java.io.*;

public class Main {
	//variables
	
	static int N;
	
	public static void main(String args[]) throws Exception {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		//heap
		MinHeap mh = new MinHeap();
		for(int i = 0; i < N; i++) {
			int input = Integer.parseInt(br.readLine());
			
			if (input == 0) {
				bw.write(mh.delete() + "\n");
			}
			else {
				mh.insert(input);
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
}

class MinHeap {
	List<Integer> list;
	
	public MinHeap() {
		list = new ArrayList<>();
		list.add(0);
	}
	
	public void insert(int val) {
		//1. 가장 마지막 위치에 노드 삽입
		list.add(val);
		//2. 반복 비교(최소힙 기준)
		// 1) 현재 노드가 부모 노드보다 크다면(조건을 만족한다면) 그대로 종료
		// 2) 현재 노드가 부모 노드보다 작다면 부모 노드와 자리 바꿈
		// 3) 조건을 만족할 때까지 혹은 현재 노드가 루트 노드가 될 때까지 반복
		int currentPos = list.size() - 1;
		int parentPos = currentPos / 2;
		
		while (true) {
			if (parentPos == 0 || list.get(parentPos) <= list.get(currentPos))
				break;
			int temp = list.get(parentPos);
			list.set(parentPos, list.get(currentPos));
			list.set(currentPos, temp);
			
			currentPos = parentPos;
			parentPos = currentPos / 2;
		}
	}
	
	public int delete() {
		if (list.size() == 1)
			return 0;
		//1. 루트 노드 삭제, 마지막 노드 루트 노드로 삽입
		int top = list.get(1);
		list.set(1, list.get(list.size() - 1));
		list.remove(list.size() - 1);
		
		//2. 반복 비교
		// 1) 리프노드에 도달할 때까지 혹은 조건을 만족할 때가지
		// 2) 자식 노드가 모두 현재 노드보다 작다면 제일 작은 노드와 위치 바꿈
		// 3) 같다면 어떤 것을 바꿔도 상관 없다.
		int currentPos = 1;
		
		while (true) {
			int leftPos = currentPos * 2;
			int rightPos = currentPos * 2 + 1;
			
			//leftPos가 현재 배열을 인덱스를 벗어나면(왼쪽 자식이 없는 경우, 리프에 도달한 것이므로)
			if (leftPos >= list.size())
				break ;
			
			int minValue = list.get(leftPos);
			int minPos = leftPos;
			
			//자식들 중 더 작은 값의 자식 체크
			if (rightPos < list.size() && list.get(rightPos) < minValue) {
				minValue = list.get(rightPos);
				minPos = rightPos;
			}
			
			//조건을 만족하지 못했을 때(현재 노드가 자식노드보다 클 때)
			if (list.get(currentPos) > minValue) {
				int temp = list.get(currentPos);
				
				list.set(currentPos, minValue);
				list.set(minPos, temp);
				
				currentPos = minPos;
			}
			//조건을 만족했을 때(현재 노드가 자식노드보다 더 작을 때)
			else
				break;
		}
		
		return top;
	}
}
