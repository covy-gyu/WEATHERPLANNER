package WPGUI;

import java.io.IOException;
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

	Client client = new Client();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnLogin.setOnAction(e -> {
			try {
				handleBtnLoginAction(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}

	public void handleBtnLoginAction(ActionEvent e) throws IOException {
		String id = txtID.getText();
		System.out.println("ID: " + id);
		String password = txtPWD.getText();
		System.out.println("Password: " + password);

		int result = client.setLoginAndgetResult(id, password);

		switch (result) {
			case 1:
				try {
					Myconn.setSessUserID(id);
					Myconn.setSessUserPW(password);

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
				break;
			case 0:
				txtLoginFail.setText("비밀번호를 틀렸습니다");
				break;
			case -1:
				txtLoginFail.setText("존재하지 않는 id 입니다");
				break;
			case -2:
				txtLoginFail.setText("DB 오류");
			default:
				break;
		}
	}
}