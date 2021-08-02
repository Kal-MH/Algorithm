package day1;

import java.util.*;
import java.io.*;

/*
 * 목표 : 최종 후보가 누구인지 결정하는 프로그램
 * 1. 사진틀
 * 2. 후보 추천하는 방식
 * - 추천받은 학생의 사진은 사진틀에 게시
 * - 추천 받은 횟수가 가장 적은 학생 사진 삭제 & 만약 동률이면 오래된 사진 삭제
 * 3. 최종 출력은 학생번호가 증가하는 순서대로
 * 
 * 정렬 : 추천이 많은 순, 게시가 오래된 순, 학생번호 증가하는 순
 * - 클래스 정의 (추천, time, 학생번호)
 * 
 * 입력 
 * - N <= 20
 * - 추천 <= 1000
 * - 학생 <= 100
 * */

public class Main {
	//variables
	static int N, K;
	static int[] inputs;
	static Person[] people;
	
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("src/day1/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		inputs = new int[K];
		people = new Person[101]; 
		
		List<Person> list = new ArrayList<>();
		for(int i = 0; i < K; i++) {
			//후보 추천을 받음
			int num = sc.nextInt();
			//만약 처음 추천받은 후보라면 일단 person 리스트에 기록한다.
			if (people[num] == null) {
				people[num] = new Person(num, 0, 0, false);
			}
			//후보가 이미 게시틀에 게시되어 있다면(또 추천받았다면)
			if (people[num].isIn == true) {
				people[num].count++;
			} else {//게시틀에 게시해야 하는 경우
				if (list.size() == N) {
					//게시틀이 꽉 차 있는 경우로 사진을 삭제해야 한다.
					Collections.sort(list);//추천 오름차순, 시간 오름차순으로 정렬
					Person p = list.remove(0);
					p.isIn = false;
				}
				people[num].count = 1;
				people[num].isIn = true;
				people[num].timeStamp = i;
				list.add(people[num]);
			}
			
		}
		Collections.sort(list, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return o1.num - o2.num;
			}
			
		});
		for (Person person : list) {
			System.out.print(person.num + " ");
		}
	}
}

class Person implements Comparable<Person>{
	int num;
	int count;
	int timeStamp;
	boolean isIn;
	
	public Person(int num, int count, int timeStamp, boolean isIn) {
		super();
		this.num = num;
		this.count = count;
		this.timeStamp = timeStamp;
		this.isIn = isIn;
	}

	//추천수를 비교하는 함수
	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		int result = count - o.count; //o가 더 추천수가 많다면 음수가 되어 추천수 적은 순으로 정렬됨
		if (result == 0) {
			//동률이라면 시간이 오래된 순으로 정렬
			return timeStamp - o.timeStamp;
		}
		return result;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.num + " ";
	}
}
