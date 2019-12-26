# 20. Valid Parentheses

:link: [문제 링크](https://leetcode.com/problems/valid-parentheses/)

**Difficulty : Easy**
***

```java
import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        if (s.length() == 0) return true;
        if (s.length() % 2 == 1) return false;
        Stack<Character> leftBracket = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                leftBracket.push(c);
            } else {
                if (leftBracket.isEmpty()) return false;
                if (c == ')') {
                    if (leftBracket.pop() != '(') return false;
                } else if (c == '}') {
                    if (leftBracket.pop() != '{') return false;
                } else {
                    if (leftBracket.pop() != '[') return false;
                }
            }
        }
        return leftBracket.isEmpty();
    }
}
```
