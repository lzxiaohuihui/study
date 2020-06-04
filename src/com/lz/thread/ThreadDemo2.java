package com.lz.thread;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class ThreadDemo2 {
    /**
     * 事件一，考试
     * 假设今天考试，20个学生，1个监考老师。规定学生可以提前交卷，即把卷子留下，直接走人就行了。
     * 但老师必须等到所有的学生都走后，才可以收卷子，然后装订打包。
     * 如果把学生和老师都看作线程，就是1个线程和20个线程的配合问题，即等20个线程都结束了，这1个线程才开始。
     * 比如20个线程分别在计算数据，等它们都结束后得到20个中间结果，最后这1个线程再进行后续汇总、处理等。
     */
    static final int COUNT = 5;
    static CountDownLatch cdl = new CountDownLatch(COUNT);

    static class Teacher implements Runnable {

        CountDownLatch cdl;

        Teacher(CountDownLatch cdl) {
            this.cdl = cdl;
        }

        @Override
        public void run() {
            System.out.println("老师发卷子。。。");
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("老师收卷子。。。");
        }

    }

    static class Student implements Runnable {

        CountDownLatch cdl;
        int num;

        Student(int num, CountDownLatch cdl) {
            this.num = num;
            this.cdl = cdl;
        }

        @Override
        public void run() {
            System.out.printf("学生(%d)写卷子。。。\n", num);
            doingLongTime();
            System.out.printf("学生(%d)交卷子。。。\n", num);
            cdl.countDown();
        }
        public static void doingLongTime(){
            System.out.println("doingLongTime...");
        }
    }

    /**
     * 事件二，旅游
     * 最近景色宜人，公司组织去登山，大伙都来到了山脚下，登山过程自由进行。
     * 但为了在特定的地点拍集体照，规定1个小时后在半山腰集合，谁最后到的，要给大家表演一个节目。
     * 然后继续登山，在2个小时后，在山顶集合拍照，还是谁最后到的表演节目。
     * 接着开始下山了，在2个小时后在山脚下集合，点名回家，最后到的照例表演节目。
     */

    static CyclicBarrier cb = new CyclicBarrier(COUNT, new Singer());

    static class Singer implements Runnable {

        @Override
        public void run() {
            System.out.println("为大家唱歌。。。");
        }

    }

    static class Staff implements Runnable {

        CyclicBarrier cb;
        int num;

        Staff(int num, CyclicBarrier cb) {
            this.num = num;
            this.cb = cb;
        }

        @Override
        public void run() {
            System.out.printf("员工(%d)出发。。。\n", num);
            doingLongTime();
            System.out.format("员工(%d)到达地点一。。。\n", num);
            try {
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.format("员工(%d)再出发。。。\n", num);
            doingLongTime();
            System.out.format("员工(%d)到达地点二。。。\n", num);
            try {
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.format("员工(%d)再出发。。。\n", num);
            doingLongTime();
            System.out.format("员工(%d)到达地点三。。。\n", num);
            try {
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.format("员工(%d)结束。。。\n", num);
        }

        public static void doingLongTime() {
            System.out.println("doingLongTime...");
        }

    }

        public static void main(String[] args) throws Exception {
            for (int i = 0; i < COUNT; i++) {
                new Thread(new Staff(i, cb)).start();
            }
//            synchronized (ThreadDemo2.class) {
//                ThreadDemo2.class.wait();
//            }
        }
}

