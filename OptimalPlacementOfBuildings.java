//Here, we check all possible combinations and find the minimum distance that all these combinations give. First, we put a combination on grid and then we perform bfs to find the distance to farthest parking lot
//We do all the combinations and get the minimum distance among them
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Main1{
    public static void main(String[] args){
        BuildingPlacement buildingPlacement = new BuildingPlacement();
        System.out.print(buildingPlacement.findMinDistance(4,5,3));
    }

    public static class BuildingPlacement{
        int H; int W; int min;
        public int findMinDistance(int h, int w, int n){
            this.H = h;
            this.W = w;
            this.min = Integer.MAX_VALUE;
            boolean [][] grid = new boolean[H][W];
            backtrack(grid, 0, n);
            return min;
        }

        private void bfs(boolean[][] grid){
            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[H][W];
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    if(grid[i][j]){
                        q.add(new int[]{i,j});
                        visited[i][j] = true;
                    }
                }
            }
            int dist = 0;
            int[][] dirs = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}};
            while(!q.isEmpty()){
                int size = q.size();
                for(int i = 0; i<size; i++){
                    int[] curr = q.poll();
                    for(int [] dir: dirs){
                        int nr = dir[0] + curr[0];
                        int nc = dir[1] + curr[1];
                        if(nr >=0 && nc >=0 && nr < H && nc < W && !visited[nr][nc]){
                            visited[nr][nc] = true;
                            q.add(new int[]{nr, nc});
                        }
                    }
                }
                dist++;
            }
            dist--; //one step back
            if(dist < min){
                if(dist == 2){
                    System.out.println(Arrays.deepToString(grid));
                }
                min = dist;
            }
        }

        private void backtrack(boolean[][] grid, int idx, int N){
            //base
            if(N==0){
                bfs(grid);
                return;
            }
            //logic
            for(int i = idx; i < H+W; i++){
                //place building at curr i
                int r = i/W;
                int c = i%W;
                grid[r][c] = true;
                //recurse
                backtrack(grid, i+1, N-1);
                //backtrack
                grid[r][c] = false;
            }
        }
    }
}