/*
[문제] [1차] 뉴스 클러스터링 (Lv2)
https://school.programmers.co.kr/learn/courses/30/lessons/17677

[접근]
- 문자열을 두 글자씩 끊어 다중집합 생성
- 두 문자열이 영문자 쌍만 유효한 요소로 인정
- 이미 존재하는 쌍이면 count++, 없으면 새로 추가
- 이후 교집합과 합집합을 계산해서 자카드 유사도 산출
- 문제 조건에 따라 결과값을 65536을 곱하고, 정수로 내림 처리

[성능]
- 시간 복잡도: O(N^2)

[배운 점]
- 다중집합을 List로 구현할 때 중복 요소의 count를 직접 관리해야 함
- Character.isLetter()는 한글도 포함, a ~ z 범위 비교로 영문자만 판별하는 것이 안전
- 문자열 비교와 누적 로직을 명확히 분리하면 코드의 가독성과 유지보수성이 높아짐
*/

import java.util.*;
class Solution {
    class Cnt {
        String str;
        Integer count;
        Cnt(String str, Integer count) {
            this.str = str;
            this.count = count;
        }
    }
    public int solution(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        // 1) str1을 2글자씩 잘라 A 멀티셋(listA)에 누적
        List<Cnt> listA = new ArrayList<>();
        
        for(int i = 0; i < len1 - 1; i++) {
            char a = Character.toLowerCase(str1.charAt(i));
            char b = Character.toLowerCase(str1.charAt(i + 1));
            
            // 영문자 쌍만 인정
            if (!(a >= 'a' && a <= 'z' && b >= 'a' && b <= 'z')) continue;

            String pair = "" + a + b;

            // listA에 이미 있으면 count++, 없으면 추가
            int idx = -1;
            for (int k = 0; k < listA.size(); k++) {
                if (listA.get(k).str.equals(pair)) {
                    idx = k; 
                    break;
                }
            }
            
            if (idx == -1) {
                listA.add(new Cnt(pair, 1));
            } else {
                listA.get(idx).count++;
            }
        }
        
        // 2) str2도 동일하게 처리해 B 멀티셋(listB) 구성
        List<Cnt> listB = new ArrayList<>();
        for (int i = 0; i < str2.length() - 1; i++) {
            char a = Character.toLowerCase(str2.charAt(i));
            char b = Character.toLowerCase(str2.charAt(i + 1));
            if (!(a >= 'a' && a <= 'z' && b >= 'a' && b <= 'z')) continue;

            String pair = "" + a + b;

            int idx = -1;
            for (int k = 0; k < listB.size(); k++) {
                if (listB.get(k).str.equals(pair)) {
                    idx = k; break;
                }
            }
            if (idx == -1) listB.add(new Cnt(pair, 1));
            else listB.get(idx).count++;
        }
        
        // 3) 교집합/합집합 계산
        Set<String> keys = new HashSet<>();
        for (Cnt c : listA) keys.add(c.str);
        for (Cnt c : listB) keys.add(c.str);

        int inter = 0;
        int union = 0;
        for (String k : keys) {
            int aCnt = 0;
            int bCnt = 0;

            for (Cnt c : listA) { 
                if (c.str.equals(k)) { 
                    aCnt = c.count; 
                    break; 
                } 
            }
            for (Cnt c : listB) { 
                if (c.str.equals(k)) { 
                    bCnt = c.count; 
                    break; 
                } 
            }

            inter += Math.min(aCnt, bCnt);
            union += Math.max(aCnt, bCnt);
        }

        if (union == 0) return 65536; // 둘 다 공집합
        return (int) Math.floor((inter * 1.0 / union) * 65536);
    }
}
