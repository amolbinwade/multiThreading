package com.MultiThreading.ProducerConsumerPattern;

import java.util.List;

public class Producer {
	
	public int produce() {
		int t = (int) (Math.random()*100);
		System.out.println("Produced : "+t);
		return t;
	}
	
	public void produce(List<Integer> list) {
		list.add(new Integer(this.produce()));
	}

}
