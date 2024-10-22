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

function factorize(number) {
    // Ваше решение
    const result = [];
    let temp = number;
    for (let i = 2; i <= temp;) {
        if (temp % i === 0) {
            result.push(i);
            temp = temp / i;
        } else {
            break;
        }
    }
    for (let i = 3; i <= temp;) {
        if (temp % i === 0) {
            result.push(i);
            temp = temp / i;
        } else {
            i += 2;
        }
    }
    if (result.length === 0) {
        result.push(number)
    }
    return result;
}

function solve() {
    const number = readInt();
    const factorization = factorize(number)
    process.stdout.write(`${factorization.join(' ')}`);
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
