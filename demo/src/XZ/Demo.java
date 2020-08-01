package XZ;

import java.io.*;
import java.net.*;

public class Demo {
	
	public static void main(String[] args) throws IOException {
		URL url =new URL(
				"https://mirror.bit.edu.cn/apache/tomcat/tomcat-10/v10.0.0-M7/bin/apache-tomcat-10.0.0-M7-windows-x64.zip");
		URLConnection conn= url.openConnection();
		System.out.println("开始下载");
		InputStream in=conn.getInputStream();
		FileOutputStream out=new FileOutputStream("d:/apache-tomcat-10.0.0-M7-windows-x64.zip");
		System.out.println("tt");
		byte[] buffer=new byte[1024];
		int count;
		while((count=in.read(buffer))>0) {
			out.write(buffer,0,count);
		}
		in.close();
		out.close();
		System.out.println("下载完成");
	}

}
