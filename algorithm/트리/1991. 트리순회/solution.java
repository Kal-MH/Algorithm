/*
 * 트리순회
 * 1. 트리를 어떻게 입력받고 채워넣을 것인가.
 * 2. 트리 순회 시간복잡도 예상
 *  - 높이만큼 검색이 이뤄지므로 O(logN)
 */

import java.io.*;
import java.util.*;

public class Main {
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();
	
	//variables
	static int n;
	static int[][] childs;
	
	static void input() {
        /* TODO */
		n = scan.nextInt();
		
		childs = new int[30][2];
		for(int i = 0; i < n; i++) {
			char curCh = scan.next().charAt(0);
			int curPo = (int)(curCh - 'A');
			for(int k = 0; k < 2; k++) {
				char ch = scan.next().charAt(0);
				if (ch != '.')
					childs[curPo][k] = (int)(ch - 'A');
				else
					childs[curPo][k] = -1;
			}
		}
    }

    static void in_order(int x) {
        /* TODO */
    	if (x == -1)
    		return;
    	in_order(childs[x][0]);
    	sb.append((char)(x + 'A'));
    	in_order(childs[x][1]);
    }

    static void pre_order(int x) {
        /* TODO */
    	if (x == -1)
    		return;
    	sb.append((char)(x + 'A'));
    	pre_order(childs[x][0]);
    	pre_order(childs[x][1]);
    }

    static void post_order(int x) {
        /* TODO */
    	if (x == -1)
    		return;
    	post_order(childs[x][0]);
    	post_order(childs[x][1]);
    	sb.append((char)(x + 'A'));
    }

    static void pro() {
        /* TODO */
    	pre_order(0);
    	sb.append('\n');
    	in_order(0);
    	sb.append('\n');
    	post_order(0);
    	System.out.println(sb.toString());
    }

	public static void main(String[] args) throws Exception{
		input();
		pro();

	}
	static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
