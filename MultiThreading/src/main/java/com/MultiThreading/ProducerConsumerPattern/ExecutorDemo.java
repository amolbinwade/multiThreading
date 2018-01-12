package com.MultiThreading.ProducerConsumerPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {
	
	ExecutorService executor = Executors.newFixedThreadPool(2);
	
	List<Integer> list = new ArrayList<>();
	
	Producer prd = new Producer();
	Consumer con = new Consumer();
	
	public static void main(String args[]){
		ExecutorDemo demo = new ExecutorDemo();
		
		demo.executor.execute(() -> demo.producerRun());
		
		demo.executor.execute(() -> demo.consumerRun());
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
