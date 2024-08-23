//DP
// dp[n] = dp[n - 1] + dp[n - 2];

function solution(n) {
  const answer = [0, 1, 2, ...Array(n).fill(0)];

  answer.forEach((_, index) => {
    if (index > 2)
      answer[index] = (answer[index - 1] + answer[index - 2]) % 1234567;
  });
}

console.log(solution(4));
console.log(solution(3));
