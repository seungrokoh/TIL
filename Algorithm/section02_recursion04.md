# __Recursion04__

## Recursion의 응용 : 미로찾기(Maze)

* Recursive Thinking  
현재 위치에서 출구까지 가는 경로가 있으려면

    1. 현재 위치가 출구 이거나 혹은
    2. 이웃한 셀들 중 하나에서 현재 위치를 지나지 않고 출구까지 가는 경로가 있거나

이동한 셀을 표시(mark)해놓지 않으면 `무한루프`에 빠질 수 있다. 따라서 내가 방문한 셀에 대한 `방문표시`가 필요함  

### 미로찾기 : pseudo code 01

```java
boolean findPath(x, y){
    if (x,y) is the exit
        return true;
    else
        mark(x,y) as a visited cell;
        for each neighbouring cell (x', y') of (x, y) do
            if (x', y') is on the pathway and not visited
                if findPath(x', y')
                    return true;
        return false;
}
```
### 미로찾기 : pseudo code 02

```java
boolean findPath(x, y){
    if (x,y) is either on the wall or a visited cell
        return false;
    else if (x,y) is the exit
        return true;
    else
        mark(x,y) as a visited cell;
        for each neighbouring cell (x', y') of (x, y) do
            if findPath(x', y')
                return true;
        return false;
}
```

두 방법은 동일하지만 `code 01`은 함수를 한 번 더 호출하지는 않지만 코드가 깔끔하지 않다는 점과, `code 02`는 함수를 한 번 더 호출하지만 코드를 보기 쉽다는 점이 있다고 한다.  
하지만 `if문`을 한번 더 거치는 것과 `함수`가 한 번 더 호출되는 비용의 차이가 있을까? `recursion`에서 보기 간결한 `code 02`가 더 끌리는 코드인 것 같다.

### 미로찾기 구현

```java
public class Maze{
    private static int N = 8;
    private static int [][] maze = {
        {0, 0, 0, 0, 0, 0, 0, 1},
        {0, 1, 1, 0, 1, 1, 0, 1},
        {0, 0, 0, 1, 0, 0, 0, 1},
        {0, 1, 0, 0, 1, 1, 0, 0},
        {0, 1, 1, 1, 0, 0, 1, 1},
        {0, 1, 0, 0, 0, 1, 0, 1},
        {0, 0, 0, 1, 0, 0, 0, 1},
        {0, 1, 1, 1, 0, 1, 0, 0}
    }

    /*
     * PATHWAY_COLOR = white : 진행할 수 있는 길
     * WALL_COLOR = blue : 벽
     * BLOCKED_COLOR = red : 방문했지만 출구까지의 경로상에 있지 않은 cell(꽝)
     * PATH_COLOR = green : 방문했으면 출구로 가는 경로가 될 가능성이 있는 cell
     */
    private static final int PATHWAY_COLOR = 0;     
    private static final int WALL_COLOR = 1;        // blue : 벽
    private static final int BLOCKED_COLOR = 2;     // red : 방문했지만 출구까지
    private static final int PATH_COLOR = 3;

    public static boolean findMazePath(int x, int y){
        if (x < 0 || y < 0 || x >= N || y >= N)
            return false;
        else if (maze[x][y] != PATHWAY_COLOR)
            return false;
        else if (x == N-1 && y == N-1){
            maze[x][y] = PATH_COLOR;
            return true;
        }
        else {
            maze[x][y] = PATH_COLOR;
            if (findMazePath(x-1, y) || findMazePath(x, y+1) || findMazePath(x+1, y) || findMazePath(x, y-1)) {
                return true;
            }
            maze[x][y] = BLOCKED_COLOR;     //dead end
            return false;
        }
    }

    public static void main(String[] args){
        printMaze();
        findMazePath(0,0);
        printMaze();
    }
}
```
