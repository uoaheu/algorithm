/*
[문제] 루시와 엘라 찾기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/59046

[접근]
- ANIMAL_INS 테이블에서 이름(NAME)이 'Lucy', 'Ella', 'Pickle', 'Rogan', 'Sabrina', 'Mitty'인 동물 찾기
- 조건에 맞는 동물의 ANIMAL_ID, NAME, SEX_UPON_INTAKE 컬럼 출력
- ANIMAL_ID 오름차순으로 정렬하여 출력

[성능]
- NAME 컬럼에 인덱스가 있다면 IN 조건은 빠르게 동작
- ORDER BY 절은 결과를 정렬해야 하므로 결과 레코드 수에 따라 성능 영향을 받을 수 있음

[배운 점]
- IN 절을 활용해 다수의 값을 조건으로 필터링할 수 있음
- 필요한 컬럼만 선택적으로 조회하여 효율적인 쿼리를 작성할 수 있음
*/

SELECT ANIMAL_ID, NAME, SEX_UPON_INTAKE
FROM ANIMAL_INS
WHERE NAME IN ('Lucy', 'Ella', 'Pickle', 'Rogan', 'Sabrina', 'Mitty')
ORDER BY ANIMAL_ID;
