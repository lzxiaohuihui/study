package com.lz.thread;

import com.lz.thread.test.Test2;

public class Test {
    static Hero gareen = new Hero();
    static class Reduce implements Runnable{
        @Override
        public void run() {
            while(true){
                gareen.hurt();
                System.out.printf("t1 为%s 减血1点,减少血后，%s的血量是%.0f%n",gareen.getName(),gareen.getName(),gareen.getHp());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    static class Recover implements Runnable{

        @Override
        public void run() {
            while(true){
                gareen.recover();
                System.out.printf("t2 为%s 回血1点,增加血后，%s的血量是%.0f%n",gareen.getName(),gareen.getName(),gareen.getHp());

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        gareen.setName("盖伦");
        gareen.setHp(616);
        gareen.maxHp = 1000;
        Reduce rd = new Reduce();
        for(int i=0; i<3; i++){
            new Thread(rd).start();
        }
        Recover rc = new Recover();
        for(int i=0; i<5; i++){
            new Thread(rc).start();
        }
    }
}
