/*
[문제] SQL Project Planning (Medium)
https://www.hackerrank.com/challenges/sql-projects/problem

[접근]
- 여러 프로젝트들 중에서 연속된 날짜로 이어지는 구간(= 하나의 프로젝트 기간)을 찾아 각 구간의 시작일과 종료일 출력
- 전체 구간의 시작점과 전체 구간의 끝점을 각각 찾아 같은 순서끼리 매칭(주어진 날짜는 하루를 의미하기에 시작점은 Start_Date 중에서 다른 행의 End_Date로 등장하지 않는 날짜를 의미)
- 시작점과 끝점은 각 연속 구간마다 정확히 1개씩 생기므로 각각을 날짜 오름차순으로 정렬한 뒤 ROW_NUMBER()로 순번을 붙이고 rnk가 같은 시작점과 끝점을 JOIN

[성능]
- 시간복잡도: O(N log N)

[배운 점]
- 연속 구간(구간 병합) 문제는 구간 시작점과 구간 끝점을 분리해서 찾으면 깔끔
- 윈도우 함수 ROW_NUMBER()를 이용하면 서로 다른 결과 집합(시작점/끝점)을 정렬된 순서대로 1:1 매칭하는 조인 가능
*/

SELECT s.Start_Date, e.End_Date
FROM (
    SELECT Start_Date, ROW_NUMBER() OVER(ORDER BY Start_Date) AS rnk 
    FROM Projects
    WHERE Start_Date NOT IN (SELECT DISTINCT End_Date FROM Projects)
) s 
JOIN (
    SELECT End_Date, ROW_NUMBER() OVER(ORDER BY End_Date) AS rnk 
    FROM Projects
    WHERE End_Date NOT IN (SELECT DISTINCT Start_Date FROM Projects)
) e
ON s.rnk = e.rnk
ORDER BY DATEDIFF(e.End_Date, s.Start_Date) ASC
