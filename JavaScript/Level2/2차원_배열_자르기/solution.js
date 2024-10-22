function solution(n, left, right) {
  const result = Array.from({ length: n * n }).fill(0);

  for (let k = 0; k < n * n; k++) {
    const i = Math.floor(k / n) + 1;
    const j = (k % n) + 1;

    result[k] = Math.max(i, j);
  }

  return result.slice(left, right + 1);
}

console.log(solution(3, 2, 5));
