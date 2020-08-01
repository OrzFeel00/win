package demo;

import java.util.*;

public class Demo4 {
	public static void main(String[] args) {
		ProducerConsumer pc=new ProducerConsumer();
		new Thread() {
			public void run() {
				try {
					pc.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		
		new Thread() {
			public void run() {
				try {
					pc.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		
		
	}
		
		
	}
	
	
	
	class ProducerConsumer{
		private List<Integer> list=new ArrayList<>();
		
   public synchronized  void producer() throws InterruptedException {
	   while(true) {
		   if(list.isEmpty()) {
			   for(int i=0;i<10;i++) {
				   list.add(i);
				   System.out.println("----------生产--"+i);
				   Thread.sleep(200);
			   }
		   }else {
			   notifyAll();
			   wait();
		   }
	   }
   }
   public synchronized  void consume() throws InterruptedException {
	   while(true) {
		   if(list.isEmpty()==false) {
			   for(int i=0;i<10;i++) {
				   list.remove(0);
				   System.out.println("=========消费=="+i);
				   Thread.sleep(100);
			   }
		   }else {
			   notifyAll();
			   wait();
		   }
	   }
   }
   
}
