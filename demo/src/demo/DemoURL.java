package demo;

import java.net.MalformedURLException;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class DemoURL {
	public static void main(String[] args) throws IOException {
	URL url=new URL("http://www.baidu.com");
	System.out.println(url.getProtocol());
	System.out.println(url.getPath());
	System.out.println(url.getHost());
	System.out.println(url.getFile());
	System.out.println(url.getQuery());
	
	URLConnection conn=  url.openConnection();
	
	System.out.println(conn.getLastModified());
	System.out.println(conn.getContentLengthLong());
	System.out.println(conn.getContentType());
	
	InputStream in= conn.getInputStream();
	
	byte[]  buffer=new byte[1024];
	
	int count;
	
	while((count = in.read(buffer))>0) {
		System.out.println("112121");
		System.out.println(new String(buffer,0,count));
	}
	in.close();
	
	
	
	
	
	
	
	
	}

}
