/*
[문제] 자동차 평균 대여 기간 구하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/157342

[접근]
- 대여 기록 테이블(CAR_RENTAL_COMPANY_RENTAL_HISTORY)에서 자동차별로 대여 기간을 계산
- DATEDIFF(end_date, start_date) + 1을 통해 실제 대여 기간(당일 포함)을 구함
- 평균 대여 기간이 7일 이상인 자동차만 조회
- 자동차 ID별로 그룹화하여 평균 대여 기간을 구하고 소수점 1자리로 반올림

[성능]
- 서브쿼리에서 미리 대여일수를 구해 평균 계산을 단순화

[배운 점]
- DATEDIFF는 두 날짜의 차이를 일(day) 단위로 계산하며, 시작일 포함을 위해 +1 필요
- HAVING 절은 GROUP BY 이후의 조건 필터링에 사용됨
- ROUND 함수로 소수점 자리수 조정 가능
*/

SELECT NEW.CAR_ID, ROUND(AVG(NEW.DATE), 1) AS AVERAGE_DURATION
FROM (
  SELECT CAR_ID, datediff(end_date, start_date)+1 AS DATE 
  from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    ) AS NEW
GROUP BY NEW.CAR_ID
HAVING AVERAGE_DURATION >= 7
ORDER BY AVERAGE_DURATION DESC, CAR_ID DESC
