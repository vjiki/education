//https://contest.yandex.ru/contest/22450/run-report/106965308/
const readline = require('readline');
const reader = readline.createInterface({
    input: process.stdin
})

const lines = [];
let currentLine = 0;
const SIZE = 4;
const PLAYERS = 2;
const ATTEMPTS = 9;

reader.on('line', line => lines.push(line));
process.stdin.on('end', solve);

function hands(k, arr) {
    let result = 0;
    const counter = new Map();
    for (let i = 0; i < SIZE; i++) {
        for (let j = 0; j < SIZE; j++) {
            let temp = arr[i][j];
            if (temp === '.') continue;
            temp = Number(temp);
            if (counter.has(temp)) {
                counter.set(temp, counter.get(temp) + 1);
            } else {
                counter.set(temp, 1);
            }
        }
    }
    for (let i = 1; i <= ATTEMPTS; i++) {
        if (counter.has(i) && counter.get(i) <= k * PLAYERS) {
            result++;
        }
    }
    return result;
}

function solve() {
    const k = readInt();
    const arr = readArea();
    process.stdout.write(String(hands(k, arr)));
}

function readInt() {
    return Number(lines[currentLine++]);
}

function readArea() {
    const result = [];
    for (let i = 0; i < SIZE; i++) {
        result.push(readArray());
    }
    return result;
}

function readArray() {
    return lines[currentLine++].split('');
}
