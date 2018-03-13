
package com.aliyun.interview.kb.threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类ThreadCase的实现描述：两个线程交叉持续打印 “ABABAB......”
 *
 * @author yaoguang.du 2018/3/13 11:08
 */
public class ThreadCase {


    public static void main(String[] args) {

        //fairsync
        final ReentrantLock lock = new ReentrantLock(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    lock.lock();//lock
                    System.out.println(Thread.currentThread().getName() + " print A");//print A
                    try {
                        TimeUnit.SECONDS.sleep(1);//sleep 1 second
                    } catch (InterruptedException e) {
                        System.out.println("thread 1 InterruptedException exception");
                    } finally {
                        lock.unlock();//unlock to release
                    }

                }

            }
        }, "thread 1 ").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    lock.lock();//lock
                    System.out.println(Thread.currentThread().getName() + " print B");//print B
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.println("thread 2 InterruptedException exception");
                    } finally {
                        lock.unlock();
                    }

                }
            }
        }, "thread 2 ").start();
    }

}

