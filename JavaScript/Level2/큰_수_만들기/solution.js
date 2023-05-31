function solution(number, k) {
    const len = number.length - k;
    
    let result = '';
    let start = 0;
    while (start < number.length && result.length < len) {
        const bound = k + 1 + result.length;
        
        let max = 0;
        for(let i = start; i < bound; i++) {
            if (max < number[i] * 1) {
                max = number[i] * 1;
                start = i + 1;
                
                if (max === 9) break;
            }
        }
        result += (max + '');
    }
    
    return result;
}
