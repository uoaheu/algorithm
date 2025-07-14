/*
[문제] N으로 표현 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/42895

[접근]
- dp[i] = N을 i번 사용해서 만들 수 있는 모든 수의 집합
- dp[1] = {N}
- dp[2] = {NN, N+N, N-N, N*N, N/N}
- dp[i] = dp[1] ~ dp[i-1] 조합으로 모든 사칙연산 결과 + 이어붙인 수(NNN...)
- i번 사용해서 number를 만들 수 있다면 즉시 반환

[성능]
- 시간 복잡도: 각 dp[i]는 i를 j와 (i-j)로 나누는 모든 조합에 대해 사칙연산을 수행, 최악의 경우 약 O(8² × 평균 집합 크기)

[배운 점]
- 숫자 조합 생성 -> String.valueOf(N).repeat(i)로 이어붙인 수 만들기
- 사칙연산 조합 문제 -> DP + 집합(Set)으로 중복 제거
- 제한된 반복(최대 8회) -> 완전탐색 기반 DP 가능
*/

import java.util.*;

public class Solution {
    public int solution(int N, int number) {
        // dp[i]: N을 i번 사용해서 만들 수 있는 수들의 집합
        List<Set<Integer>> dp = new ArrayList<>();

        // 0번 인덱스는 사용하지 않음
        dp.add(new HashSet<>());

        for (int i = 1; i <= 8; i++) {
            Set<Integer> currentSet = new HashSet<>();

            // 이어붙인 수 추가 (ex. 5, 55, 555 ...)
            int repeated = Integer.parseInt(String.valueOf(N).repeat(i));
            currentSet.add(repeated);

            // 이전 조합들로 사칙연산 수행
            for (int j = 1; j < i; j++) {
                Set<Integer> set1 = dp.get(j);
                Set<Integer> set2 = dp.get(i - j);

                for (int a : set1) {
                    for (int b : set2) {
                        currentSet.add(a + b);
                        currentSet.add(a - b);
                        currentSet.add(a * b);
                        if (b != 0) currentSet.add(a / b);
                    }
                }
            }

            // 목표 숫자가 만들어졌는지 확인
            if (currentSet.contains(number)) return i;

            dp.add(currentSet);
        }

        return -1;
    }
}
