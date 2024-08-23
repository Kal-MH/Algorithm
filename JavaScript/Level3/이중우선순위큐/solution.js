const TYPE = {
  MAX: 1,
  MIN: -1,
};

class MinHeap {
  constructor() {
    this.heap = [null];
  }

  isEmpty() {
    return this.heap.length === 1;
  }

  swap(a, b) {
    [this.heap[a], this.heap[b]] = [this.heap[b], this.heap[a]];
  }

  push(value) {
    this.heap.push(value);

    let curIdx = this.heap.length - 1;
    let parentIdx = Math.floor(curIdx / 2);

    while (parentIdx !== 0 && this.heap[curIdx] < this.heap[parentIdx]) {
      this.swap(curIdx, parentIdx);
      curIdx = parentIdx;
      parentIdx = Math.floor(curIdx / 2);
    }
  }

  pop(deleteTarget) {
    if (this.isEmpty()) return;
    if (this.heap.length === 2) return this.heap.pop();

    if (deleteTarget === TYPE.MAX) {
      const parentIdx = Math.floor((this.heap.length - 1) / 2);
      const leafs = this.heap.slice(parentIdx);

      const max = Math.max(...leafs);

      this.swap(this.heap.indexOf(max), this.heap.length - 1);

      return this.heap.pop();
    }

    const returnValue = this.heap[1];
    this.heap[1] = this.heap.pop();

    let curIdx = 1,
      leftIdx,
      rightIdx;

    while (true) {
      leftIdx = curIdx * 2;
      rightIdx = curIdx * 2 + 1;

      if (leftIdx >= this.heap.length) break;

      let minIdx = leftIdx;
      if (rightIdx < this.heap.length && this.heap[right] < this.heap[minIdx]) {
        minIdx = rightIdx;
      }

      if (this.heap[curIdx] > this.heap[minIdx]) {
        this.swap(curIdx, minIdx);
        curIdx = minIdx;
      } else break;
    }

    return returnValue;
  }

  getHeap() {
    const min = this.pop(-1);
    const max = this.pop(1);

    return [!max ? 0 : max, !min ? 0 : min];
  }
}

function solution(operations) {
  const heap = new MinHeap();

  operations.forEach((op) => {
    const [processType, number] = op.split(' ').map((value, idx) => {
      return idx == 1 ? Number(value) : value;
    });

    if (processType === 'I') {
      heap.push(number);
    } else {
      heap.pop(number < 0 ? TYPE.MIN : TYPE.MAX);
    }
  });

  return heap.getHeap();
}
