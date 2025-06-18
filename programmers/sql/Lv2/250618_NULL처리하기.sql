/*
[문제] NULL 처리하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/59410

[접근]
- ANIMAL_INS 테이블에서 동물의 종류, 이름, 성별 정보를 조회
- 이름이 NULL인 경우 'No name'으로 표시 (IFNULL 함수 활용)
- ANIMAL_ID를 기준으로 오름차순 정렬

[성능]
- 정렬에 사용하는 ANIMAL_ID에 인덱스가 있으면 성능 문제 없음
- IFNULL은 SELECT 단계에서의 단순 표현 처리로 비용 낮음

[배운 점]
- NULL 값을 대체할 때는 IFNULL(값, 대체값) 또는 COALESCE 사용
*/

SELECT ANIMAL_TYPE, 
    IFNULL(NAME, 'No name') AS NAME,
    SEX_UPON_INTAKE
FROM ANIMAL_INS
ORDER BY ANIMAL_ID;
