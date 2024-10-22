function solution(cacheSize, cities) {
  //cacheSize === 0 -> 모두 하드웨어에서 읽어와야 함
  if (cacheSize === 0) return cities.length * 5;

  let cache = [];
  let totalTime = 0;

  cities = cities.map((city) => city.toLowerCase());
  for (const city of cities) {
    //cache에 있는가?
    const idx = cache.indexOf(city);

    // 있으면 totalTime + 1, 다시 맨 뒤로 이동
    // 없으면
    // - cache가 다 찼나? -> 앞에 제고하고 맨 뒤에 추가
    // - cache가 다 안 찼으면 -> 뒤에 추가
    if (idx !== -1) {
      cache.splice(idx, 1);
      totalTime += 1;
    } else {
      if (cache.length >= cacheSize) {
        cache.shift();
      }
      totalTime += 5;
    }
    cache.push(city);
  }

  return totalTime;
}
