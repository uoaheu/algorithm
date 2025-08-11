/*
[문제] 베스트앨범 (Lv3)
https://school.programmers.co.kr/learn/courses/30/lessons/42579

[접근]
- 한 번의 순회로 두 가지 자료구조 생성
  1) totalByGenre: 장르별 총 재생 수 합계(Map)
  2) songsByGenre: 장르별 곡 목록 배열(Map, 원소: {plays, index})
- 장르를 총 재생 수 기준으로 내림차순 정렬
- 정렬된 장르 순서대로 각 장르의 곡 목록을 (재생수 내림차순, 동률이면 index 오름차순)으로 정렬해 상위 2개 index를 결과에 담기

[성능]
- 시간 복잡도: O(N log N) (N은 전체 곡 수)

[배운 점]
- "그룹 합계로 그룹 정렬 -> 그룹 내 정렬 후 상위 K 추출" 패턴은 Map으로 구현 가능
- 동률 처리 규칙(재생수 같으면 index 오름차순)을 정렬 비교 함수에 반영해야 함
*/

function solution(genres, plays) {
    // 1. 장르별 총합, 곡 리스트(plays, index) 누적
    const totalByGenre = new Map();   
    const songsByGenre = new Map();
    
    for (let i = 0; i < genres.length; i++) {
        const g = genres[i];
        const p = plays[i];

        totalByGenre.set(g, (totalByGenre.get(g) || 0) + p); // 장르, 누적 재생 수
        if (!songsByGenre.has(g)) songsByGenre.set(g, []); // 처음 보는 장르면 배열을 하나 만들고, 곡 데이터를 넣음
        songsByGenre.get(g).push({ plays: p, index: i }); // 장르별 곡 목록 누적
    }
    // console.log(totalByGenre);
    // console.log(songsByGenre);


    // 2. 장르를 총 재생 수 내림차순으로 정렬
    const sortedGenres = [...totalByGenre.entries()]
      .sort((a, b) => b[1] - a[1])   // [genre, total] 비교
      .map(([g]) => g);

    // 3. 각 장르에서 상위 2곡 (재생수 desc, 같은 경우 index asc)
    const answer = [];
    for (const g of sortedGenres) {
        const topSongs = songsByGenre.get(g)
          .sort((s1, s2) => {
              if (s2.plays !== s1.plays) return s2.plays - s1.plays;
              return s1.index - s2.index;
          })
        .slice(0, 2);

        for (const { index } of topSongs) {
          answer.push(index);
        }
    }

    return answer;
}
