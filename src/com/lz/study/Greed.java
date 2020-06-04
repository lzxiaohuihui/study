package com.lz.study;

import java.util.*;

public class Greed {
    public static void main(String[] args) {
        Map<String, Set<String>> map = new HashMap<>();
        String[] temp = new String[]{"北京","上海","天津"};
        map.put("k1",new HashSet<>(Arrays.asList(temp)));
        temp = new String[]{"广州","北京","深圳"};
        map.put("k2",new HashSet<>(Arrays.asList(temp)));
        temp = new String[]{"成都","上海","杭州"};
        map.put("k3",new HashSet<>(Arrays.asList(temp)));
        temp = new String[]{"上海","天津"};
        map.put("k4",new HashSet<>(Arrays.asList(temp)));
        temp = new String[]{"杭州","大连"};
        map.put("k5",new HashSet<>(Arrays.asList(temp)));

        Set<String> set = new HashSet<>();
        for (Set<String> s:map.values()){
            set.addAll(s);
        }

        int[] falg = new int[map.size()];
        int contains , i=0;
        for (Map.Entry<String, Set<String>> entry: map.entrySet()){
            contains = 0;
            for (String str: entry.getValue()){
                if (set.contains(str)) {
                    ++contains;
                }
            }
            falg[i++] = contains;
        }
        System.out.println(Arrays.toString(falg));

    }
}
