/****
 * This class will demonstrate how sequencing can be done or serialization can
 * be achieved in multiple thread executions. So programmer can definitely say which code in which thread
 * executed before the code in other thread.
 * Serialization here means the serialization in threading and not object 
 * serialization. Refer book Small Book Of Semaphores.
 */


package com.MultiThreading.threadSerialization;

import org.junit.Test;

public class ThreadSerializationTest {
	
private ThreadSerialization ts = new ThreadSerialization();


public void testMethodA1(){
	ts.methodA();
}


public void testMethodB1(){
	ts.methodB();
}

@Test
public void testCreateTwoThreads(){
	try {
		ts.createTwoThreads();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
