/*
 * 정수를 각각의 자릿수에 대해서 내림차순으로 재배열한 새로운 정수를 반환하시오.
 * 1. 배열 만들기 -> split();
 * 2. 문자열 바꾸기 -> 빈문자열('') 더하기
 * 3. 숫자로 바꾸기 -> 단항연산자 + 이용하기
 */
function solution(n) {
    var answer = 0;
    
    var n_str = n + '';
    var newStr = n_str.split('')
                    .sort()
                    .reverse()
                    .join('');
    return +newStr;
}
