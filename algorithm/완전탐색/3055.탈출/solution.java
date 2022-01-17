package day1;

import java.util.*;
import java.io.*;

public class Main {
	//variables
	static int [] mx = {-1, 1, 0, 0}; //왼, 오, 아래, 위
	static int [] my = {0, 0, -1, 1};
	
	static int R, C;
	static char[][] map;
	static int[][] dp;
	static Queue<Point> queue;
	static boolean foundAnswer;
	
	public static void main(String args[]) throws Exception {
		//입력 스트림 대상을 new FileInputStream으로 변경함 (파일입력스트림을 생성한 것과 마찬가지)
		//System.setIn(new FileInputStream("src/day1/input.txt"));
		//사용자로부터 키보드로 키 입력을 받기 위해 scanner생성
		Scanner sc = new Scanner(System.in);
		
		//input
		R = sc.nextInt();
		C = sc.nextInt();
		
		map = new char[R][C];
		dp = new int[R][C];
		queue = new LinkedList<>();
		
		Point st = null;
		for(int r = 0; r < R; r++) 
		{
			String line = sc.next();
			for(int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c);
				if (map[r][c] == 'S') {
					st = new Point(r, c, 'S');
				} else if (map[r][c] == '*') {
					queue.add(new Point(r, c, '*'));
				}
			}
		}
		queue.add(st);
		
		//BFS
		while (!queue.isEmpty()) {
			//1. dequeue
			Point p = queue.poll();
			//2. destination ? (if p == 0)
			if (p.type == 'D') {
				System.out.println(dp[p.y][p.x]);
				foundAnswer = true;
				break;
			}
			//3. 순회 for(위, 아래, 오른, 왼)
			for(int i = 0; i < 4; i++) {
				int ty = p.y + my[i];
				int tx = p.x + mx[i];
				//4. 갈 수 있는가? if (맵을 벗어나지 않고, '.', 'D'인가)
				if ((0 <= ty && ty < R) && (0 <= tx && tx < C)) {
					if (p.type == '*') {
						if (map[ty][tx] == '.' || map[ty][tx] == 'S') {
							//5. check in
							map[ty][tx] = '*';
							//6. enqueue		
							queue.add(new Point(ty, tx, '*'));
						}
					} else { //'.', 'S'
						if (map[ty][tx] == '.' || map[ty][tx] == 'D') {
							if (dp[ty][tx] == 0) {
								//5. check in
								dp[ty][tx] = dp[p.y][p.x] + 1;
								//6. enqueue
								queue.add(new Point(ty, tx, map[ty][tx]));
							}
						}
					}
				}
			}
		}
		if (foundAnswer == false) {
			System.out.println("KAKTUS");
		}
	}
}


class Point {
	int y;
	int x;
	char type;
	
	public Point(int y, int x, char type) {
		super();
		this.type = type;
		this.y = y;
		this.x = x;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[y=" + y +", x=" + x + " type=" + type + "]";
	}
}
