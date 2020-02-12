# 28. Search Insert Position

:link: [문제 링크](https://leetcode.com/problems/search-insert-position/)

**Difficulty : Easy**
***

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return len;
    }
}
```
