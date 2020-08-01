package demo;

import java.util.*;

public class demo3 {
	private List<String>list=new ArrayList<>();
	public  void add(String i) {
		list.add(i);
	}
	
	//同步方法synchronized
	
	/*同步代码块 ???是同步资源对象，要锁定的对象
	 * synchronized（？？？）{
	 * }
	 * 
	 * 
	 * 
	 * */
	public  String pop() {
		return list.remove(0);
	}

   public  int size() {
	   return list.size();
   }
   public static void main(String[] args) {
	   demo3 d=new demo3();
	Thread t1=new Thread("1"){
		public void run() {
			while(true) {
				if(d.size()<10) {
					for(int i=0;i<10;i++) {
						System.out.println(Thread.currentThread()+"::"+i);
						d.add(""+i);
					}
				}
				
			}
		}
		
	};
	Thread t2=new Thread("2222") {
		public void run() {
			while(true) {
				if(d.size()>0) {
					System.out.println(Thread.currentThread()+"::"+ d.pop());
				}
			}
		}
	};
	Thread t3=new Thread("3333") {
		public void run() {
			while(true) {
				if(d.size()>0) {
					System.out.println(Thread.currentThread()+"::"+ d.pop());
				}
			}
		}
	};
	Thread t4=new Thread("444444") {
		public void run() {
			while(true) {
				if(d.size()>0) {
					System.out.println(Thread.currentThread()+"::"+ d.pop());
				}
			}
		}
	};
	t1.start();
	t2.start();
	t3.start();
	t4.start();

}
}
