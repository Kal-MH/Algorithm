import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] arr;
    
    static void input() {
    	N = scan.nextInt();
    	M = scan.nextInt();
    	
    	arr = new int[N + 1];
    	for(int i = 1; i <= N; i++)
    		arr[i] = scan.nextInt();
    }
    
    static boolean determination(int limit) {
    	int count = 1;
    	
    	int money = limit;
    	for(int i = 1; i <= N; i++) {
    		if (count > M)
    			break;
    		if (arr[i] > money) {
    			count++;
    			money = limit;
    			i--;
    		} else {
    			money -= arr[i];
    		}
    		
    	}
    	
    	return count <= M;
    }

    static void pro() {
    	int L = 1, R = 1000000000, ans = 0;
    	for(int i = 1; i <= N; i++) L = Math.max(L, arr[i]);
        
    	while (L <= R) {
    		int mid = (L + R) / 2;
    		
    		if (determination(mid)) {
    			ans = mid;
    			R = mid - 1;
    		} else {
    			L = mid + 1;
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
