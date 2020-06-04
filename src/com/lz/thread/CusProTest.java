package com.lz.thread;

import java.util.EmptyStackException;
import java.util.Random;
import java.util.Vector;

public class CusProTest {
    class MyStack{
        Vector<Character> v = new Vector<>();

        public void push(char c){
            v.addElement(c);
        }
        public synchronized char peek(){
            int size=size();
            if(size==0){
                throw new EmptyStackException();
            }
            return v.get(size-1);
        }

        public synchronized char pop(){
            int size=v.size();
            char ans=this.peek();
            v.removeElementAt(size-1);
            return ans;
        }

        public synchronized int size() {//没有synchronized会报错
            // TODO 自动生成的方法存根
            return v.size();
        }
    }

    class Producer extends Thread{
        MyStack stack=new MyStack();
        String name;

        public Producer(String name){
            this.name=name;
        }

        public Producer(){}

        @Override
        public synchronized void run(){
            Random r=new Random();
            char c;
            while(true){
                c=(char)(r.nextInt(27)+65);
                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                while(stack.size()==20){
                    try{
                        this.wait();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                stack.push(c);
                System.out.println(name+"压入："+c);
                this.notify();
            }
        }
    }

    class Consumer extends Thread{
        MyStack stack=new MyStack();
        String name;

        public Consumer(){}

        public Consumer(String name){
            this.name=name;
        }

        @Override
        public synchronized void run(){
            char c;
            while(true){
                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                while(stack.size()==0){
                    try{
                        this.wait();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                c=stack.pop();
                System.out.println(name+"弹出："+c);
                this.notify();
            }
        }
    }

    public static void main(String[] args) {
        CusProTest t=new CusProTest();
        MyStack stack=t.new MyStack();
        Producer p1=t.new Producer("p1");
        Producer p2=t.new Producer("p2");
        Consumer c1=t.new Consumer("c1");
        Consumer c2=t.new Consumer("c2");
        Consumer c3=t.new Consumer("c3");

        p1.stack=p2.stack=c1.stack=c2.stack=c3.stack=stack;

        p1.start();
        p2.start();
        c1.start();
        c2.start();
        c3.start();
    }
}
