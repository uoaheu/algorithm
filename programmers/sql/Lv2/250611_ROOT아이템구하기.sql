/*
[문제] ROOT 아이템 구하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/273710

[접근]
- ITEM_TREE 테이블에서 PARENT_ITEM_ID가 NULL인 ITEM_ID는 '최상위 아이템'
- 해당 ITEM_ID를 먼저 서브쿼리에서 필터링
- ITEM_INFO 테이블에서 ITEM_ID 기준으로 이름(ITEM_NAME) 함께 조회
- 즉, '부모가 없는 아이템'의 ID와 이름 출력

[성능]
- ITEM_TREE.PARENT_ITEM_ID에 인덱스가 있으면 빠르게 NULL 필터링 가능
- 서브쿼리 + IN 구조는 직관적이며 적은 양일 때 효율적

[배운 점]
- IS NULL 조건은 조인보다 서브쿼리로 명확하게 표현 가능
- 계층적 구조(부모-자식) 파악 시 PARENT_ID NULL 여부 확인은 기본 패턴
*/

SELECT ITEM_ID, ITEM_NAME
FROM ITEM_INFO
WHERE ITEM_ID IN (
        SELECT ITEM_ID
        FROM ITEM_TREE
        WHERE PARENT_ITEM_ID IS NULL
);
