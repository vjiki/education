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

function sumOfBinaries(firstNumber, secondNumber) {
    // Ваше решение
    const fL = firstNumber.length;
    const sL = secondNumber.length;
    const minLength = Math.min(fL, sL);
    const maxLength = Math.max(fL, sL);
    const result = [];
    let additional = 0;
    for (let i = 0; i < minLength; i++) {
        let bit = Number(firstNumber[fL - 1 - i]) + Number(secondNumber[sL - 1 - i]) + additional;
        if (bit / 2 >= 1) {
            additional = 1;
        } else {
            additional = 0;
        }
        result.unshift(bit % 2)
    }
    for (let i = minLength; i < maxLength; i++) {
        const value = fL > sL ? Number(firstNumber[fL - 1 - i]) : Number(secondNumber[sL - 1 - i])
        let bit = value + additional;
        if (bit / 2 >= 1) {
            additional = 1;
        } else {
            additional = 0;
        }
        result.unshift(bit % 2)
    }
    if (additional) {
        result.unshift(additional)
    }

    return result.join('');
}

function solve() {
    const firstNumber = readLine();
    const secondNumber = readLine();
    process.stdout.write(`${sumOfBinaries(firstNumber, secondNumber)}`);
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

function readLine() {
    const line = _inputLines[_curLine];
    _curLine++;
    return line;
}