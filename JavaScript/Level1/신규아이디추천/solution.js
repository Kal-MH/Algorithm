function solution(new_id) {
  const answer = new_id
    .toLowerCase()
    .replace(/[^\w-.]/g, '')
    .replace(/\.+/g, '.')
    .replace(/^\.|\.$/g, '')
    .replace(/^$/g, 'a')
    .slice(0, 15).replace(/\.$/g, '');
  
  const len = answer.length;
  return len > 2 ? answer : answer + answer.charAt(len - 1).repeat(3 - len);
}

function solution(new_id) {
  const answer = new_id
    .toLowerCase()
    .replace(/[^\w-.]/g, '')
    .replace(/\.+/g, '.')
    .replace(/^\.|\.$/g, '')
    .padEnd(1, 'a')
    .slice(0, 15).replace(/\.$/g, '');
  
  return answer.padEnd(3, id[id.length - 1]);
}
