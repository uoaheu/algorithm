/*
[문제] 연도별 대장균 크기의 편차 구하기 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/299310

[접근]
- ECOLI_DATA 테이블에서 연도별로 최대 대장균 크기를 구한 서브쿼리를 생성
- 각 개체별로 연도, 크기, ID를 가져와 위 서브쿼리와 연도 기준으로 조인
- 각 개체의 편차(YEAR_DEV)는 연도별 최대 크기 - 해당 개체 크기로 계산
- 연도 오름차순, 편차 오름차순으로 정렬

[성능]
- 시간복잡도: O(N log N) 수준 (GROUP BY, JOIN, ORDER BY)
- 공간복잡도: O(N) (중간 테이블 2개 생성)

[배운 점]
- 날짜에서 연도만 추출할 때는 DATE_FORMAT를 사용했더니 계속 답이 틀리다고 나와서 YEAR()로 대체 -> 각 함수의 타입이 다르다는 점을 배웠음
- DATE_FORMAT의 경우 문자열, YEAR()의 경우 정수 타입
*/

SELECT 
    d.YEAR, 
    (m.MAX_SIZE - d.SIZE_OF_COLONY) AS YEAR_DEV, 
    d.ID
FROM (
    SELECT 
        YEAR(DIFFERENTIATION_DATE) AS YEAR, 
        ID, 
        SIZE_OF_COLONY 
    FROM ECOLI_DATA
) AS d
JOIN (
    SELECT 
        YEAR(DIFFERENTIATION_DATE) AS YEAR, 
        MAX(SIZE_OF_COLONY) AS MAX_SIZE 
    FROM ECOLI_DATA 
    GROUP BY YEAR
) AS m
ON d.YEAR = m.YEAR
ORDER BY d.YEAR ASC, YEAR_DEV ASC;
