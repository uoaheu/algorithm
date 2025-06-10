/*
[문제] 대여 횟수가 많은 자동차들의 월별 대여 횟수 구하기 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/151139

[접근]
- 서브쿼리 : 해당 기간(2022-08-01 ~ 2022-10-31) 동안 CAR_ID별 대여 횟수가 5 이상인 차량 필터링
- 메인쿼리 : 동일 기간 내에서 해당 CAR_ID들만 대상으로 월별 대여 횟수 집계
- GROUP BY로 월과 차량 ID 기준 집계 → COUNT(*)로 대여 횟수(RECORDS) 계산
- HAVING으로 월별 대여횟수가 0 이상인 경우만 추출
- 정렬은 MONTH 오름차순, CAR_ID 내림차순

[성능]
- 서브쿼리 GROUP BY + COUNT는 인덱스가 있다면 효율적 처리 가능
- WHERE IN 서브쿼리는 비교적 직관적이나 대용량일 경우 EXISTS로 대체 고려

[배운 점]
- MONTH() 함수로 날짜에서 월 추출 가능
- IN + GROUP BY + HAVING 패턴은 특정 조건 필터링에 효과적
*/

SELECT MONTH(START_DATE) AS MONTH, CAR_ID, COUNT(*) AS RECORDS
FROM car_rental_company_rental_history
WHERE CAR_ID IN (SELECT CAR_ID
                FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                WHERE START_DATE BETWEEN '2022-08-01' AND '2022-10-31'
                GROUP BY CAR_ID
                HAVING COUNT(*) >= 5)
AND START_DATE BETWEEN '2022-08-01' AND '2022-10-31'
GROUP BY Month(START_DATE), CAR_ID
HAVING COUNT(*) > 0
ORDER BY Month(START_DATE) ASC, CAR_ID DESC
