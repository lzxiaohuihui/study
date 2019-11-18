package com.lz.list;

import javax.swing.*;
import java.util.ArrayList;
//
public class MyStack {
    ArrayList<Object> stack ;
    //栈底下标
    int base;
    //栈顶下表
    int top;
    //栈大小
    int size;

    public MyStack(){
        this.stack = new ArrayList<>();
        this.size=0;
        this.base = 0;
        this.top = 0;
    }

    public void initStack(){
        this.stack = new ArrayList<>();
        this.size=0;
        this.base = 0;
        this.top = 0;
    }
    public void destroyStack(){
        initStack();
        this.stack = null;
    }

    public void clearStack(){
        initStack();
    }

    public boolean stackEmpty(){
        if (top==base){
            return true;
        }
        return false;
    }
    public int stackLength(){
        return this.stack.size();
    }
    public Object getTop(){
        return this.stack.get(size-1);
    }
    public void push(Object e){
        this.stack.add(e);
        this.size = this.stack.size();
        this.top = this.size;
    }
    public Object pop(){
        Object e = null;
        if (!stackEmpty()){
            e = stack.remove(this.size-1);
        }
        this.size = this.stack.size();
        this.base = 0;
        this.top = this.size;
        return e;
    }
    public void stackTraverse(){
        System.out.println(this.stack);
    }

    //十进制数转n进制数
    public void conversion(int data, int n){
        initStack();
        while (data>0){
            push(data%8);
            data = data/8;
            if (data==0){
                break;
            }
        }
        while (!stackEmpty()){
            System.out.println(pop());
        }
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
//        myStack.push(1);
//        myStack.push(3);
//        myStack.push(5);
//        myStack.push(2);
//        myStack.pop();
//        System.out.println(myStack.stackLength());
//        myStack.stackTraverse();
        myStack.conversion(1348,8);
    }

}

