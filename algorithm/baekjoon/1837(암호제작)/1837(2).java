import java.io.*;
import java.util.StringTokenizer;

public class Math_1837 {
    static String p;
    static int k;
    static boolean isAccept(int x){
        int ret=0;
        char[] arr = p.toCharArray();
        for(int i=0; i < arr.length; i++ ){
            ret = (ret * 10 + (arr[i]-'0')) % x;
        }
        if(ret==0){
            return true;
        }
        else{
            return false;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        p = st.nextToken();
        k = Integer.parseInt(st.nextToken());
        String ans="";
        boolean[] primes = new boolean[k+1];
        primes[1]=true;
        for(int i=2; i<k; i++){
            if(primes[i])   continue;
            if(isAccept(i)){
                ans = "BAD "+i;
                break;
            }
            for(int j=i+i; j<k; j+= i){
                primes[j] = true;
            }
        }
        if(ans.equals(""))
            ans = "GOOD";
        bw.write(ans);
        bw.flush();
        bw.close();
    }
}
