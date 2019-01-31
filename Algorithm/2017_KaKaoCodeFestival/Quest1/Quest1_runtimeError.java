public class Main {


    public static void main(String[] args) {
        TestData data = new TestData();
        Solution s = new Solution();

        System.out.println("======quest1======");
        int[] answer1 = s.solution(6, 4, data.quest1);
        System.out.println(answer1[0] + " " + answer1[1]);
        System.out.println("======quest2======");
        int[] answer2 = s.solution(100, 100, data.quest2);
        System.out.println(answer2[0] + " " + answer2[1]);


    }
}


class Solution {

    boolean[][] visited;
    int[][] pic;
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    int m, n;

    public void init(int m, int n) {
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = (pic[i][j] == 0);
            }
        }

        this.m = m;
        this.n = n;
    }

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        pic = picture;
        init(m,n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pic[i][j] != 0 && !visited[i][j]) {
                    ++numberOfArea;
                    int sum = dfs(pic[i][j], i, j);
                    if(sum > maxSizeOfOneArea) maxSizeOfOneArea = sum;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public boolean safe(int x , int y){
        return (0 <= x && x < m) && (0 <= y && y < n);
    }
    public int dfs(int color, int x, int y){
        int result = 1;
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            if (safe(x + dx[i], y + dy[i]) && pic[x + dx[i]][y + dy[i]] != 0 && !visited[x + dx[i]][y + dy[i]] && color == pic[x + dx[i]][y + dy[i]]) {
                result += dfs(color, x + dx[i], y + dy[i]);
            }
        }
        return result;
    }
}
