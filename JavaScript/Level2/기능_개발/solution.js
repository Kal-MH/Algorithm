//또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.

//1. 각각 남은 날을 구한다.
//2. 남은 날이 더 많은 날짜가 앞에 있다면 끝날 때까지, 그 보다 작은 뒤에 날짜만큼 + 1해준다.

function solution(progresses, speeds) {
  let answer = [];

  const remainDays = progresses.map((prog, index) =>
    Math.ceil((100 - prog) / speeds[index]),
  );

  let maxDays = remainDays[0];
  answer.push(0);

  for (const rday of remainDays) {
    if (maxDays >= rday) {
      answer[answer.length - 1]++;
    } else {
      answer.push(1);
      maxDays = rday;
    }
  }

  return answer;
}

console.log(solution([93, 30, 55], [1, 30, 5])); //[2, 1]
