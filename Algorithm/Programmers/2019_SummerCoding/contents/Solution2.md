# :page_facing_up: 올바른 괄호쌍 출력

문제에서 올바른 괄호쌍의 수를 나타내는 **정수 N**이 주어진다. 주어진 괄호쌍의 수로 나타낼 수 있는 모든 **올바른 괄호쌍을 출력**하는 문제였다. 괄호쌍을 출력할 때에는 오름차순으로 출력 되어야 한다.

문제의 접근 방식은 재귀를 통한 DFS로 풀이를 시작했다. 문자의 총 길이는 N * 2 이므로 모든 경우의 수를 확인하며 조건에 부합하는 형식만 출력을 한다.

구글링을 통해 알아보니 괄호 쌍 문제는 **카탈란 수** 알고리즘을 사용해 풀 수 있다.

```java
private void solve(List<String> result, String cur, int open, int close, int n) {
    if (cur.length() == n * 2) {
        result.add(cur);
        return;
    }

    if (open < n) solve(result, cur + "(", open + 1, close, n);

    if (close < open) solve(result, cur + ")", open, close + 1, n);

}
```
