import java.util.*;
public class Main {
    public static void main(String[] args) {
	      Scanner sc = new Scanner(System.in);
	      int N = sc.nextInt();
  	    int M = sc.nextInt();
  	    int[] arr = new int[N];
      
  	    for(int i = 0; i < N; i++) {
  	        arr[i] = sc.nextInt();
  	    }
	    
  	    int left = 0; 
  	    int right = 0;
        int sum = 0;
        int count = 0;
        
    		while(true) {
    		    if(sum >= M) {
    		        if(sum == M) count++;
    		        sum -= arr[left++];
    		    } else {
    		        if(right == N) break;
    		        sum += arr[right++];
    		    }
    		}
    		System.out.println(count);
  	}
}
