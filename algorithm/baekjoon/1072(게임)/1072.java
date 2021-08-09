package algorithm;

import java.util.*;
import java.io.*;

/*
 * 1. input
 * - 10억이 넘어가기 때문에 자료형은 int가 아닌 long을 해야 한다.
 * 2. 연산 주의
 * - 정수형 자료형에 담기 때문에 (Y / X) * 100은 원하는 값과 다른 값을 얻을 수 있다.
 * 3. end의 최대값 X
 * - X번 반복해도 승률은 그대로 일 수 있는가? 그럴 수 없다.
 * 4. 왜 이분탐색을 이용하는가
 * - X의 최대값이 10억이 최대가 되기 때문에 최대 연산을 10억하게 되어 타임아웃이 발생할 수 있다.
 * */

public class Main {
	public static void main(String args[]) {
		//input
		Scanner sc = new Scanner(System.in);
		
		long X = sc.nextLong();
		long Y = sc.nextLong();
		
		long Z = 100 * Y / X;
		//System.out.println(Z);
		//이렇게 작성하면 현재 자료형은 정수이기때문에 소수점 아래는 모두 잘라내게 되어 원하는 값을 얻을 수 없다.
		//ex) 8 / 10의 경우 Y / X의 값은 0이 되어 Z가 0이라는 값을 얻게 된다.
		//long Z = (Y / X) * 100;
		//System.out.println(Z);
		
		//calculate
		//output
		if (Z >= 99) 
			System.out.println("-1");
		else {
			long start = 0;
			long end = X;
			while (start < end) {
				long mid = (start + end) / 2;
				long newRate = (100 * (Y + mid)) / (X + mid);
				if (newRate == Z)
					start = mid + 1;
				else
					end = mid;
			}
			System.out.println(end);
		}
		
	}
}
