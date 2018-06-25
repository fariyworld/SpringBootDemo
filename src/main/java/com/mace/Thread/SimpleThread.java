package com.mace.Thread;

/**
 * description: A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同时运行，交替打印10次ABC。
 *
 * Object的wait()，notify()
 * 调用obj.wait()会立即释放锁，，以便其他线程可以执行obj.notify()，
 * wait()释放prev对象锁，暂停当前线程，等待再次被唤醒后进入循环。
 *
 * 但是notify()不会立刻立刻释放sycronized（obj）中的obj锁，
 * 必须要等notify()所在线程执行完synchronized（obj）块中的所有代码才会释放这把锁.
 * 释放自身对象锁，唤醒下一个等待线程
 * //而 yield(),sleep()不会释放锁。
 * <br />
 * Created by mace on 17:19 2018/6/14.
 */
public class SimpleThread implements Runnable {

    // 打印次数
    private static final int PRINT_COUNT = 10;
    // 前一个线程的打印锁
    private final Object fontLock;
    // 本线程的打印锁
    private final Object thisLock;
    // 打印字符
    private final char printChar;

    public SimpleThread(Object fontLock, Object thisLock, char printChar) {
        super();
        this.fontLock = fontLock;
        this.thisLock = thisLock;
        this.printChar = printChar;
    }
    @Override
    public void run() {
        // 连续打印PRINT_COUNT次
        for (int i = 0; i < PRINT_COUNT; i++) {
            // 获取前一个线程的打印锁
            synchronized (fontLock) {
                // 获取本线程的打印锁
                synchronized (thisLock) {
                    // 打印字符
                    System.out.print(printChar);
                    // 通过本线程的打印锁唤醒后面的线程
                    // notify和notifyall均可,因为同一时刻只有一个线程在等待
                    thisLock.notify();
                }
                try {
                    // 通过fontLock等待被唤醒
                    fontLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        // 打印A线程的锁
        Object lockA = new Object();
        // 打印B线程的锁
        Object lockB = new Object();
        // 打印C线程的锁
        Object lockC = new Object();
        // 打印a的线程
        Thread threadA = new Thread(new SimpleThread(lockC, lockA, 'A'));
        // 打印b的线程
        Thread threadB = new Thread(new SimpleThread(lockA, lockB, 'B'));
        // 打印c的线程
        Thread threadC = new Thread(new SimpleThread(lockB, lockC, 'C'));
        // 依次开启a b c线程
        threadA.start();
        Thread.sleep(100);
        threadB.start();
        Thread.sleep(100);
        threadC.start();
        Thread.sleep(100);
    }
}
