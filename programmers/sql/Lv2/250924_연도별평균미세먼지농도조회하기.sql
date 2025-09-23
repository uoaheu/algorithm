/*
[문제] 연도 별 평균 미세먼지 농도 조회하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/284530

[접근]
- 수원 지역의 데이터만 출력
- YM의 연도 단위로 그룹화해 PM_VAL1, PM_VAL2의 평균 구하기
- 연도는 YEAR(YM)로 숫자(정수)로 변환
- 출력 형식은 소수 둘째 자리까지 반올림

[성능]
- 시간복잡도: O(N)

[배운 점]
- GROUP BY에서는 별칭(alias) 대신 원식(ex YEAR(YM))을 쓰면 예약어/모호성 문제를 피할 수 있음
- DATE_FORMAT(YM,'%Y') = 문자열, YEAR(YM) = 정수 : 채점기에서 형식 차이로 오답 처리 가능성 존재
*/

-- 결과는 동일하게 나오는 것 같지만, 틀린 코드
-- SELECT 
--   DATE_FORMAT(YM, "%Y") AS YEAR, 
--   ROUND(AVG(PM_VAL1), 2) AS PM10, 
--   ROUND(AVG(PM_VAL2), 2) AS `PM2.5` 
-- FROM AIR_POLLUTION
-- WHERE LOCATION1 = '경기도' AND LOCATION2 = '수원'
-- GROUP BY YEAR
-- ORDER BY 1 ASC;

SELECT
  YEAR(YM) AS YEAR,
  ROUND(AVG(PM_VAL1), 2) AS PM10,
  ROUND(AVG(PM_VAL2), 2) AS `PM2.5`
FROM AIR_POLLUTION
WHERE LOCATION1 = '경기도' AND LOCATION2 = '수원'
GROUP BY YEAR(YM)
ORDER BY YEAR(YM);
