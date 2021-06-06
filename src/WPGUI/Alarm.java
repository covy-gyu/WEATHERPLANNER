package WPGUI;

import javafx.stage.Stage;
import javafx.scene.Scene;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Alarm extends Thread {
    public static String[] alData;
    public void a() {
        Date today = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat(" HH:mm");

        for (int i = 0; i < HomeController.aList.size(); i++) {
            alData = HomeController.aList.get(i).substring(18, HomeController.aList.get(i).length() - 1).split(", ");
            if ((date.format(today) + time.format(today)).equals(HomeController.aList.get(i).substring(0, 16))) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Parent notice = FXMLLoader.load(getClass().getResource("Notice.fxml"));
                            Scene scene = new Scene(notice, 600, 400);
                            Stage stage = new Stage();
                            stage.setTitle("Notice");
                            stage.setScene(scene);
                            stage.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                break;
            }
            a();
        }
    }
}
