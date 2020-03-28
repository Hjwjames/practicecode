package main;

/**
 * 1。wait、notify属于object方法，需要在synchronize监控中的monitor中操作
 * 在本实例waitTest中监控的object就是run方法
 * 2。yeild、sleep、join属于thread包方法
 * yeild让出资源，自己则重新加入cpu竞争队列，可以设置优先级
 * join可以等待上个线程完成，业务场景如joinDemo，可以新建一个线程让运行缓慢的线程先走
 * 最后用join回归数据
 */
public class ThreadPractice {
    public static void main(String args[]) throws InterruptedException {
        //yeildTest();
        //waitTest();
    }
    public static void waitTest(){
        WaitTestThread thread = new WaitTestThread();
        for(int i = 0;i<10;i++){
            Thread t = new Thread(thread);
            t.start();
        }
    }

    public static class  WaitTestThread implements Runnable{
        volatile int i;
        @Override
        public synchronized void run() {
            try {
                notify();
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i++);
        }
    }
    public static void yeildTest(){
        Runnable runnable = () -> {
            for (int i = 0; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-----" + i);
                if (i % 20 == 0) {
                    Thread.yield();
                }
            }
        };
        new Thread(runnable, "栈长").start();
        new Thread(runnable, "小蜜").start();
    }
    public void joinDemo(){
        //....
//        Thread t=new Thread(payService);
//        t.start();
//        //....
//        //其他业务逻辑处理,不需要确定t线程是否执行完
//        insertData();
//        //后续的处理，需要依赖t线程的执行结果，可以在这里调用join方法等待t线程执行结束
//        t.join();
    }
}
