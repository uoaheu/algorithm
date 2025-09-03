/*
[문제] 자동차 대여 기록 별 대여 금액 구하기 (Lv4)
https://school.programmers.co.kr/learn/courses/30/lessons/151141

[접근]
- '트럭' 차량의 대여 이력별 최종 요금(FEE)을 계산
- DATEDIFF(END, START) + 1 로 '양 끝단 포함' 일수 산출, 큰 구간부터 CASE로 라벨링
- 할인 계획(CAR_RENTAL_COMPANY_DISCOUNT_PLAN)과 (CAR_TYPE, DURATION_TYPE) 기준 LEFT JOIN -> 7일 미만(NULL)도 결과 포함되도록 하고, 할인율은 IFNULL(...,0)
- FEE 계산 시 DAILY_FEE * 일수 * (100 - 할인율) / 100, 결과는 정수로 표현 - CAST(... AS UNSIGNED)
- 대여 금액 기준 내림차순, 대여 금액이 같은 경우 대여 기록 ID 기준 내림차순 정렬

[성능]
- 시간복잡도: O(N) 스캔 + 조인

[배운 점]
- 7일 미만은 할인 미적용이므로 INNER JOIN이 아닌 LEFT JOIN + IFNULL 활용
- DATE 같은 예약어는 별칭으로 피하고 의미 있는 이름(DURATION_TYPE) 사용
- CAST, FLOOR 등을 활용해 정수 변환
*/

SELECT 
    R.HISTORY_ID,
    CAST(R.DAILY_FEE * R.RENT_DAYS * (100 - IFNULL(P.DISCOUNT_RATE, 0)) / 100 AS UNSIGNED) AS FEE
FROM (
    SELECT 
        h.HISTORY_ID,
        c.CAR_TYPE,
        c.DAILY_FEE,
        DATEDIFF(h.END_DATE, h.START_DATE) + 1 AS RENT_DAYS,
        CASE
          WHEN DATEDIFF(h.END_DATE, h.START_DATE) + 1 >= 90 THEN '90일 이상'
          WHEN DATEDIFF(h.END_DATE, h.START_DATE) + 1 >= 30 THEN '30일 이상'
          WHEN DATEDIFF(h.END_DATE, h.START_DATE) + 1 >= 7  THEN '7일 이상'
          ELSE NULL
        END AS DURATION_TYPE
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY h
    JOIN CAR_RENTAL_COMPANY_CAR c ON c.CAR_ID = h.CAR_ID
    WHERE c.CAR_TYPE = '트럭'
) AS R
LEFT JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN P ON P.CAR_TYPE = R.CAR_TYPE AND P.DURATION_TYPE = R.DURATION_TYPE
ORDER BY FEE DESC, R.HISTORY_ID DESC;
