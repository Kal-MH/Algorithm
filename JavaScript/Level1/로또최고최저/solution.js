function solution(lottos, win_nums) {
  const answer = [];
  const min = lottos.filter((n) => win_nums.includes(n)).length;
  const max = lottos.filter((n) => n === 0).length + min;

  max > 1 ? answer.push(7 - max) : answer.push(6);
  min > 1 ? answer.push(7 - min) : answer.push(6);

  return answer;
}

function solution(lottos, win_nums) {
  const answer = [];

  const min = lottos.filter((n) => win_nums.includes(n)).length;
  const max = lottos.filter((n) => !n).length + min;

  max > 1 ? answer.push(7 - max) : answer.push(6);
  min > 1 ? answer.push(7 - min) : answer.push(6);

  return answer;
}

function solution3(lottos, win_nums) {
  const zero_num = lottos.filter((lotto) => lotto === 0).length;
  const correct_num = lottos.filter((lotto) => win_nums.includes(lotto)).length;

  const lotto_rewards = [0, 6, 5, 4, 3, 2, 1, 0];

  return [lotto_rewards[correct_num + zero_num], lotto_rewards[correct_num]];
}

console.log(solution3([44, 1, 0, 0, 31, 25], [31, 10, 45, 1, 6, 19]));
