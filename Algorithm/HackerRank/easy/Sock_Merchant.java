import java.util.HashMap;

class Solution {
    // Complete the sockMerchant function below.
    public int sockMerchant(int n, int[] ar) {
        int answer = 0;
        HashMap<Integer, Integer> colorMap = new HashMap<>();
        for (int color : ar) {
            colorMap.put(color, colorMap.getOrDefault(color, 0) + 1);
        }

        for (Integer values : colorMap.values()) {
            answer += values / 2;
        }
        return answer;
    }
}
