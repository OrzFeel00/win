package XZ;

import java.io.*;
import java.net.*;

public class DemoDX {
	//块数目
	private int downNums=0;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		new DemoD().download();
	
	}
	
	public void download() throws IOException, InterruptedException{
		URL url =new URL(
				"https://mirror.bit.edu.cn/apache/tomcat/tomcat-10/v10.0.0-M7/bin/apache-tomcat-10.0.0-M7-windows-x64.zip");
		
		String filename="d:/apache-tomcat-10.0.0-M7-windows-x64.zip";
		long time=System.currentTimeMillis();
		
		URLConnection conn= url.openConnection();
		
		int filesize=conn.getContentLength();
		
		int blocksize=1*1024*1024;
		
		int blocknums=filesize /blocksize;
		if(filesize %blocksize!=0) {
			blocknums++;
		}
		System.out.println("开始下载");
		//分块开始
		for(int i=0;i<blocknums;i++) {
		int	index=i;//jdk会自动对齐+final（匿名类里不能修改外部的变量）
			
			//线程开始
			downNums++;
			
			

			new Thread() {
				public void run() {
					
					
					try {
						System.out.println("第"+(index+1)+"块开始");
						URLConnection conn= url.openConnection();
						conn=url.openConnection();
						InputStream in=conn.getInputStream();
						FileOutputStream out=new FileOutputStream(filename+index);
						
						int beginBytes=index*blocksize;
						
						int endBytes=beginBytes+blocksize;
						
						if(endBytes>filesize) {
							endBytes=filesize;
						}
						
						in.skip(beginBytes);
						
						
						
						
						
						int position=beginBytes;
						byte[] buffer=new byte[1024];
						int count;
						while((count=in.read(buffer))>0) {
							if(position+count>endBytes) {
								int a=position+count-endBytes;
								
								count=count-a;
							}
							out.write(buffer,0,count);
							
							position+=count;
			                if(position>=endBytes) {
			                	break;
			                }
							
						}
						in.close();
						out.close();
						System.out.println("第："+(index+1)+"块下载完成");
						//里面类里我们要同步锁demod
						synchronized (DemoDX.this) {
							DemoDX.this.downNums--;
							DemoDX.this.notify();
							
							
							
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				};
			}.start();
			
			
			
		}
		
		//等待
		synchronized (this) {
			//如果当前下载的块数大0就等待
			while(downNums>0) {
				wait();
			}
			
		}
		
		marge(filename,blocknums);
		
		System.out.println("共花费了："+(System.currentTimeMillis()-time)/1000+"秒");
		
		System.out.println("下载结束");	
	}
	
	
	public static void marge(String path, int filenums) throws IOException {
		FileOutputStream fos=new FileOutputStream(path);
		for(int i=0;i<filenums;i++) {
			FileInputStream fis=new FileInputStream(path+i);
			byte[] buffer=new byte[1024];
			int count;
			while((count=fis.read(buffer))>0) {
				fos.write(buffer,0,count);
			}
			fis.close();
		}
		fos.close();
	}
	

}
