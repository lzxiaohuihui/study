package com.lz.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyThread {
//    public static class ThreadTest extends Thread{
//        @Override
//        public void run() {
//            System.out.println("i am a child thread");
//        }
//    }
//
//    public static void main(String[] args) {
//        ThreadTest thread = new ThreadTest();
//        thread.start();
//    }
//    public static class RunableTask implements Runnable{
//
//        @Override
//        public void run() {
//            System.out.println("i am a child thread");
//        }
//    }
//
//    public static void main(String[] args) {
//        RunableTask task = new RunableTask();
//        new Thread(task).start();
//        new Thread(task).start();
//    }
    public static class CallerTask implements Callable<String>{
        @Override
        public String call() throws Exception {
            return "hello world";
        }
    }

    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
        new Thread(futureTask).start();
        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

