package com.MultiThreading.ProducerConsumerPattern;

import java.util.Iterator;
import java.util.List;

public class Consumer {

	public void consume(int num) {
		System.out.println("Consumed : "+ num);
	}
	
	public void consume(List<Integer> list) {
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()) {
			Integer num = it.next();
			System.out.println("Consumed : "+ num);
			it.remove();
		}
	}
}
