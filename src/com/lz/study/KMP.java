package com.lz.study;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KMP {
    public int[] build(String str){
        int[] next = new int[str.length()+1];
        next[0] = -1;
        int j = -1 ;
        for (int i=0; i<str.length(); ++i){
            while(j>=0 && str.charAt(i)!=str.charAt(j)){
                j = next[j];
            }
            next[i+1] = ++j;
        }
        return next;
    }

    public List<Integer> match(String text, String pattern){
        int n = pattern.length(), m = text.length();
        List<Integer> res = new ArrayList<>();
        int[] next =  build(pattern);
        int j = 0;
        for (int i=0; i<m; ++i){
            while (j > 0 && text.charAt(i) != pattern.charAt(j)){
                j = next[j];
            }
            if (text.charAt(i) == pattern.charAt(j)){
                ++j;
            }
            if (j == n){
                res.add(i-n+1);
                j = next[j];
            }
        }
        return res;

    }

    public static void main(String[] args) {
        KMP kmp = new KMP();
        System.out.println(kmp.match("ababababc","ababc"));
    }
}
