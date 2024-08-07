import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] A;

    static void input() {
        N = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
    }

    //나와 같은 수 찾기
    static int lower_bound(int[] A, int L, int R, int X) {
      //같은 숫자가 여러 개 중복이기 때문에 숫자를 찾았다고 해서 반복문이 끝나면 안된다.
      //또한, 같은 수 중에서 가장 작은 수를 찾아야 하기 때문에 나보다 큰 수를 찾았을 때를 기록하자.
       int ans = R + 1;
       
       while (L <= R) {
    	   int mid = (L + R) / 2;
    	   
    	   if (A[mid] >= X) {
    		   ans = mid;
    		   R = mid - 1;
    	   } else {
    		   L = mid + 1;
    	   }
       }
       
       return ans;
    }

    //나보다 큰 수 중에 가장 작은 수 찾기
    static int upper_bound(int[] A, int L, int R, int X) {
    	int ans = R + 1;
    	
    	while (L <= R) {
    		int mid = (L + R) / 2;
    		if (A[mid] > X) {
    			ans = mid;
    			R = mid - 1;
    		} else 
    			L = mid + 1;
    	}
    	
    	return ans;
    }

    static void pro() {
        int M = scan.nextInt();
        Arrays.sort(A, 1, N + 1);
        for (int i = 1; i <= M; i++) {
            int X = scan.nextInt();
            int upper = upper_bound(A, 1, N, X);
            int lower = lower_bound(A, 1, N, X);
            sb.append(upper-lower).append(' ');
        }
        System.out.println(sb);
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
