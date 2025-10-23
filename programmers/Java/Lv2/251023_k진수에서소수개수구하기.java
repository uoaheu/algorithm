/*
[문제] k진수에서 소수 개수 구하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/92335

[접근]
- 10진수 n을 k진수 문자열로 변환
- 변환된 문자열을 0 기준으로 잘라 숫자 조각 배열만들고 빈 문자열 건너뛰고 다시 10진수로 변환해서 소수 여부 판별
- 소수라면 count 1 증가, 모든 조각에 대해 반복

[성능]
- 시간 복잡도: O(L * sqrt(x))

[배운 점]
- 문자열 split("0+") 시 연속된 0을 한 번에 자르기 위해 정규식 사용
- split 후 빈 문자열("")이 생길 수 있으므로 .isEmpty() 체크 필수
- 조각 숫자가 매우 클 수 있으므로 int 대신 long 사용
- 소수 판별 시 sqrt(n)까지만 검사하면 충분, 루프 조건은 ≤로 설정해야 제곱수 누락 방지
*/

import java.util.*;
class Solution {
    public int solution(int n, int k) {
        String ch = change(n, k);
        String[] parts = ch.split("0+");
        int count = 0;
        for(String p : parts) {
            if(p.isEmpty()) continue;
            long num = Long.parseLong(p);
            if(isPrime(num)) count++;
        }
        return count;
    }
    
    static String change(int n, int k) {
        if(n == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int x = n;
        while(x > 0) {
            sb.append(x % k);
            x /= k;
        }
        return sb.reverse().toString();
    }
    
    static boolean isPrime(long n) {
        if(n < 2) return false;
        if(n == 2) return true;
        if(n % 2 == 0) return false;
        long limit = (long) Math.sqrt(n);
        for(long i = 3; i <= limit; i += 2) {
            if(n % i == 0) return false;
        }
        return true;
    }
}
