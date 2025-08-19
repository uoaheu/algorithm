/*
[문제] 입양 시각 구하기(2) (Lv4)
https://school.programmers.co.kr/learn/courses/30/lessons/59413

[접근]
- 0~23시까지 모든 시간을 갖는 임시 테이블 HOURS를 WITH RECURSIVE로 생성
- HOURS를 기준 테이블로 두고 ANIMAL_OUTS를 LEFT JOIN하여 해당 시각의 입양 건수를 집계
- 데이터가 없는 시간대도 0으로 출력되도록 IFNULL(COUNT(...), 0) 사용
- 시간 기준으로 GROUP BY, 오름차순 정렬

[성능]
- 시간복잡도: O(N + 24)

[배운 점]
- 누락된 구간을 포함해야 할 때는 기준(0~23)을 먼저 만들고 LEFT JOIN으로 채우는 패턴 활용
- MySQL에서 연속 숫자 생성은 WITH RECURSIVE로 간단히 구현 가능
- 집계 결과에서 NULL을 0으로 바꿀 때는 IFNULL/COALESCE를 활용
*/

WITH RECURSIVE HOURS AS (
    SELECT 0 AS HOUR
    UNION ALL
    SELECT HOUR + 1 FROM HOURS WHERE HOUR < 23
)

SELECT H.HOUR, IFNULL(COUNT(A.ANIMAL_ID), 0) AS COUNT
FROM HOURS H
LEFT JOIN ANIMAL_OUTS A
    ON H.HOUR = HOUR(A.DATETIME)
GROUP BY H.HOUR
ORDER BY H.HOUR;
