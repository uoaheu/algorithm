/*
[문제] 업그레이드 할 수 없는 아이템 구하기 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/273712

[접근]
- 문제 요구사항은 "더 이상 업그레이드할 수 없는 아이템" -> 즉, 부모 아이템으로 등록되지 않은 아이템 찾기
- 따라서 ITEM_INFO를 기준으로 ITEM_TREE와 LEFT JOIN 수행 후 부모로 등장하지 않는 아이템(PARENT_ITEM_ID가 NULL)을 필터링

[성능]
- 시간복잡도: O(N)

[배운 점]
- LEFT JOIN 후 WHERE 절에서 NULL을 이용하면 해당 테이블에 없는 데이터를 쉽게 찾을 수 있음
- JOIN 후 존재하지 않는 행을 찾을 때는 WHERE T.column IS NULL 패턴을 사용
- INNER JOIN은 교집합, LEFT JOIN + IS NULL은 차집합처럼 동작함
*/

SELECT I.ITEM_ID, I.ITEM_NAME, I.RARITY 
FROM ITEM_INFO I LEFT JOIN ITEM_TREE T ON I.ITEM_ID = T.PARENT_ITEM_ID
WHERE PARENT_ITEM_ID IS NULL
ORDER BY I.ITEM_ID DESC;
