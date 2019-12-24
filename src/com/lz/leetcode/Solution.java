package com.lz.leetcode;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 57、插入区间
 *
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


}
