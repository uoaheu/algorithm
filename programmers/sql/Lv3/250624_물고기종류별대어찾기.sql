/*
[문제] 물고기 종류 별 대어 찾기 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/293261

[접근]
- 서브쿼리: FISH_INFO 테이블에서 FISH_TYPE별로 MAX(LENGTH)를 구함
- 메인쿼리: FISH_INFO와 FISH_NAME_INFO를 JOIN하여, 서브쿼리 결과와 동일한 FISH_TYPE, LENGTH를 가진 행만 추출
- 결과적으로 각 FISH_TYPE마다 가장 큰 LENGTH를 가진 물고기의 ID, FISH_NAME, LENGTH 출력

[성능]
- FISH_INFO 테이블의 (FISH_TYPE, LENGTH)에 인덱스가 있다면 서브쿼리 필터링은 효율적으로 수행 가능

[배운 점]
- 튜플 조건 (FISH_TYPE, LENGTH) IN 구문을 활용하면 여러 조건의 복합 일치를 간결하게 처리 가능
- JOIN 전에 WHERE 절에서 데이터 범위를 필터링하면 불필요한 JOIN을 줄일 수 있음
- GROUP BY와 MAX를 활용해 대표값을 추출하는 방식은 정렬이나 ROW_NUMBER 없이도 간단한 문제 해결에 유용함
*/

SELECT FI.ID, FN.FISH_NAME, FI.LENGTH
FROM FISH_INFO FI
JOIN FISH_NAME_INFO FN
  ON FI.FISH_TYPE = FN.FISH_TYPE
WHERE (FI.FISH_TYPE, FI.LENGTH) IN (
  SELECT FISH_TYPE, MAX(LENGTH)
  FROM FISH_INFO
  GROUP BY FISH_TYPE
);
