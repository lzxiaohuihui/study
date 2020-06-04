package com.lz.leetcode;

import java.util.*;

/**
 * @author 小灰灰
 */
public class MinStack {

    class Node{
        int value;
        int min;
        Node next;

        Node(int x, int min){
            this.value = x;
            this.min = min;
            next = null;
        }
    }
    Node head;

    public MinStack(){
    }

    public void push(int x) {
        if (head == null){
            head = new Node(x,x);
        }
        else {
            Node node = new Node(x,Math.min(x,head.min));
            node.next = head;
            head = node;
        }
    }

    public void pop() {
        if (null == null){
            head = head.next;
        }
    }

    public int top() {
        if (head != null){
            return head.value;
        }
        return -1;
    }

    public int getMin() {
        if (head != null){
            return head.min;
        }
        return -1;
    }

    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(1);
        obj.push(2);
        obj.push(-1);
        obj.pop();
        int param_3 = obj.top();
        int param_4 = obj.getMin();
        System.out.println(param_3);
        System.out.println(param_4);

    }
}
