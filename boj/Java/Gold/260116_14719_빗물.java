import java.util.*;
public class Main
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int H = sc.nextInt();
		int W = sc.nextInt();
		int[] block = new int[W];
		int[] leftH = new int[W];
		int[] rightH = new int[W];
		int answer = 0;
		
		int max = 0;
		for(int i = 0; i < W; i++) {
		    int n = sc.nextInt();
		    block[i] = n;
		    max = Math.max(max, n);
		    leftH[i] = max;
		}
    
		max = 0;
		for(int i = W - 1; i >= 0; i--) {
		    max = Math.max(max, block[i]);
		    rightH[i] = max;
		}
		
		for(int i = 0; i < W; i++) {
		    int water = Math.min(leftH[i], rightH[i]) - block[i];
		    if(water > 0) {
		        answer += water;
		    }
		}
		
		System.out.println(answer);
	}
}
