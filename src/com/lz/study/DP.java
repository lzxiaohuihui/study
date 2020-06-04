package com.lz.study;

import java.util.HashMap;
import java.util.Map;

public class DP {
    /**
     * 斐波那契数列
     * @param n
     * @return 
     */
    public int fib(int n){
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i=1; i<=n; ++i){
            if (i == 1 || i == 2){
                cache.put(i,1);
            }else{
                cache.put(i,cache.get(i-1)+cache.get(i-2));
            }
        }
        return cache.get(n);
    }

    /**
     * 从数组中选出一些数,这些数不能相邻,并且这些数的和最大
     * {1,3,5,6,2,1,2,4,2,5}
     * @param args
     */
    public int opt(int[] nums){
        Map<Integer, Integer> cache = new HashMap<>();
        for (int index=0; index<nums.length; ++index) {
            if (index == 0) {
                cache.put(0,nums[0]);
            } else if (index == 1) {
                cache.put(1,Math.max(nums[0], nums[1]));
            } else {
                int choose = nums[index] + cache.get(index-2);
                int notChoose = cache.get(index-1);
                cache.put(index, Math.max(choose, notChoose));
            }
        }
        return cache.get(nums.length-1);
    }

    /**
     * 给定一个数组，和S
     * 若能从数组中选出几个不同的数，这个几个不同的数的和为S,则返回true，否则返回false
     * {1,3,5,6,2,1,2,4,2,5}
     * @param args
     */
    public boolean isExistSum(int[] nums, int S){
        boolean[][] cache = new boolean[nums.length][S+1];
        for (int i=0; i<=S; ++i){
            cache[0][i] = nums[0] == i;
        }
        for (int i=1; i<nums.length; ++i){
            cache[i][0] = true;
        }
        for (int i=1; i<nums.length; ++i){
            for (int j=1; j<=S; ++j){
                if (nums[i] > j){
                    cache[i][j] = cache[i-1][j];
                }else {
                    cache[i][j] = cache[i - 1][j - nums[i]] || cache[i - 1][j];
                }
            }
        }
        return cache[nums.length-1][S];

    }
    public static void main(String[] args) {
        DP dp = new DP();
        int[] arrs = new int[]{1,3,9,4,2,5};
//        System.out.println(dp.opt(arrs));
        System.out.println(dp.isExistSum(arrs,10));
        System.out.println(dp.isExistSum(arrs,11));
        System.out.println(dp.isExistSum(arrs,12));
        System.out.println(dp.isExistSum(arrs,13));
        System.out.println(dp.isExistSum(arrs,24));
        System.out.println(dp.isExistSum(arrs,25));
    }

    
}























