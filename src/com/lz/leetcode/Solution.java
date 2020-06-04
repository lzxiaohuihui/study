package com.lz.leetcode;

import java.util.*;

/**
 * 57、插入区间
 *
 * @author 小灰灰
 */

public class Solution {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int start = -1;
        int end = 0;
        int [][] result;
        boolean hasStart = false;
        boolean hasEnd = false;
        for(int i=0; i<intervals.length; i++){
            if (newInterval[0] >= intervals[i][0]){
                start = i;
                hasStart = true;
            }
            if (newInterval[0] > intervals[i][1]) {
                start = i+1;
                hasStart = false;
            }

            if (newInterval[1] >= intervals[i][0]){
                end = i;
                hasEnd = true;
            }
            if(newInterval[1] > intervals[i][1]){
                end = i;
                hasEnd = false;
            }
        }
//        int start = (hasStart)? start:start+1;
//        int end = (hasEnd)? end:end-1;

        int resultLength = intervals.length - end + start;
        result = new int[resultLength][2];
        for (int i=0; i<resultLength; i++){

            if (i==start){
                result[i][0] = (hasStart)?intervals[start][0]:newInterval[0];
                result[i][1] = (hasEnd)?intervals[end][1]:newInterval[1];
            }
            else if(i<start){
                result[i] = intervals[i];
            }
            else{
                result[i] = intervals[i+intervals.length-resultLength];
            }
        }
        return result;
    }


    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack();
        for(int i=T.length-1; i>=0; --i){
            while(!stack.isEmpty() && T[i] >=  T[stack.peek()]){
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return ans;
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack();
        for (int i=0; i<tokens.length; i++){
            try {
                stack.push(Integer.parseInt(tokens[i]));
            }catch (NumberFormatException e){
                int a = stack.pop();
                int b = stack.pop();
                int res = 0;
                switch (tokens[i]){
                    case "+": res = b+a; break;
                    case "-": res = b-a; break;
                    case "*": res = b*a; break;
                    case "/": res = b/a; break;
                    default: ;
                }
                stack.push(res);
            }
        }
        return stack.pop();
    }
    public int findTargetSumWays(int[] nums, int S) {
        if(nums.length == 0){
            return 0;
        }
        Stack<Integer> stack = new Stack();

        int[] res = new int[1];
        boolean[][] flag = new boolean[nums.length][2];
        dfs(nums,flag,0,stack,res,S);

        return res[0];
    }

    public void dfs(int[] nums, boolean[][] flag, int curs, Stack<Integer> stack, int[] res, int S){
        if(curs == nums.length){
            List<Integer> list = new ArrayList<>(stack);
            int sum = 0;
            for(int i:list){
                sum += i;
            }
            if(sum == S){
                res[0] += 1;
            }

            return ;
        }

        for(int i=0; i<flag.length; ++i){
line1:      for(int j=0; j<flag[0].length; j++){
                if(!flag[i][j]){
                    flag[i][j] = true;
                    switch(j){
                        case 0: stack.push(nums[i]); break;
                        default: stack.push(nums[i]*(-1)); break;
                    }
                    dfs(nums,flag,curs+1,stack,res,S);
                    stack.pop();
                    flag[i][j] = false;
                    break line1;
                }
            }

        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.findTargetSumWays(new int[]{1,1,1,1,1},3);
        System.out.println(ans);
    }

}
