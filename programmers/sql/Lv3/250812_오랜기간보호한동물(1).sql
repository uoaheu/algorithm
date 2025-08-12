/*
[문제] 오랜 기간 보호한 동물(1) (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/59044

[접근]
- 보호소에 들어왔지만 아직 입양되지 않은 동물을 찾기 위해 ANIMAL_INS 테이블에서 ANIMAL_OUTS 테이블에 없는 ANIMAL_ID를 필터링
- 입소일(DATETIME) 기준으로 오래된 순으로 정렬하여 상위 3마리만 조회

[성능]
- 시간복잡도: O(N + M)

[배운 점]
- NOT IN을 사용해 다른 테이블에 없는 데이터를 필터링할 수 있음
- 오래된 데이터 조회 시 ORDER BY ASC와 LIMIT 활용
- 동일 조건을 LEFT JOIN + IS NULL로도 구현 가능하며, NULL 처리 문제를 피할 수 있음
*/

SELECT NAME, DATETIME FROM ANIMAL_INS
WHERE ANIMAL_ID NOT IN (SELECT ANIMAL_ID FROM ANIMAL_OUTS)
ORDER BY DATETIME ASC
LIMIT 3;
