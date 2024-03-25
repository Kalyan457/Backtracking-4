import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Runner {

    public static void main(String[] args) {
        OptimalPlacementOfBuildings optimalPlacementOfBuildings = new OptimalPlacementOfBuildings();
        int result = optimalPlacementOfBuildings.findMinDistance(5,5,3);
        System.out.println("result:" + result);
    }

    public static class OptimalPlacementOfBuildings {
        int H;
        int W;
        int minDistance = Integer.MAX_VALUE;
        // TC: O(H * W * c((H * W),N))
        public int findMinDistance(int h, int w, int n) {
            this.H = h;
            this.W = w;
            int[][] grid = new int[H][W];
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    grid[i][j] = -1;
                }
            }
            backtrack(grid, 0, n);
            return minDistance;
        }

        private void bfs(int[][] grid) {
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[H][W];
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (grid[i][j] == 0) {
                        queue.add(new int[]{i, j});
                    }
                }
            }
            int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
            int distance = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int[] curr = queue.poll();
                    for (int[] dir : dirs) {
                        int nextX = curr[0] + dir[0];
                        int nextY = curr[1] + dir[1];
                        if (nextX >= 0 && nextX < H && nextY >= 0 && nextY < W && !visited[nextX][nextY]) {
                            queue.add(new int[]{nextX, nextY});
                            visited[nextX][nextY] = true;
                        }
                    }
                }
                distance++;
            }
            distance--;
            if (distance < minDistance) {
                minDistance = distance;
            }
        }
        private void backtrack(int[][] grid, int index, int n) {
            if (n == 0) {
                bfs(grid);
                return;
            }
            for (int i = index; i < H * W; i++) {
                int row = i / W;
                int col = i % W;
                grid[row][col] = 0;
                backtrack(grid, index + 1, n - 1);
                grid[row][col] = -1;
            }
        }
    }
}

