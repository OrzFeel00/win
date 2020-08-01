package demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;

import javax.xml.stream.events.StartDocument;

/*
 * 
 * url半双工
 * Socket全双工 
 * */


//服务器端只是连接前的称呼
public class DemoServer {
	public static void main(String[] args) throws IOException {
		ServerSocket server=new ServerSocket(8888);
		//接受客户端连接
		System.out.println("服务器启动成功，监听8888等待连接");
		//io操作，会导致程序在这里阻塞,客户端连接成功后阻塞，同时返回socket对象（套接字对象）
		
		Socket socket= server.accept();
		//当前地址对象
		InetAddress myAddress= socket.getInetAddress();
		SocketAddress otherAddress= socket.getRemoteSocketAddress();
		System.out.println("我是s");
		System.out.println("我的地址："+myAddress);
		System.out.println("客户端地址"+otherAddress);
		
		InputStream in=socket.getInputStream();
		OutputStream out=socket.getOutputStream();
		
	    Scanner sc=new Scanner(System.in);
	    
	    new Thread() {
	    	public void run() {
	    		byte[] buffer =new byte[1024];
	    		int count ;
	    		while(true) {
	    			 try {
						count=in.read(buffer);//流的读炒作，程序阻塞
						System.out.println("客户端说："+new String(buffer,0,count));
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
        	   System.out.println("服务器说：");
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
