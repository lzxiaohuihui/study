package com.lz.study;

public class AntTree {
    public int maxTree(){
        int[][] cache = new int[3][4];
        int ans = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1<<4; j++) {
                if(!check(j)) {
                    continue;
                }

            }
        }
        return ans;
    }
    public boolean check(int p){
        for (int i = 0; ; i++) {
            if((p>>i&7)==7) {
                return false;
            }
            if(p>>i <= 7){
                break;
            }
        }
        return true;
    }
}
