package demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;

public class DemoClient {

          public static void main(String[] args) throws UnknownHostException, IOException {
			Socket socket=new Socket("127.0.0.1",8888);
			//当前地址对象
			InetAddress myAddress= socket.getInetAddress();
			SocketAddress otherAddress= socket.getRemoteSocketAddress();
			System.out.println("我是c");
			System.out.println("我的地址："+myAddress);
			System.out.println("客户端地址"+otherAddress);
			
			InputStream in =socket.getInputStream();
			OutputStream out=socket.getOutputStream();
			
		    Scanner sc=new Scanner(System.in);
		    
			
		    new Thread() {
		    	public void run() {
		    		byte[] buffer =new byte[1024];
		    		int count ;
		    		while(true) {
		    			 try {
							count=in.read(buffer);//流的读炒作，程序阻塞读取别人的流
							System.out.println("服务器说："+new String(buffer,0,count));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    			
		    		}
		    		
		    		
		    	
		    	}
		    	
		    }.start();
		    
	        new Thread() {
	           public void run() {
	        	   while(true) {
	        	   System.out.println("客户端说：");//向别人输出流
	        	   try {
					out.write(sc.nextLine().getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	   }
		    		
		    	} 
		    }.start();
		    
		    
			
		}
}
