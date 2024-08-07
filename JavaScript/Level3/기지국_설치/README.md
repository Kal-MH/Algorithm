N개의 아파트가 일렬로 쭉 늘어서 있습니다. 이 중에서 일부 아파트 옥상에는 4g 기지국이 설치되어 있습니다. 기술이 발전해 5g 수요가 높아져 4g 기지국을 5g 기지국으로 바꾸려 합니다. 그런데 5g 기지국은 4g 기지국보다 전달 범위가 좁아, 4g 기지국을 5g 기지국으로 바꾸면 어떤 아파트에는 전파가 도달하지 않습니다.

예를 들어 11개의 아파트가 쭉 늘어서 있고, [4, 11] 번째 아파트 옥상에는 4g 기지국이 설치되어 있습니다. 만약 이 4g 기지국이 전파 도달 거리가 1인 5g 기지국으로 바뀔 경우 모든 아파트에 전파를 전달할 수 없습니다. (전파의 도달 거리가 W일 땐, 기지국이 설치된 아파트를 기준으로 전파를 양쪽으로 W만큼 전달할 수 있습니다.)

- 초기에, 1, 2, 6, 7, 8, 9번째 아파트에는 전파가 전달되지 않습니다.
- 1, 7, 9번째 아파트 옥상에 기지국을 설치할 경우, 모든 아파트에 전파를 전달할 수 있습니다.
- 더 많은 아파트 옥상에 기지국을 설치하면 모든 아파트에 전파를 전달할 수 있습니다.

이때, 우리는 5g 기지국을 **최소로 설치**하면서 모든 아파트에 전파를 전달하려고 합니다. 위의 예시에선 최소 3개의 아파트 옥상에 기지국을 설치해야 모든 아파트에 전파를 전달할 수 있습니다.

아파트의 개수 N, 현재 기지국이 설치된 아파트의 번호가 담긴 1차원 배열 stations, 전파의 도달 거리 W가 매개변수로 주어질 때, 모든 아파트에 전파를 전달하기 위해 증설해야 할 기지국 개수의 최솟값을 리턴하는 solution 함수를 완성해주세요

### 제한사항

- N: 200,000,000 이하의 자연수
- stations의 크기: 10,000 이하의 자연수
- stations는 오름차순으로 정렬되어 있고, 배열에 담긴 수는 N보다 같거나 작은 자연수입니다.
- W: 10,000 이하의 자연수

---

### 입출력 예

| N | stations | W | answer |
| --- | --- | --- | --- |
| 11 | [4, 11] | 1 | 3 |
| 16 | [9] | 2 | 3 |

### 문제 풀이

```jsx
let answer = 0;
    let begin = 1;
    
    function isPossible(begin, end) {
        let res = parseInt((end - begin + 1)/ (2 * w + 1));
        if ((end - begin + 1) % (2 * w + 1) > 0) res++;
        return res;
    }
    
    for(let i = 0; i < stations.length; i++) {
        if (begin < stations[i] - w) {
            answer += isPossible(begin, stations[i] - w - 1);
        }
        begin = stations[i] + w + 1;
    }
    
    if (stations[stations.length - 1] + w < n) {
        answer += isPossible(stations[stations.length - 1] + w + 1, n);
    }
    
    return answer;
```

[출처 - 프로그래머스]
