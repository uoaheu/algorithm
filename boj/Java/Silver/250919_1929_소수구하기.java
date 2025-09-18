import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        boolean[] isComposite = new boolean[N + 1]; // true면 소수 아님

        if (N >= 0) isComposite[0] = true;
        if (N >= 1) isComposite[1] = true;

        int limit = (int)Math.sqrt(N);
        for (int i = 2; i <= limit; i++) {
            if (!isComposite[i]) {
                // i*i 이전은 이미 더 작은 소수의 배수로 지워졌음
                for (long j = (long)i * i; j <= N; j += i) {
                    isComposite[(int)j] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = Math.max(2, M); i <= N; i++) {
            if (!isComposite[i]) sb.append(i).append('\n');
        }

        System.out.print(sb.toString());
    }
}
