/*
[문제] 입국심사 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/43238

[접근]
- X분 안에 처리 가능한 인원 수를 계산해보며, n명을 처리할 수 있는 최소 시간을 이분 탐색 진행
- mid 분일 때 각 심사대가 처리 가능한 사람 수는 mid / times[i], 모든 심사대 합이 n 이상이면 시간 상한 감소
- 오버플로 방지를 위해 모든 합산/곱셈은 long으로 처리

[성능]
- 시간복잡도: O(m * log(max(times) * n)) / m: 심사대 수

[배운 점]
- 우선순위큐에 넣어서 계산하려고 했으나 오버플로 위험과 시간초과 문제 발생
- 최소/최대 값을 구하는 최적화 문제는 값(시간)을 잡고 가능/불가능 판정으로 이분 탐색하는 방식이 효과적
*/

import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        int len = times.length;
        long min = 0; // 최소 시간
        long max = (long) times[len - 1] * n; // 최대 시간
        long result = max;
        
        while(min <= max) {
            long mid = (min + max) / 2;
            long person = 0; // mid분 동안 처리 가능한 총 인원
            
            for(int t : times) {
                person += mid/t;
                if(person >= n) break; // 더 계산할 필요 X
            }
            
            if(person >= n) { // n명 이상 처리 가능 -> 시간 줄이기
                result = mid;
                max = mid - 1;
            } else { // 시간 늘리기
                min = mid + 1;
            }   
        }
        
        return result;
    }
}





