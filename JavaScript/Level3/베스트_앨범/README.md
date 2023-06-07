### **문제 설명**

스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.

1. 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
2. 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.

노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.

### 제한사항

- genres[i]는 고유번호가 i인 노래의 장르입니다.
- plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
- genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
- 장르 종류는 100개 미만입니다.
- 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
- 모든 장르는 재생된 횟수가 다릅니다.

### 입출력 예

| genres | plays | return |
| --- | --- | --- |
| ["classic", "pop", "classic", "classic", "pop"] | [500, 600, 150, 800, 2500] | [4, 1, 3, 0] |

### 입출력 예 설명

classic 장르는 1,450회 재생되었으며, classic 노래는 다음과 같습니다.

- 고유 번호 3: 800회 재생
- 고유 번호 0: 500회 재생
- 고유 번호 2: 150회 재생

pop 장르는 3,100회 재생되었으며, pop 노래는 다음과 같습니다.

- 고유 번호 4: 2,500회 재생
- 고유 번호 1: 600회 재생

따라서 pop 장르의 [4, 1]번 노래를 먼저, classic 장르의 [3, 0]번 노래를 그다음에 수록합니다.

- 장르 별로 가장 많이 재생된 노래를 최대 두 개까지 모아 베스트 앨범을 출시하므로 2번 노래는 수록되지 않습니다.

### 문제 풀이

```tsx
function solution(genres, plays) {
    const map = new Map();
    
    //init
    const genreSet = Array.from(new Set(genres));
    for(const genre of genreSet) {
        map.set(genre, {total: 0, songs: []})
    }
    for(let i = 0; i < genres.length; i++) {
        const genre = genres[i];
        const playCnt = plays[i];
        
        const {total, songs} = map.get(genre);
        map.set(genre, {total: total + playCnt, songs: [...songs, {plays: playCnt, index: i}]})
    }
    
    //sorting
    const values = Array.from(map.values());
    
    values.sort((a, b) => b.total - a.total);
    for(const {_, songs} of values) {
        if (songs.length === 1) continue;
        
        songs.sort((a, b) => {
            if (a.plays === b.plays) return a.index - b.index;
            return b.plays - a.plays
        })
    }
     
    //get answer
    const answer = [];
    
    let order = 0;
    for(const {_, songs} of values) {
        answer[order++] = songs[0].index;
        if (songs.length >= 2) answer[order++] = songs[1].index;
    }
    
    return answer;
    
}
```

1. 해시맵 초기화
2. 원소들을 
    1. total 많은 순대로
    2. songs의 경우, plays는 내림차순, index는 오름차순으로 정렬
        - 장르 100, songs가 10000이라고 해도, 100*10000*log(10000)을 넘지 않는다. (약 백만 * 13)
3. 출력
    1. answer[i] = index;

[출처 - 프로그래머스]
