# __Recursion05__

### Recursion의 응용 : Counting Cell in a Blob

* Binary 이미지
* 각 픽셀은 background pixel 이거나 image pixel
* 서로 연결된 image pixel들의 집합을 blob라고 부름
* 상하좌우 및 대각 방향으로도 연결된 것으로 간주

### pseudo code 01

현지 지목된 cell이 background cell이 아니라면 상하좌우 및 대각선을 모두 확인해서 `blob`인지를 확인한다.

```java
// Algoritm for countCells(x, y)

if the pixel(x,y) is outside the grid
    the result is 0;
else if pixel(x,y) is not an image pixel or already counted
    the result is 0;
else
    set the color of the pixel (x, y) to a red color;
    the result is 1 plus number of cells in each piece of
        the blob that includes a nearest neighbour;
```

### Java code

```java
private static int BACKGROUND_COLOR = 0;
private static int IMAGE_COLOR = 1;
private static int ALREADY_COUNTED = 2;

public int countCells(int x, int y){
    if (x < 0 || y < 0 || x >= N || y >= N)
        return 0;
    if (grid[x][y] != IMAGE_COLOR)
        return 0;
    else {
        grid[x][y] = ALREADY_COUNTED;
        return 1 + countCells(x-1, y+1) + countCells(x, y+1) + countCells(x+1, y+1) + countCells(x-1, y) + countCells(x+1, y) + countCells(x-1, y-1) + countCells(x, y-1) + countCells(x+1, y-1);
    }
}
```
