import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0, i = 0, j = 0;
        Set<Character> lett = new HashSet<>();
        
        while (j < s.length()) {
            if (!lett.contains(s.charAt(j))) {
                lett.add(s.charAt(j));
                res = Math.max(res, j - i + 1);
                j++;
            } else {
                lett.remove(s.charAt(i));
                i++;
            }
        }
        return res;
    }
}