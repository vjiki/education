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

// Если ответ существует, верните список из двух элементов
// Если нет - то верните пустой список
function twoSum(array, targetSum) {
    const n = array.length
    const tempArr  = new Set();
    for (let i = 0; i < n; i++) {
        const targetEl=targetSum-array[i]

        if (tempArr.has(targetEl)) {
            return [array[i], targetEl]
        }

        tempArr.add(array[i])
    }

    return [];
}

function solve() {
    const n = readInt();
    const array = readArray();
    const targetSum = readInt();
    const ans = twoSum(array, targetSum);
    if (ans.length === 0) {
        console.log("None")
    } else {
        process.stdout.write(`${ans.join(' ')}`);
    }
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


const array="-9 -7 -6 -1 -1 3".split(' ').map(i=>Number(i))
const X=2
console.log(twoSum(array, X))
