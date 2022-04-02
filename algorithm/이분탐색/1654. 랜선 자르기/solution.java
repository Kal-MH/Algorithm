import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int K, N, ans;
    static int[] A;

   

    static boolean determination(long D) {
        long count = 0;
        
        for(int i = 1; i <= K; i++) {
        	if (A[i] >= D) {
        		count += (A[i] / D);
        	}
        }
    	return count >= N;
    }

    static void pro() {
        // determination을 빠르게 하기 위해서 정렬해주자.
    	long L = 1, R = Integer.MAX_VALUE, ans = 0;
    	
    	while (L <= R) {
    		long mid = (L + R) / 2;
    		
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
        
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        A = new int[K + 1];
        for(int i = 1; i <= K; i++) {
        	A[i] = Integer.parseInt(br.readLine());
        }
    	
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
