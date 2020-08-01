package SocketCS;
import java.io.*;
import java.net.*;

/**
 * Created with IntelliJ IDEA.
 * User: HYY
 * Date: 13-10-30
 * Time: 下午2:15
 * To change this template use File | Settings | File Templates.
 */
public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket;
        BufferedInputStream bufferedInputStream;//文件读取流
        DataOutputStream dataOutputStream;//向客户端发送的数据流
        try {
            serverSocket = new ServerSocket(10000);
            Socket socket = serverSocket.accept();
            System.out.println("客户端连接。");
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            bufferedInputStream = new BufferedInputStream(new FileInputStream("E:/123/1.txt"));
            byte[] buffer = new byte[1024];
            int read;
            while ((read = bufferedInputStream.read(buffer)) != -1) {
                dataOutputStream.write(buffer, 0, read);
                dataOutputStream.flush();
            }
            System.out.println("文件传输完毕！");
            bufferedInputStream.close();
            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }
}