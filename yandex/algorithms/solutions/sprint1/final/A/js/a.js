//https://contest.yandex.ru/contest/22450/run-report/106784224/
const readline = require('readline');
const reader = readline.createInterface({
    input: process.stdin
})

const lines = [];
let currentLine = 0;

reader.on('line', line => lines.push(line));
process.stdin.on('end', solve);

function nearestZero(n, arr) {
    const result = [];
    const zeroesFromLeft = new Array(n);
    const zeroesFromRight = new Array(n);
    for (let i = 0; i < n; i++) {
        if (arr[i] === 0) {
            zeroesFromLeft[i] = 0;
        } else {
            if (i === 0) {
                zeroesFromLeft[i] = (n - 1);
            } else {
                if (arr[i - 1] === 0) {
                    zeroesFromLeft[i] = (1);
                } else {
                    zeroesFromLeft[i] = (zeroesFromLeft[i - 1] + 1);
                }
            }
        }
    }
    for (let i = n - 1; i >= 0; i--) {
        if (arr[i] === 0) {
            zeroesFromRight[i] = (0);
        } else {
            if (i === n - 1) {
                zeroesFromRight[i] = (n - 1);
            } else {
                if (arr[i + 1] === 0) {
                    zeroesFromRight[i] = (1);
                } else {
                    zeroesFromRight[i] = (zeroesFromRight[i + 1] + 1);
                }
            }
        }
    }

    for (let i = 0; i < n; i++) {
        result.push(Math.min(zeroesFromLeft[i], zeroesFromRight[i]));
    }
    return result;
}

function solve() {
    const n = readInt();
    const arr = readArray();
    process.stdout.write(nearestZero(n, arr).join(' '));
}

function readInt() {
    return Number(lines[currentLine++]);
}

function readArray() {
    return lines[currentLine++].split(' ').map(item => Number(item));
}
