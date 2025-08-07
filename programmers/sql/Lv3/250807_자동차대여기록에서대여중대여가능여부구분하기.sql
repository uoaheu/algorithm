/*
[문제] 자동차 대여 기록에서 대여중 / 대여 가능 여부 구분하기 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/157340

[접근]
- 2022-10-16가 차량 대여 기간(START_DATE ~ END_DATE) 사이에 포함되는지 확인
- 서브쿼리로 해당 날짜에 대여중인 CAR_ID 목록을 구함
- 메인쿼리에서 모든 차량(CAR_ID)을 DISTINCT로 조회하고, 서브쿼리 결과에 포함되면 '대여중', 포함되지 않으면 '대여 가능'으로 분류

[성능]
- 시간복잡도: O(N + M)

[배운 점]
- 특정 날짜가 기간 사이에 포함되는지 확인할 때 BETWEEN을 사용하는 방법
- IN (서브쿼리)와 CASE 문을 활용하면 조건 분기 로직을 SQL 안에서 처리할 수 있음
- DISTINCT로 중복 CAR_ID 제거 가능
*/

SELECT DISTINCT CAR_ID, 
    CASE
        WHEN CAR_ID IN (SELECT CAR_ID 
                        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                        WHERE '2022-10-16' BETWEEN START_DATE AND END_DATE) 
                    THEN '대여중'
        ELSE '대여 가능'
    END AS AVAILABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
ORDER BY CAR_ID DESC;
