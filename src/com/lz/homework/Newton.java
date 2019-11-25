package com.lz.homework;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 小灰灰
 * 牛顿插值多项式
 */
public class Newton {
    public static double F(ArrayList<String> items){
        if (items.size()>1){
            ArrayList<String> a = new ArrayList<>();
            ArrayList<String> b = new ArrayList<>();
            a.addAll(items);
            b.addAll(items);
            String head = items.get(0);
            String last = items.get(items.size()-1);
            a.remove(0);
            b.remove(items.size()-1);
            double gHead = g(head);
            double gLast = g(last);
            return (F(a) - F(b))/(gLast-gHead);
        }
        else{
            return (f(items.get(0)));
        }
//        else{
//            return 0;
//        }
    }
    public static double g(String x){
        if ("x0".equals(x)){
            return 0.40;
        }
        else if ("x1".equals(x)){
            return 0.55;
        }
        else if ("x2".equals(x)){
            return 0.65;
        }
        else if("x3".equals(x)){
            return 0.80;
        }
        else{
            return 0.90;
        }
    }
    public static double f(String x){
        if ("x0".equals(x)){
            return 0.41075;
        }
        else if ("x1".equals(x)){
            return 0.57815;
        }
        else if ("x2".equals(x)){
            return 0.69675;
        }
        else if("x3".equals(x)){
            return 0.88811;
        }
        else{
            return 1.02652;
        }
    }


    public static void main(String[] args) {
        ArrayList<String> items = new ArrayList<>();
        items.add("x0");
        items.add("x1");
        items.add("x2");
        items.add("x3");
//        items.add("x4");
        System.out.println(F(items));
    }
}
