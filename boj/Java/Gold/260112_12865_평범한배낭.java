import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 물품의 수
        int K = sc.nextInt(); // 버틸 수 있는 무게
        
        int[] bag = new int[K + 1]; // bag[w] : 무게 w 만큼 담았을 때 가치 
        
        for(int i = 0; i < N; i++) {
            int w = sc.nextInt(); // 무게 
            int v = sc.nextInt(); // 가치
            
            for(int j = K; j >= w; j--) {
                bag[j] = Math.max(bag[j], bag[j - w] + v); 
            }
        }
        
        System.out.println(bag[K]);
	}
}
