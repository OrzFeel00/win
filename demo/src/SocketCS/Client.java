package SocketCS;
import java.io.*;
import java.net.*;

/**
 * Created with IntelliJ IDEA.
 * User: HYY
 * Date: 13-10-30
 * Time: 下午2:29
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String[] args) {
        DataInputStream dataInputStream;//从服务器读取数据输入流
        FileOutputStream fileOutputStream;//写入本地文件流
        try {
            Socket socket = new Socket("127.0.0.1", 10000);
            System.out.println("连上服务器。");
            dataInputStream = new DataInputStream(socket.getInputStream());
            File file = new File("E:/122/3.txt");
            fileOutputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int read;
            while ((read=dataInputStream.read(buffer))>0) {
                fileOutputStream.write(buffer, 0, read);
            }
            System.out.println("文件接收完毕！");
            fileOutputStream.close();
            dataInputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
