/*
[문제] 조건에 맞는 도서와 저자 리스트 출력하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/144854

[접근]
- BOOK과 AUTHOR를 AUTHOR_ID로 내부조인하여 제목 대신 저자명까지 함께 조회
- 도서 카테고리에서 경제만 필터링하고, 출간일 오름차순 정렬
- 출간일은 DATE_FORMAT으로 'YYYY-MM-DD' 형태로 표시

[성능]
- 시간복잡도: O(|B| + |A| + r log r)

[배운 점]
- ORDER BY에서 컬럼 인덱스3 대신 컬럼명 사용이 안전함
*/

SELECT B.BOOK_ID, A.AUTHOR_NAME, DATE_FORMAT(B.PUBLISHED_DATE, "%Y-%m-%d") 
FROM BOOK B JOIN AUTHOR A ON B.AUTHOR_ID = A.AUTHOR_ID
WHERE CATEGORY = '경제'
ORDER BY 3 ASC
