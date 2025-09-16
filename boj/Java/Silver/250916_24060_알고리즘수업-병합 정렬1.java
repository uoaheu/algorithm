import java.util.Scanner;

public class Main {
        static int[] temp;
        static int cnt, result, K;
    
        public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        cnt = 0;
        result = -1;
        int num = sc.nextInt();
        int[] arr = new int[num];
        K = sc.nextInt();
        temp = new int[num];

        for(int i = 0 ; i < num ; i++){
            arr[i] = sc.nextInt();
        }
        merge_sort(arr, 0, arr.length - 1);
        System.out.print(result);
      }

        static void merge_sort(int A[], int l, int h){
            if(l < h){
                int m = (l + h) / 2;
                merge_sort(A, l, m);
                merge_sort(A, m + 1, h);
                merge(A, l, m, h);
            }
        }

static void merge(int A[], int l, int m, int h){
        int i = l;
        int j = m + 1;
        int t = 0;
        
        while(i <= m && j <= h){
            if(A[i] <= A[j]){
                temp[t++] = A[i++];
            }else{
                temp[t++] = A[j++];
            }
        }

        while(i <= m){
            temp[t++] = A[i++];
        }

        while(j <= h){
            temp[t++] = A[j++];
        }
        t = 0;
        i = l;

        while(i <= h){
            cnt++;
            if(cnt==K){
                result = temp[t];
                break;
            }
            A[i++] = temp[t++];
        }     
   }
}
