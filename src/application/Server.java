package application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final static int SERVER_PORT = 7127;
    static UpdateDisater updateDister = new UpdateDisater();

    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("**************서버실행************** 포트번호="+SERVER_PORT);
            //DB 지진,태풍 테이블 업데이트 (3시간 주기로)
            updateDister.setDaemon(true);
            updateDister.start();
        
            //다수의 클라이언트와 통신하기위한 loop
            while (true) {
                System.out.println("클라이언트 접속 대기중...");
                socket = serverSocket.accept(); // 클라이언트 접속시 새로운 소켓이 리턴
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
                System.out.println(socket.getInetAddress() + "접속");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}