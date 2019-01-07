# __Recursion06__

### Recursion의 응용 : N Queens Problem
엄청 유명한 문제인 `N Queens`문제를 `Recursion`으로 풀어보자. 하나의 행에 한 개의 말 만이 놓여야 한다. 따라서 `경우의 수`를 따져본다면 `N^N`일 것이다.  

문제 해결 방식은 `첫 번째 행`에 말을 놓고 `두 번째 행`에서 경로가 겹치지 않는 위치에 말을 놓는다. 만약 `N개의 말이 모두 놓일 수 있는 상황`이면 문제가 해결되는 것이고 그렇지 않다면 중간에 다시 되돌아가 이전 말들의 위치를 바꿔 놓는다. 즉 `백트래킹(Backtracking)`을 이용한다.  

### 상태공간트리
`상태공간트리`란 찾는 해를 포함하는 트리이다. 즉 `해가 존재`한다면 그것은 반드시 이 트리의 어떤 한 노드에 해당한다. 따라서 이 트리를 `체계적으로 탐색`하면 해를 구할 수 있다. 트리를 검색하는 방식은 `깊이우선탐색(DFS)`으로 탐색을 진행한다.  

하지만 트리의 모든 노드를 탐색해야 하는 것은 아니다. `왜냐하면` 충돌 위치에 있는 노드는 더이상 탐색하지 않아도 되기 때문에다. 이렇게 더 이상 탐색하지 않아도 되는 노드를 `non-promissing` 또는 `infeasible`이라 한다.


### __Design Recursion__
`Backtracking` 을 `Recursion`으로 구현을 할 때, 가장 간단하면서 널리 쓰이는 코드의 구조는 다음과 같다.
```java
return-type queens( arguments ){
    if non-promissing
        report failure and return;
    else if success
        report answer and return;
    else
        visit children recursively;
}
```
queens라는 함수가 호출되는 것은 `상태공간트리`에서 내가 `어떤 노드`에 도착한 순간이라고 생각을 하고 설계를 하는 것이다.
* 노드에 도착 했을 때 현재 노드가 `non-promissing`한 노드인지 확인, 만약 `non-promissing`한 노드라면 `해당 노드`뿐만 아니라 `자식 노드`로 탐색을 할 필요가 없는 것이다.
* 노드에 도착 했을 때 `현재 노드`가 내가 찾고 있던 답인 경우
* `현재 노드`의 `자식 노드`들을 순서대로 방문을 한다.`(recursive 하게)`

### Java code

```Java
/*
 * @arg level : 트리의 level을 뜻함(현재 도착한 노드의 level)
 * @variable cols[] : 각 말들이 놓일 열 번호 (cols[1] : 1번 말이 놓인 열 번호)
 */

int [] cols = new int [N+1];
boolean queens(int level){
    if(!promissing(level))
        return false;
    else if (level == N){
        printQueens();
        return true;    // level == N 이라면 모든 말이 놓였다는 의미
    }
    else {
        for (int i = 1; i <= N; i++){
            cols[level + 1] = i;
            if(queens(level + 1))
                return true;
        }
        return false;
    }
}
```
여기서 생각해 봐야 할 것은 `level이 2인 경우`이미 `두 개의 말`은 어디에 놓일지 정해 졌다는 뜻이다. 즉 `1번말 부터 level번째 말`까지는 놓일 곳이 이미 정해졌다고 볼 수 있다. 여기서 각 레벨에서 말이 놓일 위치를 `cols 배열`에 저장을 한다.  

### Promissing Test

cols[]의 크기가 8이고 현재 level값이 4라고 가정을 해보자. 그렇다면 아래와 같은 상황일 것이다.  

[1] [2] [3] [4] [5] [6] [7] [8] <- 배열의 index  
[1] [4] [2] [3] [0] [0] [0] [0] <- `말의 열 위치`  

이 상황에서 모든 말들이 충돌하는지 검사하는 것이 아닌 마지막에 놓인 말인 `cols[4]`가 이전에 놓인 다른 말들과 충돌하는지에 대해서만 검사하는 것으로 충분하다. 왜냐하면 `이전 level`의 말들 간에는 이미 충돌이 없음이 보장되어 있기 때문이다. 따라서 `promissing Test`의 코드는 다음과 같다.

```Java
boolean promissing(int level) {
    for (int i = 1; i < level; i++){
        if(cols[i] == cols[level])  // 동일한 열에 놓여 있는지 검사
            return false;
        else if (level - i == Math.abs(cols[level] - cols[i]))
            return false;   // 같은 대각선에 놓여 있는지 검사
    }
    return true;
}
```
`promissing(int level)`함수에서 확인해야 할 것이 `같은 대각선에 놓여있는지`에 대한 검사인데 두 점이 `같은 대각선`에 놓여 있다면 두 점을 이었을 때 생기는 `삼각형`의 `밑변과 높이`는 동일 할 것이다. 또한 `밑변`의 경우 방향에 따라 `양수 또는 음수`가 되기 때문에 `절대값`을 취해준다.  

이렇게 모든 해답을 찾았을 때 Queen들의 위치는 `cols`배열을 출력하기만 하면 된다.

```java
void printQueens(){
    for (int i = 1; i < N; i++){
        System.out.println("(" + i + ", " + cols[i] + ")");
    }
}
```
