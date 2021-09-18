package lecture2homework;

/* Implement a counting semaphore.
*
* Semaphore 通常我们叫它信号量， 可以用来控制同时访问特定资源的线程数量，通过协调各个线程，以保证合理的使用资源。
*
* 信号量（Semaphore）又称为信号量、旗语，它以一个整数变数，提供信号，以确保在并行计算环境中，
* 不同进程在访问共享资源时，不会发生冲突。是一种不需要使用忙碌等待（busy waiting）的一种方法
*
* 如果信号量是一个任意的整数，通常被称为计数信号量（Counting semaphore），或一般信号量（general semaphore）；
* 如果信号量只有二进制的0或1，称为二进制信号量（binary semaphore）。
*
* a Counting semaphore with one permit is known as binary semaphore because it has
* only two state permit available or permit unavailable. Binary semaphore can be used
* to implement mutual exclusion or critical section where only one thread is allowed to execute.
* Thread will wait on acquire() until Thread inside critical section release permit
* by calling release() on semaphore.
* https://javarevisited.blogspot.com/2012/05/counting-semaphore-example-in-java-5.html
*
* 可以把它简单的理解成我们停车场入口立着的那个显示屏，每有一辆车进入停车场显示屏就会显示剩余车位减1，
* 每有一辆车从停车场出去，显示屏上显示的剩余车辆就会加1，
* 当显示屏上的剩余车位为0时，停车场入口的栏杆就不会再打开，车辆就无法进入停车场了，直到有一辆车从停车场出去为止
* https://zhuanlan.zhihu.com/p/98593407?ivk_sa=1024320u
* */

import java.util.Random;
import java.util.concurrent.Semaphore;

public class CountingSemaphore {

    private static Semaphore semaphore = new Semaphore(10);
    // A parking lot's capacity is 10.

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            // Imagine there are 100 cars entering the parking lot.
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println("====" + Thread.currentThread().getName() +
                                "Welcome to the parking lot.");
                        if(semaphore.availablePermits() == 0) {
                            System.out.println("No available space. Please wait.");
                        }
                        semaphore.acquire(); // Wait on acquire() to enter the parking lot.
                        System.out.println(Thread.currentThread().getName() + "Entering the parking lot.");
                        Thread.sleep(new Random().nextInt(10000)); // Simulating the car's staying time.
                        System.out.println(Thread.currentThread().getName() + "Leaving the parking lot.");
                        semaphore.release(); // releasing permit so that remaining cars can enter.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "your number is " + i + ". ");
            thread.start();
        }
    }
}
