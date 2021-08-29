package day1;

import java.io.*;
import java.util.*;


public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int L, C;
    static char[] characters, selected;
    
    public static void main(String[] args) {
    	input();
    	//sorting
    	Arrays.sort(characters);
    	
    	rec_func(1);
    	
    	System.out.println(sb.toString());
    }
    static void input() {
    	L = scan.nextInt();
    	C = scan.nextInt();
    	
    	selected = new char[L + 1];
    	characters = new char[C + 1];
    	for(int i = 1; i <= C; i++) {
    		characters[i] = scan.next().charAt(0);
    	}
    }
    
    static boolean checkable() {
    	int consonant = 0;
    	int vowel = 0;
    	for(int i = 1; i <= L; i++) {
    		if (selected[i] == 'a' || selected[i] == 'i' || selected[i] == 'e'
    				|| selected[i] == 'u' || selected[i] == 'o')
    			vowel++;
    		else
    			consonant++;
    	}
    	
    	if (vowel >= 1 && consonant >= 2)
    		return (true);
    	return (false);
    }

    static void rec_func(int k) {
    	//1. 목적지인가
    	if (k == L + 1) {
    		if (checkable()) {    			
    			for(int i = 1; i <= L; i++)
    				sb.append(selected[i]);
    			sb.append("\n");
    		}
    	}
    	//갈 수 있는 곳 순회
    	else {
    		for(int i = k; i <= C; i++) {
    			if (selected[k - 1] >= characters[i])
    				continue;
    			selected[k] = characters[i];
    			rec_func(k + 1);
    			selected[k] = 0;
    		}
    	}
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
