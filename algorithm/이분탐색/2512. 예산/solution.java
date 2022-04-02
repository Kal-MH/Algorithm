import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M, ans;
    static int[] A;

   

    static boolean determination(int bound) {
        int sum = 0;
        
       for(int i = 1; i <= N; i++) {
    	   sum += Math.min(A[i], bound);
       }
        return sum <= M;
    }

    static void pro() {
        // determination을 빠르게 하기 위해서 정렬해주자.
    	Arrays.sort(A, 1, N + 1);
    	int L = 1, R = A[N], ans = 0;
    	
    	while (L <= R) {
    		int mid = (L + R) / 2;
    		
    		//합산이 된다면
    		if (determination(mid)) {
    			ans = mid;
    			L = mid + 1;
    		} else {
    			R = mid - 1;
    		}
    	}
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        A = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
        	A[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
    	
    	//input();
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
