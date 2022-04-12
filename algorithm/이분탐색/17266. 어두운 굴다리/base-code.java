import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] A;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        A = new int[M + 1];
        for (int i = 1; i <= M; i++) {
            A[i] = scan.nextInt();
        }
    }

    static boolean determination(int height) {
        int last = 0;
        
        //가로등이 닿는 가장 오른쪽 지점을 생각하자.
        // - 다음 가로등의 왼쪽 범위는 이전 오른쪽 지점과 같거나 겹쳐야 한다.
        for(int i = 1; i <= M; i++) {
        	// TODO
        }
    }

    static void pro() {
    	//L과 R의 범위는?, 정렬이 필요한가
      // TODO
        
        while (L <= R) {
        	// TODO
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
