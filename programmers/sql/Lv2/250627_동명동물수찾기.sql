/*
[문제] 동명 동물 수 찾기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/59041

[접근]
- ANIMAL_INS 테이블에서 이름(NAME) 기준으로 그룹화
- 이름이 NULL이 아닌 데이터만 집계 대상으로 설정
- COUNT로 같은 이름의 개수를 센 후, 2번 이상 등장한 이름만 필터링 (HAVING)
- 최종 결과는 이름 오름차순 정렬

[성능]
- GROUP BY와 COUNT는 인덱스 없이도 소규모 데이터에서 충분히 빠르게 처리 가능

[배운 점]
- NULL 값은 GROUP BY에서 자동 제외되지 않으므로 WHERE 절에서 명시적으로 제거 필요(현재 문제 코드 상에서는 WHERE이 없어도 정답)
- GROUP BY + HAVING 절을 통해 조건 기반 집계 필터링 가능
*/

SELECT NAME, COUNT(NAME) AS COUNT
FROM ANIMAL_INS
WHERE NAME IS NOT NULL
GROUP BY NAME
HAVING COUNT(NAME) >= 2
ORDER BY NAME ASC;
