package com.lz.homework;

import java.util.*;

/**
 * @author 小灰灰
 * 操作系统作业
 * 银行家算法
 */
public class Banker {
    private int n,m;
    private int[] available;
    private int[][] max,allocation,need;
    private Thread[] threads;
    public Banker(int n,int m){
        this.n = n;
        this.m = m;
        available = new int[m];
        max = new int[n][m];
        allocation = new int[n][m];
        need = new int[n][m];
    }

    /**
     * 暴力获取全部安全序列
     * @return
     */
    public ArrayList<ArrayList<Integer>> checkBank(){
        int[] work = new int[m];
        work = Arrays.copyOf(available,m);
        boolean[] finish = new boolean[m];
        boolean[] flag = new boolean[n];
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Stack<Integer> path = new Stack<>();
        travel(work,finish,flag,0,path,res);
        return res;
    }

    private void travel(int[] work, boolean[] finish,boolean[] flag, int curs, Stack<Integer> path, ArrayList<ArrayList<Integer>> res) {
        if (curs == n){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i=0; i<n; i++){
            if (!flag[i]){
                finish = new boolean[m];
                for (int j=0; j<m; j++){
                    if (need[i][j] <= work[j]){
                        finish[j] = true;
                    }
                }
                if (checkFlag(finish)){
                    for (int j=0; j<m; j++){
                        work[j] += allocation[i][j];
                    }
                    path.push(i);
                    flag[i] = true;
                    travel(work,finish,flag,curs+1,path,res);
                    path.pop();
                    for (int j=0; j<m; j++){
                        work[j] -= allocation[i][j];
                    }
                    flag[i] = false;
                }

            }
        }
    }
    public boolean requests(int i,int[] resource){
        System.out.println("本次进程"+i+"请求资源");
        for (int j=0; j<m; j++){
            if (resource[j] > need[i][j] || resource[j] > available[j]){
                System.out.println("本次请求会造成系统不安全！");
                System.out.println("拒绝该请求！");
                System.out.println();
                return false;
            }
            need[i][j] -= resource[j];
            available[j] -= resource[j];
            allocation[i][j] += resource[j];
        }

        ArrayList<ArrayList<Integer>> res = checkBank();
        if (res.size()>0){
            System.out.println("系统是安全的！");
            System.out.println("存在一个安全序列：");
            System.out.println(res.get(0));
            System.out.println("同意该次请求！");
            System.out.println();
            return true;
        }
        else{
            for (int j=0; j<m; j++){
                need[i][j] += resource[j];
                available[j] += resource[j];
                allocation[i][j] -= resource[j];
            }
        }
        System.out.println("本次请求会造成系统不安全！");
        System.out.println("拒绝该请求！");
        System.out.println();
        return false;
    }


    public boolean checkFlag(boolean[] flag){
        for (int i=0; i<flag.length; i++){
            if (!flag[i]) {
                return false;
            }
        }
        return true;
    }

    public void setAvailable(int[] available) {
        this.available = available;
    }

    public void setAllocation(int[][] allocation) {
        this.allocation = allocation;
    }

    public void setNeed(int[][] need) {
        this.need = need;
    }

    public static void main(String[] args) {
        int[] available = new int[]{3,3,2};
        int[][] allocation = new int[][]{{0,1,0},{2,0,0},{3,0,2},{2,1,1},{0,0,2}};
        int[][] need = new int[][]{{7,4,3},{1,2,2},{6,0,0},{0,0,1},{4,3,1}};
        Banker banker = new Banker(5,3);

        banker.setAvailable(available);
        banker.setAllocation(allocation);
        banker.setNeed(need);

        banker.requests(1,new int[]{1,0,2});
        banker.requests(4,new int[]{3,3,0});
        banker.requests(0,new int[]{0,2,0});
        banker.requests(0,new int[]{0,1,0});


    }
}
