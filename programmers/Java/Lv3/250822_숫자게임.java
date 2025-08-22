/*
[문제] 숫자 게임 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/12987

[접근]
- B가 A의 각 원소를 이길 수 있는 최대 경우의 수를 구하는 것
- A, B를 오름차순 정렬 후, A를 앞에서부터 순회하면서 B의 값이 큰지 체크
- 크다면 두개 모두 포인터 값 + 1, 만약 A보다 B가 작다면 B 포인터 + 1 후 다음 값과 비교

[성능]
- 시간복잡도: O(n log n)

[배운 점]
- 정렬 후 두 포인터 그리디 방식으로 접근
- 내부 while문을 활용해 범위를 먼저 체크해서 불필요한 분기 제거 가능
*/

import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int cnt = 0; // B의 승점
        int idx = 0; // B 배열 포인터

        out: for(int i = 0; i < A.length; i++) {
            while(idx < B.length) {
                // 더이상 A[i]를 이길 B가 없는 경우 -> 조기 종료
                if(idx == B.length) {
                    break out;
                }
                // B에서 A[i]를 이길 수 있는 원소를 찾았다면, 승점 + 1 & B 포인터 + 1
                if(A[i] < B[idx]) {
                    cnt++;
                    idx++;
                    break;
                } else {
                    idx++; // B에서 A[i]를 이길 수 있는 원소를 찾을 때까지 포인터 이동
                }   
            }
            
        }
        return cnt;
    }
}
