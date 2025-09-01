/*
[문제] DATETIME에서 DATE로 형 변환 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/59414

[접근]
- ANIMAL_INS 테이블 내 각 동물의 아이디와 이름, 들어온 날짜 조회
- 날짜는 년-월-일로 변환
- 아이디 순으로 정렬

[성능]
- 시간복잡도: O(N log N)

[배운 점]
- DATE_FORMAT 사용법 익히기
*/

SELECT ANIMAL_ID, NAME, DATE_FORMAT(DATETIME, '%Y-%m-%d') AS 날짜 
FROM ANIMAL_INS
ORDER BY ANIMAL_ID
