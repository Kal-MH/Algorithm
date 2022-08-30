function solution(sizes) {
  let w = 0;
  let h = 0;
  
  sizes.forEach(s => {
    const [a, b] = s.sort((a, b) => a - b);
    if (a > w) w = a;
    if (b > h) h = b;
  });
  
  return w * h;
}
