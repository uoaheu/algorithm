/*
[문제] [3차] n진수 게임 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/17687

[접근]
- 0부터 시작해서 숫자를 n진수 문자열로 변환하며 이어붙이기
- 게임은 m명이 돌아가며 숫자를 말하고, 그 중 p번째 순서인 내가 말하는 문자만 모아야 함
- 말하게 될 인덱스는 (p-1), (p-1)+m, (p-1)+2m, ... 이고, t개가 필요하므로 마지막으로 필요한 인덱스는 (p-1) + (t-1) * m
- 확보된 문자열에서 위 인덱스들만 뽑아서 대문자로 반환 

[성능]
- 시간 복잡도: O(L)

[배운 점]
- String으로 반복문에서 누적 덧붙이면 비효율적이므로 StringBuilder를 사용해야 함
- 진수 변환은 Integer.toString(value, base)로 바로 처리 가능
- 필요한 문자 개수(t개)를 기준으로 필요한 만큼만 생성하면 불필요하게 긴 문자열을 만들지 않아도 됨
*/

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;

        // 내가 말해야 하는 마지막 인덱스까지 커버되도록 생성
        int needLen = (p - 1) + (t - 1) * m + 1;

        while (sb.length() < needLen) {
            sb.append(Integer.toString(idx, n));
            idx++;
        }

        char[] str = sb.toString().toCharArray();
        StringBuilder newsb = new StringBuilder();

        // 내 차례 문자만 t개 뽑기
        for (int k = 0; k < t; k++) {
            int pick = (p - 1) + k * m;
            newsb.append(Character.toUpperCase(str[pick]));
        }

        return newsb.toString();
    }
}
