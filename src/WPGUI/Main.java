package WPGUI;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import dao.DBConnection;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	public static Socket LoginSocket;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root= FXMLLoader.load(getClass().getResource("LogIn.fxml"));
			Scene scene = new Scene(root,600,400);
			primaryStage.setTitle("LogIn");
			primaryStage.setScene(scene);
            primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("192.168.145.151", 7127); // 소켓 서버에 접속
		Myconn.setSocket(socket);
		launch(args);
	}
} 