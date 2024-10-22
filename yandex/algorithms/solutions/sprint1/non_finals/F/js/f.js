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

function isPalindrome(line) {
    let result = true;
    const regExp = /[a-zA-Z0-9]/i;
    let left = 0;
    let right = line.length - 1;
    while (left < right) {
        while (!regExp.test(line[left])) {
            left++;
        }
        while (!regExp.test(line[right])) {
            right--;
        }
        if (line[left].toLowerCase() !== line[right].toLowerCase()) {
            return false;
        }
        left++;
        right--;
    }

    return result;
}

function solve() {
    const line = readLine();
    if (isPalindrome(line)) {
        console.log("True")
    } else {
        console.log("False")
    }
}


function readLine() {
    const line = _inputLines[_curLine];
    _curLine++;
    return line;
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