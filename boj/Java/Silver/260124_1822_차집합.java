import java.util.*;
public class Main {
    public static void main(String[] args) {
	      Scanner sc = new Scanner(System.in);
		    int n = sc.nextInt();
        int m = sc.nextInt();
        int[] A = new int[n];
        int[] B = new int[m];
        
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        
        for (int i = 0; i < m; i++) {
            B[i] = sc.nextInt();
        }
        
        Arrays.sort(A);
        Arrays.sort(B);

        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;
        int cnt = 0;

        while (i < n && j < m) {
            if (A[i] < B[j]) {
                sb.append(A[i]).append(' ');
                cnt++;
                i++;
            } else if (A[i] == B[j]) {
                i++; 
                j++;
            } else {
                j++;
            }
        }

        while (i < n) {
            sb.append(A[i]).append(' ');
            cnt++;
            i++;
        }

        System.out.println(cnt);
        if (cnt > 0) System.out.println(sb);
	  }
}
