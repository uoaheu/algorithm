/*
[문제] 업그레이드 된 아이템 구하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/273711

[접근]
- ITEM_TREE 테이블을 기준으로 부모 아이템과 자식 아이템의 관계를 파악
- 부모 아이템의 희귀도가 RARE인 경우만 필터링
- 해당 부모 아이템을 재료로 업그레이드된 자식 아이템의 정보를 조회하기 위해 ITEM_INFO 테이블을 부모, 자식 두 번 조인
- 업그레이드된 아이템 기준으로 출력

[성능]
- 시간복잡도: O(N)

[배운 점]
- 자기 참조 구조(부모-자식 관계)를 가진 테이블은 별칭을 명확히 나누는 것이 핵심
*/

SELECT I.ITEM_ID, I.ITEM_NAME, I.RARITY 
FROM ITEM_INFO O
JOIN ITEM_TREE T
ON O.ITEM_ID = T.PARENT_ITEM_ID
JOIN ITEM_INFO I
ON T.ITEM_ID = I.ITEM_ID
WHERE O.RARITY = 'RARE'
ORDER BY I.ITEM_ID DESC;
