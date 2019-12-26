# 28. Implement strStr()

:link: [문제 링크](https://leetcode.com/problems/implement-strstr/)

**Difficulty : Easy**
***

```java
class Solution {
    public int strStr(String haystack, String needle) {
        if (!haystack.contains(needle)) {
            return -1;
        }
        if (haystack.startsWith(needle)) {
            return 0;
        }

        return solve(haystack, needle);
    }

    private int solve(String haystack, String needle) {
        char[] hayStr = haystack.toCharArray();
        char[] needleStr = needle.toCharArray();

        int hayLen = haystack.length();
        int needleLen = needle.length();

        for (int i = 0; i <= hayLen - needleLen; i++) {
            if (isContains(hayStr, needleStr, i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isContains(char[] str, char[] needle, int startIndex) {
        int len = needle.length;
        for (int i = 0; i < len; i++) {
            if (str[i + startIndex] != needle[i]) return false;
        }
        return true;
    }
}
```
