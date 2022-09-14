function solution(arr, divisor) {
	var answer = arr.filter(v => v % divisor === 0);
	return answer.length === 0 ? [-1] : answer.sort((a, b) => a - b);
}

function solution(arr, divisor) {
	var answer = [];
	arr.map((o) =>
		o % divisor === 0 && answer.push(o);
	}
	return answer.length ? answer.sort((a, b) => a - b) : [-1];
}

/* 이진변환 반복하기 */
function solution(s) {
	var answer = [0, 0];
	while (s.length > 1) {
		answer[0]++;
		answer[1] += (s.match(/0/g) || []).length;
		s = s.replace(/0/g, '').toString(2);
	}
	return answer;
}

/* 최솟값 만들기 */
function solution(A, B) {
	A.sort((a, b) => a - b);
	B.sort((a, b) => b - a);
	return A.reduce((total, val, idx) => total + val * B[idx], 0);
}
