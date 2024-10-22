/*
출력: 주차요금 정수 배열 반환
    - 차량 번호 적은 순서부터
    - fees <= 100,000 (int)
    - records 길이 <= 1,000 
*/
function solution(fees, records) {
  //입차 시간, 주차 시간에 대한 Object 활용
  const [basicTime, basicFee, unitTime, unitFee] = fees;
  const carRecords = {};
  const parkingTimes = {};

  //시:분 => 분으로 바꾸기
  const timeToMinutes = (time) => {
    const [hour, minutes] = time.split(':').map(Number);
    return hour * 60 + minutes;
  };
  //주차 요금 계산하기
  const calculateParkingFee = (time) => {
    if (time <= basicTime) return basicFee;
    return basicFee + Math.ceil((time - basicTime) / unitTime) * unitFee;
  };

  //입차 -> 입차시간부터 23:59까지로 주차시간 계산해서 넣기
  //출차 -> 현재 주차시간에서 출차시간부터 23:59까지의 시간 빼기
  const endOfDay = timeToMinutes('23:59');
  for (const record of records) {
    const [time, car, action] = record.split(' ');
    const minutes = endOfDay - timeToMinutes(time);

    if (!parkingTimes[car]) parkingTimes[car] = 0;
    if (action === 'IN') {
      parkingTimes[car] += minutes;
    } else {
      parkingTimes[car] -= minutes;
    }
  }

  //차량 번호 적은 순서로 정렬 후, 주차 요금 계산하기
  return Object.keys(parkingTimes)
    .sort()
    .map((car) => calculateParkingFee(parkingTimes[car]));
}

console.log(
  solution(
    [180, 5000, 10, 600],
    [
      '05:34 5961 IN',
      '06:00 0000 IN',
      '06:34 0000 OUT',
      '07:59 5961 OUT',
      '07:59 0148 IN',
      '18:59 0000 IN',
      '19:09 0148 OUT',
      '22:59 5961 IN',
      '23:00 5961 OUT',
    ],
  ),
);
