function lengthOfLongestSubstring(s: string): number {
    let res = 0,i = 0, j = 0;
    const found = new Set();
    while(j<s.length){
        if(!found.has(s[j])){
            found.add(s[j++]);
            res = Math.max(res, j-i);
        }else{
            found.delete(s[i++]);
        }
    }
    return res;
};