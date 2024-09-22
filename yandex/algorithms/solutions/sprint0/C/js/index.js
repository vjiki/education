const _readline = require('readline');

const _reader = _readline.createInterface({
    input: process.stdin
});

const _inputLines = [];
let _curLine = 0;

_reader.on('line', line => {
    _inputLines.push(line);
});

process.stdin.on('end', solve);

function movingAverage(array, k) {
    const n = array.length
    const result = []
    let firstSum = 0
    for (let i = 0; i < k; i++) {
        firstSum += array[i]
    }
    result.push(firstSum / k)
    for (let i = 0; i < n - k; i++) {
        let currSum = firstSum - array[i] + array[k + i]
        result.push(currSum / k)
        firstSum = currSum
    }

    return result
}

function solve() {
    const n = readInt();
    const arr = readArray();
    const windowSize = readInt();
    process.stdout.write(`${movingAverage(arr, windowSize).join(' ')}`);
}

function readInt() {
    const n = Number(_inputLines[_curLine]);
    _curLine++;
    return n;
}

function readArray() {
    var arr = _inputLines[_curLine].trim(" ").split(" ").map(num => Number(num));
    _curLine++;
    return arr;
}
