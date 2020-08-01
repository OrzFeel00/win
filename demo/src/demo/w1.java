package demo;

import java.util.*;

public class w1 {
   public static void main(String[] args) {
	
	A a=new A();
	B b=new B();
	Thread t=new Thread(b);
	
	a.start();
	t.start();
	
	
}
   public static void a() {
	   Scanner sc =new Scanner(System.in);
	   System.out.println("请输入：");
	   String s=sc.nextLine();
	   System.out.println("你输入的是："+s);
	   sc.close();
   }
  public static void b() {
	  System.out.println("这是b");
  }
  
  public static class A extends Thread{
	  public void run() {
		  a();
	  }
  }
  public static class B implements Runnable{
	  public void run() {
		  b();
	  }
  }
}
