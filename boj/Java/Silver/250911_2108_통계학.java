import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N];
        int[] freq = new int[8001]; // -4000 ~ 4000
        long sum = 0;

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            a[i] = x;
            sum += x;
            freq[x + 4000]++;
        }

        Arrays.sort(a);

        // 산술평균 (반올림)
        int mean = (int)Math.round((double)sum / N);

        // 중앙값
        int median = a[N / 2];

        // 최빈값 (여러 개면 두 번째로 작은 값)
        int maxFreq = 0;
        for (int f : freq) maxFreq = Math.max(maxFreq, f);

        int mode = 0;
        int found = 0; // maxFreq를 가진 값 몇 번째로 만났는지
        for (int i = 0; i < 8001; i++) {
            if (freq[i] == maxFreq) {
                mode = i - 4000;
                found++;
                if (found == 2) break; // 두 번째로 작은 값에서 멈춤
            }
        }

        // 범위
        int range = a[N - 1] - a[0];

        sb.append(mean).append('\n')
          .append(median).append('\n')
          .append(mode).append('\n')
          .append(range).append('\n');

        System.out.print(sb.toString());
    }
}
