package WPGUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class LogInController implements Initializable {
	public static Object LoginSocket;
    @FXML
	private TextField txtID;
	@FXML
	private Text txtLoginFail;
	@FXML
	private PasswordField txtPWD;
	@FXML
	private Button btnLogin;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnLogin.setOnAction(e -> handleBtnLoginAction(e));
	}

	public void handleBtnLoginAction(ActionEvent e) {
		String id = txtID.getText();
		System.out.println("ID: " + id);

		String password = txtPWD.getText();
		System.out.println("Password: " + password);
		if (id.equals("1"))
		{
			try {
				Stage loginstage = (Stage) btnLogin.getScene().getWindow();
				loginstage.close();
				FXMLLoader guest = new FXMLLoader(getClass().getResource("/WPGUI/Home.fxml"));
				Parent root = (Parent) guest.load();
				Stage stage = new Stage();
				stage.setTitle("WeatherPlanner");
				stage.setScene(new Scene(root));
				stage.show();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		else
		{
			txtLoginFail.setText("로그인 실패");
		}
	}
}