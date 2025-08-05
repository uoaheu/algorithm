/*
[문제] 조건에 부합하는 중고거래 상태 조회하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/164672

[접근]
- 중고거래 게시판(BOARD)에서 작성일이 '2022-10-05'인 글만 조회
- 상태(STATUS)를 SALE -> 판매중, RESERVED -> 예약중, DONE -> 거래완료로 매핑
- 조건에 맞는 게시글을 ID 내림차순으로 출력

[성능]
- 시간복잡도: O(N)

[배운 점]
- CASE문을 활용해 매핑하는 방식
- ORDER BY 절에서 컬럼 번호로 정렬 가능하지만, 가독성 면에서는 컬럼명을 사용하는 게 좋음
*/

SELECT 
    BOARD_ID, 
    WRITER_ID,
    TITLE, 
    PRICE, 
    CASE 
        WHEN STATUS = 'SALE' THEN '판매중'
        WHEN STATUS = 'RESERVED' THEN '예약중'
        WHEN STATUS = 'DONE' THEN '거래완료'
    END AS STATUS
FROM USED_GOODS_BOARD
WHERE CREATED_DATE = '2022-10-05'
ORDER BY 1 DESC;
