package day3.p1927;

/*
 * 최소 힙 만들기
 */

import java.util.*;
import java.io.*;

public class Main {
	//variables
	static int N;
	
	public static void main(String[] args) throws Exception {
		//input setting
		System.setIn(new FileInputStream("src/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//input
		N = Integer.parseInt(br.readLine());
		
		//heap
		MaxHeap mh = new MaxHeap();
		for(int n = 0; n < N; n++) {
			int command = Integer.parseInt(br.readLine());
			
			if (command > 0) {
				mh.insert(command);
			} else if (command == 0) {
				bw.write(mh.delete() + "\n");
			}
		}
		
		//free
		bw.flush();
		bw.close();
		br.close();
	}
}

class MaxHeap {
	List<Integer> list;
	
	public MaxHeap() {
		list = new ArrayList<>();
		//초기화 - 인덱스로 접근하기 위해 0번째는 쓰지 않는다.
		list.add(0);
	}
	
	void insert(int input) {
		//1. 가장 마지막 노드에 삽입
		list.add(input);
		
		//2. 부모 노드와 비교
		int currentPos = list.size() - 1;
		int parentPos = currentPos / 2;
		
		//2.2 부모노드와 비교해서 크면 swap
		while (true) {
			//탈출조건
			// current가 현재 root에 있거나, maxheap조건을 만족하고 있다면 탈출
			if (parentPos == 0 || list.get(parentPos) >= list.get(currentPos))
				break ;
			//비교
			int temp = list.get(parentPos);
			list.set(parentPos, list.get(currentPos));
			list.set(currentPos, temp);
			
			currentPos = parentPos;
			parentPos = currentPos / 2;
		}
	}
	
	int delete() {
		//list가 비어있는가
		if (list.size() == 1)
			return (0);
		//1. 루트 노드 삭제 & 마지막 노드 루트에 삽입
		int result = list.get(1);
		list.set(1, list.get(list.size() - 1));
		list.remove(list.size() - 1);
		
		//2. 자식 노드와 비교
		// - 자식노드와 비교해서 더 작으면 swap
		// - 모든 자식과 비교해서 작을 때면, 자식 노드 중 가장 큰 것과 swap
		int currentPos = 1;
		while (true) {
			int leftPos = currentPos * 2;
			int rightPos = currentPos * 2 + 1;
			
			if (leftPos >= list.size())
				break ;
			
			int childMaxValue = list.get(leftPos);
			int childMaxPos = leftPos;
			
			if (rightPos < list.size() && list.get(rightPos) > childMaxValue) {
				childMaxValue = list.get(rightPos);
				childMaxPos = rightPos;
			}
			
			if (list.get(currentPos) < childMaxValue) {
				int temp = list.get(currentPos);
				list.set(currentPos, childMaxValue);
				list.set(childMaxPos, temp);
				
				currentPos = childMaxPos;
			}
			else
				break ;
		}
		return (result);
	}
}
