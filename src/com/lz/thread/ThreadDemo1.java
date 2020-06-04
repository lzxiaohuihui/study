package com.lz.thread;

public class ThreadDemo1 {

    /**
     * 场景一，停止
     * “大胖，大胖，12点了，该去吃饭了，别写了”
     * “好的，好的，稍等片刻，把这几行代码写完就走”
     * 要点：把停止的信号传达给别人，别人处理完手头的事情就自己主动停止了。
     */
    static void stopByFlag() {
        ARunnable ar = new ARunnable();
        new Thread(ar).start();
        ar.tellToStop();
    }
    static class ARunnable implements Runnable {

        volatile boolean stop;

        void tellToStop() {
            stop = true;
        }

        @Override
        public void run() {
            System.out.println("进入不可停止区域 1。。。");
            doingLongTime(5);
            System.out.println("退出不可停止区域 1。。。");
            System.out.printf("检测标志stop = %s\n", String.valueOf(stop));
            if (stop) {
                System.out.println("停止执行");
                return;
            }
            System.out.println("进入不可停止区域 2。。。");
            doingLongTime(5);
            System.out.println("退出不可停止区域 2。。。");
        }

        private void doingLongTime(int i) {
            for (int j = 0; j < i; j++) {
                System.out.println("do "+i);
            }
        }
    }

    /**
     * 场景二，暂停/恢复
     * “大胖，大胖，先别发请求了，对方服务器快挂了”
     *      “好的，好的，等这个执行完就不发了”
     *      过了一会
     *      “大胖，大胖，可以重新发请求了”
     *      “好的，好的”
     * 要点：把暂停的信号传达给别人，别人处理完手头的事情就自己主动暂停了。
     * 但是恢复是无法自主进行的，只能由操作系统来恢复线程的执行。
     */
    static void pauseByFlag() throws InterruptedException {
        BRunnable br = new BRunnable();
        new Thread(br).start();
        br.tellToPause();
        Thread.sleep(1000);
        br.tellToResume();
    }

    static class BRunnable implements Runnable {

        volatile boolean pause;

        void tellToPause() {
            pause = true;
        }

        void tellToResume() {
            synchronized (this) {
                this.notify();
            }
        }

        @Override
        public void run() {
            System.out.println("进入不可暂停区域 1。。。");
            doingLongTime(5);
            System.out.println("退出不可暂停区域 1。。。");
            System.out.printf("检测标志pause = %s\n", String.valueOf(pause));
            if (pause) {
                System.out.println("暂停执行");
                try {
                    synchronized (this) {
                        this.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("恢复执行");
            }
            System.out.println("进入不可暂停区域 2。。。");
            doingLongTime(5);
            System.out.println("退出不可暂停区域 2。。。");
        }

        private void doingLongTime(int i) {
            for (int j = 0; j < i; j++) {
                System.out.println("do "+i);
            }
        }

    }

    /**
     * 场景三，插队
     * “大胖，大胖，让我站到你前面，不想排队了”
     * “好吧”
     * 要点：别人插队到你前面，必须等他完事后才轮到你。
     *
     */
    static void jqByJoin() throws InterruptedException {
        CRunnable cr = new CRunnable();
        Thread t = new Thread(cr);
        t.start();
        Thread.sleep(1);
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("终于轮到我了");
    }

    static class CRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("进入不可暂停区域 1。。。");
            doingLongTime(5);
            System.out.println("退出不可暂停区域 1。。。");
        }
        private void doingLongTime(int i) {
            for (int j = 0; j < i; j++) {
                System.out.println("do "+i);
            }
        }

    }

    /**
     * 场景四，叫醒
     * “大胖，大胖，醒醒，醒醒，看谁来了”
     * “谁啊，我去”
     * 要点：要把别人从睡梦中叫醒，一定要采取稍微暴力一点的手段。
     */

    static void stopByInterrupt() throws InterruptedException {
        DRunnable dr = new DRunnable();
        Thread t = new Thread(dr);
        t.start();
        Thread.sleep(2);
        t.interrupt();
    }

    static class DRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("进入暂停。。。");
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("收到中断异常。。。");
                System.out.println("做一些相关处理。。。");
            }
            System.out.println("继续执行或选择退出。。。");
        }

    }
    public static void main(String[] args) throws InterruptedException {
//        stopByFlag();
//        pauseByFlag();
//        jqByJoin();
        stopByInterrupt();
    }

}
