package com.lz.list;

public class DFS {
    private final int INT_MAX = 1000;
    private final int MAX_NUM = 20;
    //顶点
    private char[] vexs = new char[MAX_NUM];
    private boolean visited[] = new boolean[MAX_NUM];
    //当前顶点个数
    //当前边的个数
    private int vexnum;
    private int[][] edgeWeight = new int[8][8];

    public void createGraph(){
        this.vexnum = 8;
        this.vexs = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
        //权值
        this.edgeWeight = new int[][]{
                {0, 1, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0}
        };
    }

    //查找顶点v在数组的位序
    public Integer vertexLoc(char v){
        for (int i=0;i<this.vexs.length;i++){
            if (v == vexs[i]){
                return i;
            }
        }
        return null;
    }

    public int firstAdjVex(int v){
        for (int i=0;i<this.vexnum;i++){
            if (this.edgeWeight[v][i] != 0 && v!=i){
                return i;
            }
        }
        return -1;
    }
    public int nextAdjVex(int v,int w){
        for (int i=w+1;i<this.vexnum;i++){
            if (this.edgeWeight[v][i] != 0 && v!=i){
                return i;
            }
        }
        return -1;
    }
    public void dfs(int v){
        this.visited[v] = true;
        System.out.println(v+1);
        for (int w = firstAdjVex(v); w>=0; w=nextAdjVex(v,w)){
            if(!visited[w]){
                dfs(w);
            }
        }
    }
    public void dfsTraverse(int v){
        for (v=0; v<this.vexnum; v++){
            visited[v] = false;
        }
        for (v=0; v<this.vexnum; v++){
            if (!visited[v]){
                dfs(v);
            }
        }
    }

    public static void main(String[] args) {
        DFS dfs = new DFS();
        dfs.createGraph();
        dfs.dfsTraverse(0);
    }

}


