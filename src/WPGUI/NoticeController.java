package WPGUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.text.Text;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class NoticeController implements Initializable {
    @FXML
    private Text txtCurTime;
    @FXML
    private Text txtCurTemp;
    @FXML
    private Text txtCurRain;
    @FXML
    private Text txtCurDust;

    Date today = new Date();
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat time = new SimpleDateFormat(" HH:mm");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtCurTime.setText(date.format(today) + time.format(today));
        for (String data : Alarm.alData) {
            if (data.equals("기")) {
                txtCurTemp.setText("현재 온도 : " + HomeController.curTemp + "°C");
            }
            if (data.equals("강")) {
                txtCurRain.setText("강수 확률 : " + HomeController.curProb + "%");
            }
            if (data.equals("미")) {
                switch (HomeController.curDustDegree) {
                    case "1":
                        txtCurDust.setText("미세먼지 농도 : " + HomeController.curDust + "㎍/m³ (좋음)");
                        break;
                    case "2":
                        txtCurDust.setText("미세먼지 농도 : " + HomeController.curDust + "㎍/m³ (보통)");
                        break;
                    case "3":
                        txtCurDust.setText("미세먼지 농도 : " + HomeController.curDust + "㎍/m³ (나쁨)");
                        break;
                    case "4":
                        txtCurDust.setText("미세먼지 농도 : " + HomeController.curDust + "㎍/m³ (매우나쁨)");
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
