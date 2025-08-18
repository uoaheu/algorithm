/*
[문제] 오랜 기간 보호한 동물(2) (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/59411

[접근]
- ANIMAL_INS(보호소 들어온 기록)와 ANIMAL_OUTS(보호소 나간 기록)를 INNER JOIN
- 가장 오래 보호된 상위 2마리의 ID와 이름을 출력
- 보호기간 계산에는 DATEDIFF 함수 사용
- 내림차순 정렬 후 LIMIT 2로 상위 두 마리만 출력

[성능]
- 시간복잡도: O(N log N)

[배운 점]
- DATEDIFF를 사용하면 날짜 차이를 계산 가능
- ORDER BY와 LIMIT을 활용해 상위 K개 데이터를 효율적으로 추출 가능
*/

SELECT O.ANIMAL_ID, O.NAME
FROM ANIMAL_INS I INNER JOIN ANIMAL_OUTS O ON I.ANIMAL_ID = O.ANIMAL_ID
ORDER BY DATEDIFF(O.DATETIME, I.DATETIME) DESC
LIMIT 2;
