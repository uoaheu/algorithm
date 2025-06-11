/*
[문제] 중복 제거하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/59408

[접근]
- ANIMAL_INS 테이블에서 NAME 컬럼이 NULL이 아닌 데이터만 필터링
- 중복된 이름은 제외하고 고유한 이름의 수를 세기 위해 COUNT(DISTINCT NAME) 사용

[성능]
- NULL 필터링은 인덱스 없이도 빠르게 동작
- DISTINCT는 정렬 기반으로 처리되므로 데이터 양이 많을 경우 성능에 영향 있음

[배운 점]
- NULL 값 제외 시 `IS NOT NULL` 조건 사용
- 중복 제거한 개수 세기에는 `COUNT(DISTINCT 컬럼)` 패턴이 효과적
*/

SELECT COUNT(DISTINCT NAME) AS count
FROM ANIMAL_INS
WHERE NAME IS NOT NULL;
