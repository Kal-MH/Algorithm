//JadenCase
String.prototype.replaceAt = function (index, replacement) {
  if (index >= this.length || index < 0) return this.valueOf();
  
  return this.substring(0, index) + replacement + this.substring(index + 1);
}

String prototype.replaceAt2 = function (index, replacement) {
  if (index >= this.length || index < 0) return this.valueOf();
  
  let chars = this.split('');
  chars[index] = replacement;
  return chars.join('');
}

function solution(s) {
  return s.split(" ").map(v => v.charAt(0).toUpperCase() + v.substring(1).toLowerCase()).join(" ");
}
