/*
 * 문자열 내 p와 y의 개수를 구하여라.
 * 1. p와 y의 대소문자 구분은 하지 않음.
 * 2. 개수가 같거나, 둘 다 0이면 true, 다르면 false.
 */

function numPY(s){
  //함수를 완성하세요
    return s.toUpperCase().split("P").length === s.toUpperCase().split("Y").length;
}
