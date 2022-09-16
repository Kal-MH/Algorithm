// n다음 가장 큰 수
function solution(n) {
  var size = n.toString(2).match(/1/g).length;
  while (n++) {
    if (size === n.toString(2).match(/1/g).length) return n;
  }
}

function solution(n, a=n+1) {
  return n.toString(2).match(/1/g).length === a.toString(2).match(/1/g).length ? a : solution(n, a + 1);
}

/*
 * -------------------------------------------------------------------
 * boj.1021 회전하는 큐
 * 1. 두 가지 연산 : 왼쪽으로 한 칸 이동, 오른쪽으로 한 칸 이동
 * 2. 뽑아내려는 위치의 원소를 얻기 위해 두 가지 연산을 수행하는 최솟값 출력 */
const input = require("fs").readFileSync(0, "utf-8").trim().split("\n");

const [N] = input[0].split(' ').map(Number);
const nums = input[1].split(' ').map(Number);

const dq = Array(3 * N + 5).fill(0);
dq.head = N;
dq.tail = N;

const findIndex = (dq, cb) => {
    for(let i = dq.head; i < dq.tail; i++){
        if (cb(dq[i])) return i;
    }
};

//왼쪽으로 이동하는 연산 : popFront + pushBack
const popFront = (dq) => dq[dq.head++];
const pushBack = (dq, el) => (dq[dq.tail++] = el);

//오른쪽으로 이동하는 연산 : popBack + pushFront
const popBack = (dq) => dq[--dq.tail];
const pushFront = (dq, el) => (dq[--dq.head] = el);

//중심을 기준으로 왼쪽에 원소가 있는가, 오른쪽에 원소가 있는가
const dir = (dq, i) =>
    i - dq.head <= dq.tail - i
    ? [pushBack, popFront, i - dq.head]
    : [pushFront, popBack, dq.tail - i];

Array.fron({length:N}).forEach((_, i) => pushBack(dq, i + 1));

let count = 0;
nums.forEach((target) => {
    const i = findIndex(dq, (t) => t === target);

    const [push, pop, times] = dir(dq, i);

    count += times;
    for(let j = 0; j < times; j++) push(dq, pop(dq));

    popFront(dq);
})


/*
 * -------------------------------------------------------------------
 * boj.1074 Z
 * 1. Z모양으로 2^n크기의 격자를 이동한다.
 * 2. 예를 들어) [3, 1]위치에는 몇번째로 도달하게 되는가*/
const [N, R, C] = require('fs')
  .readFileSync(0, "utf-8")
  .toString()
  .split(" ")
  .map((str) => Number(str));

const answer = (function rec(n, r, c) {
    if (n === 0) return 0;

    const half = Math.pow(2, n - 1);

    //r, c의 위치에 따라 격자 크기를 2^n짜리에서 2^(n- 1)로 줄인다. 전체를 4등분할 때,
  
    //상단 왼쪽
    if (r < half && c < half) return rec(n - 1, r, c);
    //상단 오른쪽
    if (r < half && c >= half) return half**2 + rec(n - 1, r, c - half);
    //하단 왼쪽
    if (r >= half && c < half) return 2 * half**2 + rec(n - 1, r - half, c);
    //하단 오른쪽
    if (r >= half && c >= half) return 3 * half**2 + rec(n - 1, r - half, c - half);
})(N, R, C);

console.log(answer);
