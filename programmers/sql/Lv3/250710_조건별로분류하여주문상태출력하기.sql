/*
[문제] 조건별로 분류하여 주문상태 출력하기 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/131113

[접근]
- 주문 정보 테이블(FOOD_ORDER)에서 출고일(OUT_DATE)을 기준으로 출고 상태를 분류
- CASE 문을 활용해 조건에 따라 '출고완료', '출고대기', '출고미정' 값을 설정
  1) 출고일이 '2022-05-01' 이하이면 → '출고완료'
  2) 출고일이 NULL이면 → '출고미정'
  3) 그 외 → '출고대기'
- 날짜 포맷은 'YYYY-MM-DD' 형식으로 지정
- ORDER_ID 기준으로 오름차순 정렬

[성능]
- 시간복잡도: O(N log N) 테이블 전체 조회 및 정렬
- 단일 테이블 조회이므로 조인 비용 없음

[배운 점]
- CASE 문을 활용해 조건에 따른 출력 값을 가공하는 방법
- DATE_FORMAT을 통해 날짜 형식을 원하는 포맷으로 바꾸는 방법
- SQL에서도 로직 분기 및 가공이 가능하며, 복잡한 IF 조건을 대체할 수 있음
*/

SELECT ORDER_ID, PRODUCT_ID, DATE_FORMAT(OUT_DATE, '%Y-%m-%d'),
    (CASE
    WHEN OUT_DATE <= '2022-05-01' THEN '출고완료'
    WHEN OUT_DATE IS NULL THEN '출고미정'
    ELSE '출고대기'
END) AS '출고여부'
FROM FOOD_ORDER
ORDER BY ORDER_ID ASC;
