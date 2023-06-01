# 전력망을 둘로 나누기

### **문제 설명**

n개의 송전탑이 전선을 통해 하나의 [트리](https://en.wikipedia.org/wiki/Tree_(data_structure)) 형태로 연결되어 있습니다. 당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 합니다. 이때, **두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.**

송전탑의 개수 n, 그리고 전선 정보 wires가 매개변수로 주어집니다. 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때, 두 전력망이 가지고 있는 **송전탑 개수의 차이(절대값)를 return** 하도록 solution 함수를 완성해주세요.

---

### 제한사항

- n은 2 이상 100 이하인 자연수입니다.
- wires는 길이가 `n-1`인 정수형 2차원 배열입니다.
    - wires의 각 원소는 [v1, v2] 2개의 자연수로 이루어져 있으며, 이는 전력망의 v1번 송전탑과 v2번 송전탑이 전선으로 연결되어 있다는 것을 의미합니다.
    - 1 ≤ v1 < v2 ≤ n 입니다.
    - 전력망 네트워크가 하나의 트리 형태가 아닌 경우는 입력으로 주어지지 않습니다.

---

### 입출력 예

| n | wires | result |
| --- | --- | --- |
| 9 | [[1,3],[2,3],[3,4],[4,5],[4,6],[4,7],[7,8],[7,9]] | 3 |
| 4 | [[1,2],[2,3],[3,4]] | 0 |
| 7 | [[1,2],[2,7],[3,7],[3,4],[4,5],[6,7]] | 1 |

---

### 문제풀이

1. 트리를 만들자
2. n은 최대 100개이므로 wires는 최대 100*100가 된다. for문으로 한번 순회가 가능
3. 각각을 끊었다 보고, 트리 순회를 해서 개수를 count
4. **두 개의 집단에서 각각 구해야 하므로 root를 번갈아 가면서 지정해주자.**

```tsx
function solution(n, wires) {
    //init tree
    const map = Array.from(Array(n + 1), () => []);
    
    for(const [v1, v2] of wires) {
        map[v1].push(v2);
        map[v2].push(v1);
    }
    
    //BFS
    function BFS(root, except) {
        let count = 0;
        let queue = [];
        let visit = [];
        
        queue.push(root);
        visit[root] = true;
        
        while (queue.length) {
            const v = queue.pop();
            
            for(const next of map[v]) {
                if (visit[next]) continue;
                if (next === except) continue;
                
                count++;
                visit[next] = true;
                queue.push(next);
            }
        }
        return count;
    }
    
    let min = Number.MAX_SAFE_INTEGER;
    for(const [v1, v2] of wires) {
        **const count1 = BFS(v1, v2);
        const count2 = BFS(v2, v1);**
        min = Math.min(min, Math.abs(count1 - count2))
    }
    
    return min;
}
```

[출처 - 프로그래머스]
