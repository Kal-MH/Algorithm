import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();

    static int n, M;
    static int[] a;

    static void input() {
        n = scan.nextInt();
        a = new int[n + 1];
        M = scan.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = scan.nextInt();
        }
    }

    static void pro() {
        // 투 포인터 기법을 쓰기 위해서 정렬 해주기
        Arrays.sort(a, 1, n + 1);

        int R = 1, ans = Integer.MAX_VALUE;
        for(int L = 1; L <= n; L++) {
        	while (R + 1 <= n && a[R] - a[L] < M) R++;
        	
        	if (a[R] - a[L] >= M) {
        		ans = Math.min(ans, a[R] - a[L]);
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
