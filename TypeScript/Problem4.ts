function isMatch(s: string, p: string): boolean {
    const save: (boolean | null)[][] = 
        Array.from({ length: s.length + 1 }, () => Array(p.length + 1).fill(null));
    return match(save, s, p, 0, 0);
};

function match(save: (boolean | null)[][], s: string, p: string, i: number, j: number): boolean {
    if (save[i][j] !== null) {
        return save[i][j]!;
    }
    if (j === p.length) {
        save[i][j] = (i === s.length);
        return save[i][j];
    }

    const firstMatch = (i < s.length && (p[j] === '.' || s[i] === p[j]));

    if (j + 1 < p.length && p[j + 1] === '*') {
        const zeroOccurrences = match(save, s, p, i, j + 2);
        const oneOrMoreOccurrences = firstMatch && match(save, s, p, i + 1, j);
        save[i][j] = zeroOccurrences || oneOrMoreOccurrences;
    } else {
        save[i][j] = firstMatch && match(save, s, p, i + 1, j + 1);
    }

    return save[i][j];
}