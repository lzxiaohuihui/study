package com.lz.study;

import java.util.*;

/**
 * 排序
 * @author 小灰灰
 */
public class Sort {

    public static void quickSort(int[] nums, int start, int end){
        if(start >= end) {
            return ;
        }
        int left = start, right = end;

        int cur = nums[start];
        while(left < right){
            while(left<right && nums[right] >= cur){
                --right;
            }
            nums[left] = nums[right];
            while(left<right && nums[left] <= cur){
                ++left;
            }
            nums[right] = nums[left];
        }
        nums[left] = cur;
        quickSort(nums,start,left-1);
        quickSort(nums,left+1,end);
    }

    public static void insertSort(int[] nums){
        if (nums.length < 2){
            return ;
        }
        int cur ;
        for (int i=0; i<nums.length-1; ++i){
            cur = nums[i+1];
            if (nums[i] > cur){
                nums[i+1] = nums[i];
                int j = i;
                while(j>0 && nums[j-1] > cur){
                    nums[j] = nums[j-1];
                    --j;
                }
                nums[j] = cur;
            }
        }
    }

    public static void shellSort(int[] nums){
        for (int d=nums.length/2; d>=1; d/=2){
            for(int i=0; i<d; ++i){
                for(int j=i+d; j<nums.length; j+=d){
                    int cur = nums[j];
                    int k = j-d;
                    while(k>=0 && nums[k]>cur){
                        nums[k+d] = nums[k];
                        k -= d;
                    }
                    nums[k+d] = cur;
                }

            }
        }
    }

    public static void chooseSort(int[] nums){
        int j=0,min=0;
        int temp;
        while(j<nums.length){
            temp = nums[j];
            min = j;
            for (int i=j; i<nums.length; ++i){
                if(nums[i] < nums[min]){
                    min = i;
                }
            }
            nums[j] = nums[min];
            nums[min] = temp;
            ++j;
        }
    }

    public static void mergeSort(int[] nums, int[] result, int start, int end){
        if(start >= end){
            return ;
        }
        int mid = (start+end)/2;
        int start1 = start,end1 = mid;
        int start2 = mid+1,end2 = end;
        mergeSort(nums,result,start1,end1);
        mergeSort(nums,result,start2,end2);

        int k = start;
        while(start1 <= end1 && start2 <= end2){
            result[k++] = nums[start1]<nums[start2]?nums[start1++]:nums[start2++];
        }
        while(start1 <= end1){
            result[k++] = nums[start1++];
        }
        while(start2 <= end2){
            result[k++] = nums[start2++];
        }
        for (int i=start; i<=end; ++i){
            nums[i] = result[i];
        }
    }

    public static void radixSort(int[] nums){
        List<Queue<Integer>> list = new ArrayList<>();
        for (int i=0; i<10; ++i){
            list.add(new LinkedList<>());
        }
        int max = nums[0];
        for(int i=1; i<nums.length; ++i){
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        int j = 0;//记录最大的数的位数、
        while(max>0){
            max /= 10;
            ++j;
        }
        for(int i=0; i<j; ++i){
            for (int k=0; k<nums.length; ++k){
                int temp = (nums[k]/(int)Math.pow(10,i))%10;
                list.get(temp).offer(nums[k]);
            }
            int k=0;
            for (Queue<Integer> q : list){
                while (!q.isEmpty()){
                    nums[k++] = q.poll();
                }
            }
        }
    }

    public static int[] heapSort(int[] nums){
        int length=nums.length;
        if(length <= 0){
            return nums;
        }
        for (int i=length/2 - 1; i>=0; --i){
            adjustHelper(nums,i,length);
        }
        for(int j=length-1; j>0; --j){
            swap(nums, j, 0);
            adjustHelper(nums, 0,j);
        }
        return nums;
    }
    public static void adjustHelper(int[] nums,int i, int length){
        int temp = nums[i];
        for(int k=2*i+1; k<length; k = 2*k+1){
            if (k+1 < length && nums[k] < nums[k+1] ){
                k++;
            }
            if(nums[k] > temp){
                swap(nums,i,k);
                i = k;
            }else{
                break;
            }
        }
    }
    public static void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        /**
        int[] nums1 = new int[1000000];
        for (int i=0; i<nums1.length; ++i){
            nums1[i] = (int)(Math.random()*1000);
        }
        int[] nums2 = Arrays.copyOf(nums1,nums1.length);
        int[] nums3 = Arrays.copyOf(nums1,nums1.length);
        int[] nums4 = Arrays.copyOf(nums1,nums1.length);
        int[] nums5 = Arrays.copyOf(nums1,nums1.length);
        int[] nums6 = Arrays.copyOf(nums1,nums1.length);
        int[] nums7 = Arrays.copyOf(nums1,nums1.length);
        int[] nums8 = Arrays.copyOf(nums1,nums1.length);
        System.out.println("=========《大比拼》排序100万个0--1000的随机整数==========");
        Calendar c1 = Calendar.getInstance();

        quickSort(nums1,0,nums1.length-1);
        Calendar c2 = Calendar.getInstance();

//        insertSort(nums2);
        Calendar c3 = Calendar.getInstance();

        shellSort(nums3);
        Calendar c4 = Calendar.getInstance();

//        chooseSort(nums4);
        Calendar c5 = Calendar.getInstance();

        mergeSort(nums5,new int[nums5.length],0,nums5.length-1);
        Calendar c6 = Calendar.getInstance();

        radixSort(nums6);
        Calendar c7 = Calendar.getInstance();

        heapSort(nums7);
        Calendar c8 = Calendar.getInstance();


        Arrays.sort(nums8);
        Calendar c9 = Calendar.getInstance();

        System.out.println("----快排所耗时："+(c2.getTimeInMillis()-c1.getTimeInMillis())+" ms");
        System.out.println("----插入所耗时：退出直播间"+(c3.getTimeInMillis()-c2.getTimeInMillis())+" ms");
        System.out.println("----希尔所耗时："+(c4.getTimeInMillis()-c3.getTimeInMillis())+" ms");
        System.out.println("----选择所耗时：退出直播间"+(c5.getTimeInMillis()-c4.getTimeInMillis())+" ms");
        System.out.println("----归并所耗时："+(c6.getTimeInMillis()-c5.getTimeInMillis())+" ms");
        System.out.println("----基数所耗时："+(c7.getTimeInMillis()-c6.getTimeInMillis())+" ms");
        System.out.println("----堆排所耗时："+(c8.getTimeInMillis()-c7.getTimeInMillis())+" ms");
        System.out.println("----Arrays.sort()所耗时："+(c9.getTimeInMillis()-c8.getTimeInMillis())+" ms");
*/
        String[] str = new String[]{"hello","world"};
        List<String> list = Arrays.asList(str);
        list.add("hiahiha");
    }
}
