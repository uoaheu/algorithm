/*
[문제] 이름에 el이 들어가는 동물 찾기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/59047

[접근]
- ANIMAL_INS 테이블에서 이름에 'el'이 들어간 개 조회
- 문자열 포함 여부는 LIKE 연산자와 % 사용
- NAME 기준 오름차순 정렬

[성능]
- 시간복잡도: O(N)

[배운 점]
- 문자열 검색 시 LIKE와 와일드카드를 활용하는 방법
*/

SELECT ANIMAL_ID, NAME FROM ANIMAL_INS
WHERE NAME LIKE "%el%" AND ANIMAL_TYPE = 'Dog'
ORDER BY NAME;
