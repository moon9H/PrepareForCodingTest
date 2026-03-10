const readline = require("readline");

const solution = (input) => {
    const [max, count] = input.shift().split(" ").map(Number);
    const data = input.map((val) => val.split(" ").map(Number));
    const result = [];

    // 부모 노드 배열 초기화 (각 노드의 부모, 처음에는 자기자신)
    const parent = Array.from({ length: max + 1 }, (_, i) => i);
    // parent = [0 1 2 3 4 5 6 7]

    // 루트 노드를 찾는 함수
    const find = (x) => {
        if (parent[x] !== x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    };

    // 두 집합을 합치는 함수
    const union = (a, b) => {
        const rootA = find(a);
        const rootB = find(b);
        if (rootA !== rootB) {
            parent[rootB] = rootA; // 한쪽을 다른 쪽의 루트노드로 설정
        }
    };

    for (const [cmd, a, b] of data) {
        if (cmd === 0) {
            union(a, b);
        } else if (cmd === 1) {
            result.push(find(a) === find(b) ? "YES" : "NO");
        }
    }

    return result.join("\n");
};

const input = [];
readline
    .createInterface({
        input: process.stdin,
        output: process.stdout,
    })
    .on("line", (line) => {
        input.push(line);
    })
    .on("close", () => {
        console.log(solution(input));
    });
