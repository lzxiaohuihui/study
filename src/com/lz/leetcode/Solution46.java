import com.lz.leetcode.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author 小灰灰
 * 全排列
 */
public class Solution46{
    public List<List<Integer>> permute(int[] nums) {
        Stack<Integer> path = new Stack<>();
        boolean[] visited = new boolean[nums.length];
        List<List<Integer>> res = new ArrayList<>();
        travelNums(nums,visited,0,nums.length,path,res);
        return res;
    }

    private void travelNums(int[] nums, boolean[] visited, int curs, int length, Stack<Integer> path, List<List<Integer>> res) {
        if (curs==length){
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=0;i<length;i++){
            if (!visited[i]){
                path.push(nums[i]);
                visited[i] = true;
                travelNums(nums,visited,curs+1,length,path,res);
                path.pop();
                visited[i] = false;
            }

        }
    }

    public static void main(String[] args) {
        Solution46 solution46 = new Solution46();
        List<List<Integer>> list = solution46.permute(new int[]{1,2,3});
        System.out.println(list);
    }
}