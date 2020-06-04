package com.lz.thread;

import java.util.HashMap;
import java.util.concurrent.Exchanger;

public class ThreadDemo3 {
    /**
     * 事件三，劳动
     * 大胖和小白去了创业公司，公司为了节约开支，没有请专门的保洁人员。让员工自己扫地和擦桌。
     * 大胖觉得擦桌轻松，就让小白去扫地。可小白觉得扫地太累，也想擦桌。
     * 为了公平起见，于是决定，每人先干一半，然后交换工具，再接着干对方剩下的那一个半。
     */

    public static void main(String[] args) throws Exception {
        new Thread(new Staff("大胖", new Tool("笤帚", "扫地"), ex)).start();
        new Thread(new Staff("小白", new Tool("抹布", "擦桌"), ex)).start();
//        synchronized (ThreadDemo3.class) {
//            ThreadDemo3.class.wait();
//        }
    }

    static Exchanger<Tool> ex = new Exchanger<>();

    static class Staff implements Runnable {

        String name;
        Tool tool;
        Exchanger<Tool> ex;

        Staff(String name, Tool tool, Exchanger<Tool> ex) {
            this.name = name;
            this.tool = tool;
            this.ex = ex;
        }

        @Override
        public void run() {
            System.out.printf("%s拿的工具是[%s]，他开始[%s]。。。\n", name, tool.name, tool.work);
            doingLongTime();
            System.out.printf("%s开始交换工具。。。", name);
            try {
                tool = ex.exchange(tool);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.printf("%s的工具变为[%s]，他开始[%s]。。。", name, tool.name, tool.work);
        }
        public static void doingLongTime() {
            System.out.println("doingLongTime...");
        }
    }

    static class Tool {

        String name;
        String work;

        Tool(String name, String work) {
            this.name = name;
            this.work = work;
        }

    }
}
