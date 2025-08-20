/*
[문제] 대여 기록이 존재하는 자동차 리스트 구하기 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/157341

[접근]
- 자동차 테이블과 대여 이력 테이블을 CAR_ID로 JOIN
- 세단(CAR_TYPE = '세단') 조건을 걸고, 대여 시작일이 2022년 10월 범위에 포함되는지 체크
- 동일 차량이 여러 번 대여될 수 있으므로 DISTINCT를 사용해 중복 제거
- 결과는 문제 조건에 맞게 CAR_ID 내림차순 정렬

[성능]
- 시간복잡도: O(N log N)

[배운 점]
- 날짜 조건을 명확히 지정할 때 BETWEEN, >= AND <=, 또는 LIKE('2022-10%') 등 다양한 표현 가능
- 중복된 대여 기록을 제거하려면 DISTINCT가 필요
*/

SELECT DISTINCT c.CAR_ID FROM CAR_RENTAL_COMPANY_CAR c JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY h on c.CAR_ID = h.CAR_ID
WHERE c.CAR_TYPE = '세단' AND (h.START_DATE BETWEEN '2022-10-01' AND '2022-10-31')
ORDER BY c.CAR_ID DESC
