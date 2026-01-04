/*
[문제] New Companies (Medium)
https://www.hackerrank.com/challenges/the-company/problem

[접근]
- 각 회사 테이블을 기준으로 하위 조직 테이블들(Lead, Senior, Manager, Employee) 결합
- 한 명의 상급자가 여러 명의 하급자를 가질 수 있는 1:N 관계이기 때문에 DISTINCT를 사용해서 중복된 코드 수를 제거하고 유니크한 인원수만 집계
- 모든 테이블이 공통으로 가지고 있는 company_code를 조인 키로 활용해 계층 간의 누락 없이 데이터 통합

[성능]
- 시간복잡도: O(N log N)

[배운 점]
- JOIN을 할 때 반드시 계층의 바로 윗 단계와 연결할 필요 없이 모든 테이블에 공통 컬럼이 있다면 이를 기준으로 직접 연결하는 것이 쿼리가 더 간결하고 안전할 수 있음
- LEFT JOIN을 사용하여 하위 직급이 없는 회사 정보도 누락되지 않도록 처리하는 것의 중요성
*/
SELECT c.company_code, 
    c.founder,
    COUNT(DISTINCT lm.lead_manager_code),
    COUNT(DISTINCT sm.senior_manager_code),
    COUNT(DISTINCT m.manager_code),
    COUNT(DISTINCT e.employee_code)
FROM Company c
LEFT JOIN Lead_Manager lm ON c.company_code = lm.company_code
LEFT JOIN Senior_Manager sm ON c.company_code = sm.company_code
LEFT JOIN Manager m ON c.company_code = m.company_code
LEFT JOIN Employee e ON c.company_code = e.company_code
GROUP BY c.company_code, c.founder
ORDER BY c.company_code;
