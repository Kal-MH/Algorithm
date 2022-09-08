function solution(n, x) {
  return Array(n).fill(x).map((v, i) => (i + 1) * v);
}
