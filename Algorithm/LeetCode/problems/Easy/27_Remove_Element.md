# 20. Remove Element

:link: [문제 링크](https://leetcode.com/problems/remove-element/)

**Difficulty : Easy**
***

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) return 0;

        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}
```
