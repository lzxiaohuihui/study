package com.lz.list;

import javax.swing.*;
import java.util.ArrayList;
//
public class MyStack {
    ArrayList<Object> stack ;
    Object base;
    Object top;
    int size;

    public MyStack(){
        this.stack = new ArrayList<>();
        this.size=0;
        this.base = null;
        this.top = null;
    }

    public void initStack(){
        this.stack = new ArrayList<>();
        this.size=0;
        this.base = null;
        this.top = null;
    }
    public void destroyStack(){
        this.stack = null;
        this.size=0;
        this.base = null;
        this.top = null;
    }

    public void clearStack(){
        this.size=0;
        this.base = null;
        this.top = null;
    }

    public boolean stackEmpty(){
        if (this.top==null||this.base==null){
            return true;
        }
        return false;
    }
    public int stackLength(){
        return this.stack.size();
    }
    public Object getTop(){
        return this.top;
    }
    public void push(Object e){
        this.stack.add(e);
        this.size = this.stack.size();
        this.base = this.stack.get(0);
        this.top = this.stack.get(size-1);
    }
    public Object pop(){
        Object e = null;
        if (stackEmpty()){
            e = stack.remove(this.size-1);
        }
        this.size = this.stack.size();
        this.base = this.stack.get(0);
        this.top = this.stack.get(size-1);
        return e;
    }
    public void stackTraverse(){
        System.out.println(this.stack);
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(3);
        myStack.push(5);
        myStack.push(2);
        myStack.pop();
        System.out.println(myStack.stackLength());
        myStack.stackTraverse();
    }

}

