/*
[문제] [PCCP 기출문제] 2번 / 퍼즐 게임 챌린지 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/340212

[접근]
- 제한 시간 안에 모든 퍼즐을 클리어할 수 있는 최소 숙련도 찾기
- diffs[i] : i번째 퍼즐의 난이도, times[i] : 해당 퍼즐을 푸는 데 걸리는 시간
- 숙련도가 낮을수록, diffs[i] > mid인 퍼즐에서 오답이 발생하므로 추가 시간 발생
- 이진 탐색을 통해 숙련도를 줄여가며 limit 이하로 가능한 최소값 찾기

[성능]
- 시간 복잡도: O(N log M)

[배운 점]
- '제한 조건을 만족하는 최소/최대 값'을 찾을 때는 이진 탐색 패턴 적용
*/

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int max = 0;
        
        for(int i = 0; i < diffs.length; i++){
            max = Math.max(max, diffs[i]);
        }
        
        int answer = max;
        int left = 1;
        int right = max;
        
        while(left < right) {
            int mid = (left + right) / 2; // 숙련도
            long time = 0;
            time += times[0];
            
            for(int i = 1; i < diffs.length; i++) {
                if(diffs[i] <= mid) {
                    time += times[i];
                } else {
                    int num = diffs[i] - mid; // 틀린 횟수
                    time += num * (times[i] + times[i - 1]) + times[i];
                }
            }
            
            if(time <= limit){
                answer = Math.min(answer, mid);
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        
        return answer;
    }
}
