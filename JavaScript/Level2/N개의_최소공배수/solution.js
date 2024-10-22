function greatestCommonDivisor(a, b) {
  while (b > 0) {
    let r = a % b;
    a = b;
    b = r;
  }

  return a;
}

function leastCommonMultiple(a, b) {
  return (a * b) / greatestCommonDivisor(a, b);
}

function solution(arr) {
  var answer = 0;

  answer = arr.reduce((acc, cur) => leastCommonMultiple(acc, cur), 1);

  return answer;
}

console.log(solution([2, 6, 8, 14]));
