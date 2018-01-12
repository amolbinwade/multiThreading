package com.MultiThreading.ProducerConsumerPattern;

import java.util.ArrayList;
import java.util.List;

public class Demo {

	List<Integer> list = new ArrayList<>();

	Producer prd = new Producer();
	Consumer con = new Consumer();

	public static void main(String args[]) {

		Demo d = new Demo();
		d.startDemo();
	}

	public void startDemo(){

		Thread prdThread = new Thread(() -> this.producerRun());

		Thread conThread = new Thread(() -> this.consumerRun());

		prdThread.start();
		conThread.start();
	}

	private void producerRun(){
		while(true){
			synchronized (list) {
				while(list.size() < 10){
					prd.produce(list);
				}	
				list.notifyAll();
				try {
					list.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
	
		
	}
	
	private void consumerRun(){

		while(true){
			synchronized (list) {
				while(list.size() > 0){
					con.consume(list);
				}	
				list.notify();
				try {
					list.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
	
	}


}
