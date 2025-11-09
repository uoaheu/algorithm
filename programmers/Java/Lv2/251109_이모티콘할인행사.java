/*
[문제] 이모티콘 할인행사 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/150368

[접근]
- 각 이모티콘마다 할인율(10, 20, 30, 40%)을 설정해야 하므로 가능한 모든 조합을 완전탐색으로 생성
- 각 조합에 대해 모든 유저의 구매 금액을 계산하고, 기준 금액 이상이면 플러스 가입자 수 + 1, 미만이면 총 매출액에 합산
- 모든 조합을 비교해 가입자 수를 최대로 가입자 수가 같으면 매출액을 최대로 하는 값 구함

[성능]
- 시간 복잡도: O(4^N × M)

[배운 점]
- 완전탐색에서도 N의 크기를 확인하고 가능한 연산량 추정하기
- 할인율에 따라 결과가 일정하게 변하지 않기 때문에 이분탐색이 아닌 완전탐색으로 접근해야 함
*/

class Solution {
    static int[][] user;
    static int[] emoticon;
    static int[] discounts = {10, 20, 30, 40}; // 가능한 할인율
    static int maxMember = 0; // 최대 가입자 수
    static int maxSales = 0; // 최대 매출액
    
    public int[] solution(int[][] users, int[] emoticons) {
        user = users;
        emoticon = emoticons;
        
        dfs(0, new int[emoticon.length]); // 모든 할인율 조합 탐색 시작
        
        return new int[]{maxMember, maxSales};
    }
    
    static void dfs(int depth, int[] selected) {
        // 모든 이모티콘의 할인율이 정해졌으면 시뮬레이션 수행
        if(depth == emoticon.length) {
            simulate(selected);
            return;
        }
        
        // 각 이모티콘에 대해 10,20,30,40 중 하나를 선택
        for(int d : discounts) {
            selected[depth] = d;
            dfs(depth + 1, selected);
        }
    }
    
    // 주어진 할인율 조합으로 유저들의 구매 결과 계산
    static void simulate(int[] selectedDiscounts) {
        int member = 0; // 현재 조합에서 가입자 수
        int sales = 0; // 현재 조합에서 매출액
        
        // 모든 유저에 대해 반복
        for (int[] u : user) {
            int minDiscount = u[0]; // 유저가 구매할 최소 할인율
            int purchase = u[1]; // 이 금액 이상이면 가입
            int total = 0; // 해당 유저의 총 구매 금액

            // 각 이모티콘 구매 여부 계산
            for (int i = 0; i < emoticon.length; i++) {
                if (selectedDiscounts[i] >= minDiscount) {
                    total += emoticon[i] * (100 - selectedDiscounts[i]) / 100;
                }
            }

            if (total >= purchase) {
                member++;
            } else {
                sales += total;
            }
        }

        if (member > maxMember) {
            maxMember = member;
            maxSales = sales;
        } else if (member == maxMember && sales > maxSales) {
            maxSales = sales;
        }
    }
}
