/****
 * This class will demonstrate how sequencing can be done or serialization can
 * be achieved in multiple thread executions. So programmer can definitely say which code in which thread
 * executed before the code in other thread.
 * Serialization here means the sequencing in threading and not object serialization.
 * Refer book Small Book Of Semaphores.
 * 
 * T1 prints 
 * A1
 * A2
 * A3
 * 
 * T2 prints
 * B1
 * B2
 * B3
 *  when both threads will run, there will not be any confirmed sequence without synchronization.
 * Purpose of this pattern is to create some constraint in threads that program will always print:
 * A1
 * B1
 * A2
 * B2
 * A3
 * B3
 * 
 */


package com.MultiThreading.threadSerialization;

import java.util.concurrent.Semaphore;

public class ThreadSerialization {

	Semaphore a1Done = new Semaphore(0);
	Semaphore b1Done = new Semaphore(0);
	Semaphore a2Done = new Semaphore(0);
	Semaphore b2Done = new Semaphore(0);
	Semaphore a3Done = new Semaphore(0);

	/**
	 * methodA prints : A1 A2 A3
	 * @throws InterruptedException 
	 */
	public void methodA() {

		System.out.println("A1");
		a1Done.release();
		try {
			b1Done.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("A2");
		a2Done.release();
		try {
			b2Done.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("A3");
		a3Done.release();

	}

	/**
	 * methodB prints : B1 B2 B3
	 * @throws InterruptedException 
	 */
	public void methodB() {
		try {
			a1Done.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("B1");
		b1Done.release();
		try {
			a2Done.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("B2");
		b2Done.release();
		try {
			a3Done.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("B3");


	}

	public void createTwoThreads() throws InterruptedException{ 
		ThreadSerialization ts = new ThreadSerialization();
		Thread T1 = new Thread(() -> ts.methodA());
		Thread T2 = new Thread(() -> ts.methodB());
		T1.start();
		T2.start();
		Thread.sleep(5000);
		System.out.println("test");
	}


}
