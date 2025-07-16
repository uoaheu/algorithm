/*
[문제] 없어진 기록 찾기 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/59042

[접근]
- ANIMAL_OUTS에는 있는데 ANIMAL_INS에는 없는 동물 -> 입양 기록만 존재하는 동물
- ANIMAL_OUTS를 기준으로 RIGHT JOIN하고, ANIMAL_INS의 DATETIME이 NULL인 경우만 필터링
- 입양은 됐지만 보호소에 들어온 기록이 없는 경우를 의미
- 정렬은 이름 기준, 이름이 NULL인 경우는 ANIMAL_ID 기준으로 정렬

[성능]
- 시간복잡도: O(N) ~ O(N log N) (JOIN 후 WHERE 조건 및 정렬 수행)

[배운 점]
- JOIN 이후 NULL 값 조건 필터링을 통해 누락된 데이터를 추적할 수 있음
- RIGHT JOIN을 통해 기준 테이블(ANIMAL_OUTS)의 모든 데이터를 유지하면서 비교 가능
- 누락된 데이터 추출 시 IS NULL 이용하기
*/

SELECT O.ANIMAL_ID,
       O.NAME
FROM ANIMAL_INS I 
RIGHT JOIN ANIMAL_OUTS O
ON I.ANIMAL_ID = O.ANIMAL_ID
WHERE I.DATETIME IS NULL
ORDER BY I.NAME, I.ANIMAL_ID ASC;
