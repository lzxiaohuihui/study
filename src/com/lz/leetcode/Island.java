package com.lz.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 小灰灰
 * 岛屿的数量
 */
public class Island {



    public int numIslands(char[][] grid) {
        int nr = grid.length;
        int nc = (nr==0)?0:grid[0].length;
        //岛屿数量
        int numsIsland = 0;
        Queue<Integer> queue = new LinkedList<>();

        for (int i=0; i<nr; i++){
            for (int j=0; j<nc; j++){
                if (grid[i][j] == '1'){
                    ++numsIsland;
                    queue.offer(i*nc+j);
                    grid[i][j] = '0';
                    while (!queue.isEmpty()){
                        int temp = queue.poll();
                        int x = temp/nc;
                        int y = temp - nc*x;

                        if (x>0 && grid[x-1][y] == '1'){
                            queue.offer((x-1)*nc+y);
                            grid[x-1][y] = '0';
                        }
                        if (y<nc-1 && grid[x][y+1]=='1'){
                            queue.offer(x*nc+y+1);
                            grid[x][y+1] = '0';
                        }
                        if (x<nr-1 && grid[x+1][y]=='1'){
                            queue.offer((x+1)*nc+y);
                            grid[x+1][y] = '0';
                        }
                        if (y>0 && grid[x][y-1]=='1'){
                            queue.offer((x)*nc+y-1);
                            grid[x][y-1] = '0';
                        }
                    }
                }
            }
        }
        return numsIsland;
    }

    public static void main(String[] args) {
        Island island = new Island();

        char[][] grid = {
                {'1','0','1','1','0'},
                {'1','1','0','1','0'},
                {'0','0','0','0','1'},
                {'0','1','0','1','0'}
        };
        char[][] grid1 = {};
        System.out.println(island.numIslands(grid1));
    }
}
