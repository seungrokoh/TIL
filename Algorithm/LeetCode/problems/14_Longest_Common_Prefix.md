# 14. Longest Common Prefix

:link: [문제 링크](https://leetcode.com/problems/longest-common-prefix/)
***

```java
import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];

        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        char[] target = strs[0].toCharArray();

        StringBuilder prefix = new StringBuilder();
        String result = "";
        for (char c : target) {
            prefix.append(c);
            if (isPossible(strs, prefix.toString())) {
                result = prefix.toString();
            }
        }
        return result;
    }

    private boolean isPossible(String[] strs, String prefix) {
        for (String str : strs) {
            if (!str.startsWith(prefix)) return false;
        }
        return true;
    }
}
```
