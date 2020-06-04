package com.lz.leetcode;

import java.util.*;

/**
 * 380. 常数时间插入、删除和获取随机元素
 *
 *
 * @author 小灰灰
 */
public class RandomizedSet {
    Map<Integer,Integer> map;
    List<Integer> list;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!map.containsKey(val)){
            map.put(val,list.size());
            list.add(list.size(),val);
            return true;
        }
        return false;

    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (map.containsKey(val)){
            int i = map.get(val);
            list.remove(i);
            map.remove(val);
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        Random ra = new Random();
        return list.get(ra.nextInt(list.size()));
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(1);
        randomizedSet.insert(2);
        randomizedSet.insert(3);
        randomizedSet.remove(2);
        for (int i=0; i < 10; i++){
            System.out.println(randomizedSet.getRandom());

        }
    }
}
