const log = console.log;
/** *
 * 평가 * - 코드가 계산(Evaluation) 되어 값을 만드는 것
 * * ex)
 * * 1 => 1로평가
 * 1 + 2 => 3으로 평가
 * [1, 2] => 배열로 평가
 * [1, 2 + 4] => 2 + 4가 6으로 평가가 된 후, [1, 6]로 평가
 *
 * 일급
 * - 값으로 다룰 수 있다.
 * - 변수에 담을 수있다.
 * - 함수의 인자로 사용할 수 있다.
 * - 함수의 결과로 사용할 수 있다.
 */

const a = 10;
const add10 = (a) => a + 10;
const r = add10(a);
log(r);

/** 일급 함수
 *  - 함수를 값으로 다룰 수 있다.
 *  - 조합성과 추상화의 도구
 * */

const add5 = (a) => a + 5;
log(add5); //함수 자체를 인자로 전달
log(add5(5)); // 함수 결과를 평가해서 다른 함수에 전달

const f1 = () => () => 1; // 실행되었을 때, 함수를 반환
log(f1()); // 함수를 반환

const f2 = f1(); //함수를 변수에 저장

log(f2);
log(f2());
