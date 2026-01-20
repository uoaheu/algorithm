import java.util.*;
public class Main {
    static int L, C;
    static char[] arr;
    static StringBuilder sb = new StringBuilder();
  
  	public static void main(String[] args) {
    		Scanner sc = new Scanner(System.in);
    		L = sc.nextInt();
    		C = sc.nextInt();
    		arr = new char[C];
    
        for (int i = 0; i < C; i++) {
            arr[i] = sc.next().charAt(0);
        }
    
        Arrays.sort(arr);
    
        // 최소 한 개의 모음 & 최소 두 개의 자음
        make(0, 0, 0, 0, new char[L]); // 인덱스, 길이, 모음 개수, 자음 개수, 배열
    
        System.out.print(sb.toString());
  	}
	
  	static void make(int idx, int len, int vowelCnt, int consonantCnt, char[] chosen) {
  	    if(len == L) {
            // 모음 >= 1 && 자음 >= 2
            if(vowelCnt >= 1 && consonantCnt >= 2) {
                sb.append(chosen).append('\n');
            }
            return;
  	    }
  	    
  	    // 더이상 뽑지 못하는 경우 
  	    if(idx == C) {
  	        return;
  	    }
  	    
  	    // 남은 문자로 L을 채울 수 없으면 종료
        if(len + (C - idx) < L) return;
          
        // idx 문자 선택
        char ch = arr[idx];
        chosen[len] = ch;
          
        if(isVowel(ch)) {
            make(idx + 1, len + 1, vowelCnt + 1, consonantCnt, chosen);
        } else {
            make(idx + 1, len + 1, vowelCnt, consonantCnt + 1, chosen);
        }
          
        // 안뽑고 넘어가는 경우 
        make(idx + 1, len, vowelCnt, consonantCnt, chosen);
  	}
	
  	static boolean isVowel(char ch) {
  	    if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
  	        return true;
  	    }
  	    return false;
  	}
}

