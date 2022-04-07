import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, K;
    static long[] arr;
    
    static void input() {
    	N = scan.nextInt();
    	K = scan.nextInt();
    	
    	arr = new long[N + 1];
    	for(int i = 1; i <= N; i++)
    		arr[i] = scan.nextLong();
    }
    
    static boolean determination(long limit) {
    	int count = 0;
    	for(int i = 1; i <= N; i++) {
    		if (limit <= arr[i]) {
    			count += (arr[i] / limit);
    		}
    	}
    	
    	return count >= K;
    }

    static void pro() {
    	long L = 1, R = Integer.MIN_VALUE, ans = 0;
    	for(int i = 1; i <= N; i++) R = Math.max(R, arr[i]); 
    	
    	while (L <= R) {
    		long mid = (L + R) / 2;
    		
    		if (determination(mid)) {
    			ans = mid;
    			L = mid + 1;
    		} else {
    			R = mid - 1;
    		}
    	}
    	
    	//output
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
