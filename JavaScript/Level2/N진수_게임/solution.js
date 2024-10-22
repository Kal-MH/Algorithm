function solution(n, t, m, p) {
  let answer = '';
  let allNumbers = '';
  let num = 0;

  while (allNumbers.length < t * m) {
    allNumbers += num.toString(n).toUpperCase();
    num++;
  }

  for (let i = p - 1; i < t * m; i += m) {
    answer += allNumbers[i];
  }

  return answer;
}
