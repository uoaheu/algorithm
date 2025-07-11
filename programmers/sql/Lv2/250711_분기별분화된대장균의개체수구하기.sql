/*
[문제] 분기별 분화된 대장균의 개체 수 구하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/299308

[접근]
- ECOLI_DATA 테이블에서 DIFFENTIATION_DATE를 기준으로 분기를 계산
- MySQL 내장 함수 QUARTER()를 활용해 1~4분기로 분류
- 분기별 개체 수를 COUNT(*)로 계산
- 분기 문자열을 '1Q', '2Q' 형식으로 표시하기 위해 CONCAT 사용
- GROUP BY로 분기별 집계 후 ORDER BY로 오름차순 정렬

[성능]
- QUARTER()는 MySQL 내장 함수로 가볍고 빠름
- GROUP BY + COUNT 쿼리는 효율적이며 인덱스 영향을 거의 받지 않음
- 데이터 양이 많아도 분기 수가 4개뿐이므로 성능 부담 적음

[배운 점]
- 날짜 데이터에서 분기 추출은 QUARTER() 함수로 간단히 처리 가능
- CONCAT으로 동적 문자열 조합 가능 (숫자 + 'Q' 등)
- GROUP BY 컬럼은 SELECT 절에서 별칭을 쓰더라도 실제 함수 사용 가능
*/

SELECT
  CONCAT(QUARTER(DIFFERENTIATION_DATE), 'Q') AS QUARTER,
  COUNT(*) AS ECOLI_COUNT
FROM ECOLI_DATA
GROUP BY QUARTER
ORDER BY QUARTER;
