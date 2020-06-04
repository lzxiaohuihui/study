package com.lz.thread;

/**
 * @author 小灰灰
 */
public class ThreadTest {
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                //获取resourceA
                try {

                    synchronized (resourceA){
                        System.out.println("threadA get resourceA lock");
                        //获取resourceB
                        synchronized (resourceB){
                            System.out.println("threadA get resourceB lock");

                            //释放resourceA
                            System.out.println("threadA relase resourceA lock");
                            resourceA.wait(1500);
                        }

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    //获取resourceA
                    synchronized (resourceA){
                        System.out.println("threadB get resourceA lock");
                        System.out.println("threadB try get resourceB lock...");
                        //获取resourceB
                        synchronized (resourceB){
                            System.out.println("threadB get resourceB lock!");
                            System.out.println("threadB release resourceA lock");
                            resourceA.wait();
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //启动线程
        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println("main over!");
    }
}
