/*
[문제] 특정 조건을 만족하는 물고기별 수와 최대 길이 구하기 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/298519

[접근]
- FISH_INFO 테이블에서 물고기 종류별(FISH_TYPE)로 그룹화 -> 해당 종류의 물고기 수(COUNT(ID)) & 가장 큰 길이(MAX(LENGTH))를 구함
- LENGTH 값이 NULL인 경우 10으로 간주하도록 CASE WHEN으로 치환
- 평균 길이(AVG(LENGTH))가 33 이상인 물고기 종류만 필터링(HAVING 절)
- 최종 결과를 FISH_TYPE 오름차순으로 정렬

[성능]
- 시간복잡도: O(N) -> N : FISH_INFO 레코드 수

[배운 점]
- CASE WHEN을 통해 NULL 값을 집계 전에 원하는 값으로 변환 가능
- HAVING 절은 GROUP BY 이후 집계 결과에 조건을 적용할 때 사용
- 여러 집계 함수(COUNT, MAX, AVG)를 동시에 활용 가능
*/

SELECT COUNT(ID) AS FISH_COUNT, MAX(LENGTH) AS MAX_LENGTH, FISH_TYPE
FROM (
    SELECT ID, 
        FISH_TYPE, 
        (CASE WHEN LENGTH IS NULL THEN 10 ELSE LENGTH END) AS LENGTH 
    FROM FISH_INFO
) NEW
GROUP BY FISH_TYPE
HAVING AVG(LENGTH) >= 33
ORDER BY FISH_TYPE ASC;
