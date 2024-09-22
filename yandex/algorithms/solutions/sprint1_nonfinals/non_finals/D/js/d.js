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

function getWeatherRandomness(n, temperatures) {
    const result = [];
    for (let i = 0; i < n; i++) {
        if (i > 0 && temperatures[i] <= temperatures[i - 1] || (i < n - 1 && temperatures[i] <= temperatures[i + 1])) {
            continue;
        }
        result.push(temperatures[i]);
    }
    return result.length;
}

function solve() {
    const n = readInt();
    const temperatures = readArray();
    process.stdout.write(`${getWeatherRandomness(n, temperatures)}`);
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
