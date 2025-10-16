/*
[문제] 우유와 요거트가 담긴 장바구니 (Lv4)
https://school.programmers.co.kr/learn/courses/30/lessons/62284

[접근]
- 장바구니별로 Milk, Yogurt 두 품목이 모두 있는지 확인
- 한 품목이 여러 행으로 들어 있을 수 있어 중복 제거 필요 -> COUNT(DISTINCT NAME)=2 사용

[성능]
- 시간복잡도: O(K log K)

[배운 점]
- 두 품목 모두 존재 검증은 HAVING COUNT(DISTINCT NAME) = 목표개수 패턴이 깔끔함
*/

SELECT CART_ID 
FROM CART_PRODUCTS
WHERE NAME IN ('Milk', 'Yogurt')
GROUP BY CART_ID
HAVING COUNT(DISTINCT NAME) = 2
ORDER BY CART_ID;
