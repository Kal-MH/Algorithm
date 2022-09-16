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
