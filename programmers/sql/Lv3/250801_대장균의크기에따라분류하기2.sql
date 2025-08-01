/*
[문제] 대장균의 크기에 따라 분류하기 2 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/301649

[접근]
- ECOLI_DATA 테이블에서 대장균의 크기(SIZE_OF_COLONY)를 기준으로 내림차순 정렬
- NTILE(4) 윈도우 함수를 사용해 전체 데이터를 4분위로 나누고 'CRITICAL', 'HIGH', 'MEDIUM', 'LOW'로 그룹명 부여
- 결과는 ID 오름차순 정렬로 출력

[성능]
- 시간복잡도: O(N log N) (내림차순 정렬 기반의 NTILE 계산)

[배운 점]
- NTILE(n) OVER (ORDER BY ...)을 활용하면 손쉽게 분위 구간을 만들 수 있음
- 내림차순 정렬을 기반으로 NTILE을 적용하면 "상위 n%" 분류도 직관적으로 처리 가능
*/

SELECT ID,
    CASE NTILE(4) OVER (ORDER BY SIZE_OF_COLONY DESC)
        WHEN 1 THEN 'CRITICAL'   -- 상위 25%
        WHEN 2 THEN 'HIGH'       -- 상위 25~50%
        WHEN 3 THEN 'MEDIUM'     -- 상위 50~75%
        ELSE 'LOW'               -- 하위 25%
    END AS COLONY_NAME
FROM ECOLI_DATA
ORDER BY 1 ASC;
