package com.lz.leetcode;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * @author 小灰灰
 */
public class String8 {


    int[][] dir = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
    public int[][] updateMatrix(int[][] matrix) {
        int lr = matrix.length;
        int lc = matrix[0].length;
        Queue<Integer> queue = new LinkedList();
        for(int i=0; i<lr; i++){
            for(int j=0; j<lc; j++){
                queue.offer(i*lc+j);
                while(!queue.isEmpty()){
                    int temp = queue.poll();
                    int r = temp/lc;
                    int c = temp - r*lc;
                    if(matrix[r][c] == 0){
                        matrix[i][j] = Math.abs(i-r)+Math.abs(j-c);
                        break;
                    }
                    for(int[] d:dir){
                        if(r+d[0]>=lr || r+d[0]<0 || c+d[1]>=lc || c+d[1]<0){
                            continue;
                        }
                        queue.offer((r+d[0])*lc+c+d[1]);
                    }
                }
                queue.clear();
            }
        }
        return matrix;
    }



    public static String decodeString(String s) {
        String ans = new String();
        Stack<String> stack = new Stack();
        String temp = "";
        int multi = 0;
        for(int i=0; i<s.length(); i++){

            if(s.charAt(i) == '['){
                stack.push(temp+"");
                stack.push(multi+"");
                multi = 0;
                temp = "";
            }
            else if(s.charAt(i) == ']') {
                multi = Integer.parseInt(stack.pop());
                ans = stack.pop();
                for (int j=0; j<multi; j++){
                    ans += temp;
                }
                temp = ans;
                multi = 0;
            }
            else if (s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                multi = 10*multi + Integer.parseInt(s.charAt(i)+"");
            }
            else{
                temp += s.charAt(i);
            }
        }
        return temp;

    }


    public static String convert(String s) {
        String ans = new String();
        for (int i = 0; i < s.length(); i++) {
            if ((int) s.charAt(i)<65 || (int) s.charAt(i)>122 ){
                throw new InputMismatchException();
            }
            if (((int) s.charAt(i)>90 && (int) s.charAt(i)<97)){
                throw new InputMismatchException();
            }
            if ((int) s.charAt(i) > 90) {
                ans += (char) ((int) s.charAt(i) - 32);
            } else {
                ans += (char) ((int) s.charAt(i) + 32);
            }
        }
        return ans;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor){
            return image;
        }
        int lr = image.length;
        int lc = image[0].length;
        Queue<Integer> queue = new LinkedList();
        queue.add(sr*lc+sc);
        while(!queue.isEmpty()){
            int temp = queue.poll();
            int srr = temp/lc;
            int scc = temp - srr*lc;

            if(srr > 0){
                if(image[srr][scc] == image[srr-1][scc]){
                    if (!queue.contains(temp-lc)){
                        queue.add(temp-lc);
                    }
                }
            }
            if(scc > 0){
                if(image[srr][scc] == image[srr][scc-1]){
                    if (!queue.contains(temp-1)){
                        queue.add(temp-1);
                    }
                }
            }
            if(srr < lr-1){
                if(image[srr][scc] == image[srr+1][scc]){
                    if (!queue.contains(temp+lc)){
                        queue.add(temp+lc);
                    }
                }
            }
            if(scc < lc-1){
                if(image[srr][scc] == image[srr][scc+1]){
                    if (!queue.contains(temp+1)){
                        queue.add(temp+1);
                    }
                }
            }
            image[srr][scc] = newColor;


        }
        return image;
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int length = rooms.size();
        boolean[] flag = new boolean[length];
        Queue<Integer> queue = new LinkedList();
        List<Integer> list;
        // bfs(rooms, flag, queue);
        flag[0] = true;
        for(int i: rooms.get(0)){
            queue.add(i);
        }


        while(!queue.isEmpty()){
            int key = queue.poll();
            if(!flag[key]){
                list = new ArrayList(rooms.get(key));
                for(int i: list){
                    queue.add(i);
                }
                flag[key] = true;
            }

        }

        for(boolean i: flag){
            if(!i){
                return false;
            }
        }
        return true;
    }

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0){
            return null;
        }
        int length = preorder.length;

        return build(preorder, inorder, 0, length-1, 0, length-1);
    }

    public TreeNode build(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd){
        if(preEnd >= preorder.length || inEnd >= inorder.length){
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);

        for(int i=inStart; i<=inEnd; i++){
            if(inorder[i] == preorder[preStart]){

                root.left = build(preorder, inorder, preStart+1, preStart + i, inStart, inStart+i-1);
                root.right = build(preorder, inorder, preStart+i+1, preEnd, preStart+i+1, inEnd);
            }
        }
        return root;
    }
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList();
        ans.add(1);
        for(int i=1; i<=rowIndex; i++){
            for(int j=0; j<=i; j++){
                if(j==0 || j==i) {
                    ans.add(1);
                } else {
                    ans.add(ans.get(0)+ans.get(1));
                }

                if(j == 0) {
                    continue;
                }
                ans.remove(0);
            }
        }
        return ans;
    }

    Map<Integer, Integer> map = new HashMap();
    public int climbStairs(int n) {
        if(map.containsKey(n)){
            return map.get(n);
        }
        int result;
        if(n < 3){
            result = n;
        }else{
            result = climbStairs(n-1);
            result += climbStairs(n-2);
        }
        map.put(n,result);
        return result;

    }

    public int search(int[] nums, int target){
        if(nums.length == 0 || nums == null){
            return -1;
        }
        int left = 0, right = nums.length-1;
        while(left <= right){
            int mid = left + (right-left)/2;
            System.out.print(mid);
            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid] < target){
                if(nums[mid] < nums[left]){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }
            else{
                if(nums[left] > target){
                    left = mid + 1;
                    System.out.print(left);
                }else{
                    right = mid - 1;
                    System.out.print(right);
                }
            }
        }
        return -1;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList();
        Map<Integer,Integer> map = new HashMap();
        for(int i=0; i<nums.length; i++){
            map.put(i, nums[i]);
        }
        for(int i=0; i<nums.length; i++){
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }
            if(-nums[i] > nums[nums.length-1]+nums[nums.length-2]){
                continue;
            }
            int complement_x = 0 - nums[i];
            map.remove(i,nums[i]);
            for(int j=i+1; j<nums.length; j++){
                if(j!=i+1 && nums[j] == nums[j-1]){
                    continue;
                }
                int complement_y = complement_x - nums[j];
                map.remove(j,nums[j]);
                if(map.containsValue(complement_y) && complement_y >= nums[j] && complement_y >= nums[i]){
                    ans.add(Arrays.asList(nums[i],nums[j],complement_y));

                }
                map.put(j,nums[j]);
            }
            map.put(i,nums[i]);
        }
        return ans;

    }
    int start = 0;
    public void rotate(int[] nums, int k) {
        if(nums.length == 0 ) return ;
        while(k > nums.length){
            k -= nums.length;
        }
        rotateHelper(nums,start,nums.length-1,k);

    }
    public void rotateHelper(int[] nums, int start, int end, int k){
        int length = end-start+1;
        while(k > length){
            k -= length;
        }
        if(k == 0 || (end-start+1)<=k) {
            return;
        }
        int temp ;
        for(int i=start; i<start+k; ++i){
            temp = nums[i];
            nums[i] = nums[length-k+i];
            nums[length-k+i] = temp;
        }

        rotateHelper(nums,start+k,end,k);
    }




    Queue<Integer> queue = new LinkedList();
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans=0, area=0;
        boolean[][] flag = new boolean[m][n];
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(grid[i][j] == 1 && !flag[i][j]){
                    queue.offer(i*n+j);
                    flag[i][j] = true;
                    while(!queue.isEmpty()){
                        int temp = queue.poll();
                        int x = temp/n;
                        int y = temp%n;
                        expand(x,y,grid,flag);
                        ++area;
                    }
                    ans = (ans>area)?ans:area;
                    area = 0;
                }
            }
        }
        return ans;

    }
    public void expand(int i, int j, int[][] grid, boolean[][] flag){
        int m = grid.length, n = grid[0].length;
        if(i+1 < m && grid[i+1][j] == 1){
            if(!flag[i+1][j]) {
                flag[i+1][j] = true;
                queue.offer((i+1)*n+j);
            }
        }
        if(j-1 >= 0 && grid[i][j-1] == 1){
            if(!flag[i][j-1]) {
                flag[i][j-1] = true;
                queue.offer((i)*n+j-1);
            }
        }
        if(i-1 >=0 && grid[i-1][j] == 1){
            if(!flag[i-1][j]) {
                flag[i-1][j] = true;
                queue.offer((i-1)*n+j);
            }
        }
        if(j+1 < n && grid[i][j+1] == 1){
            if(!flag[i][j+1]) {
                flag[i][j+1] = true;
                queue.offer(i*n+j+1);
            }
        }
    }

    public static void main(String[]args)
    {
        String8 string8 = new String8();
        String a = "abc";
        for(byte b :a.getBytes()){
            System.out.print(b);
        }

    }
}
