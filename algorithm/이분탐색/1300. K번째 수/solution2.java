import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, K;

    static void input() {
        N = scan.nextInt();
        K = scan.nextInt();
    }

    static boolean determination(long candidate) {
        // candidate 이하의 숫자가 K개 이상인가?
        long cnt = 0;
        
        // 각 행 마다 candidate 이하인 개수를 누적해보자!
        for(long i = 1; i <= N; i++) {
        	long left = 1, right = N, rowCnt = 0;
        	while (left <= right) {
        		long mid = (left + right) / 2;
        		
        		if (i * mid <= candidate) {
        			rowCnt = mid;
        			left = mid + 1;
        		} else {
        			right = mid - 1;
        		}
        	}
        	
        	cnt += rowCnt;
        }
        
        return cnt >= K;
    }

    static void pro() {
        long L = 1, R = (long)N*N, ans = 0;
        // [L ... R] 범위 안에 정답이 존재한다!
        // 이분 탐색과 determination 문제를 이용해서 answer를 빠르게 구하자!
        
        while (L <= R) {
        	long mid = (L + R) / 2;
        	
        	if (determination(mid)) {
        		ans = mid;
        		R = mid - 1;
        	} else {
        		L = mid + 1;
        	}
        }
        
        System.out.println(ans);
    }

    public static void main(String[] args) {
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
