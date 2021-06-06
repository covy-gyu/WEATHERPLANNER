package WPGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Calendar;
import java.util.Objects;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.scene.image.*;
import javafx.scene.chart.*;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;

public class HomeController implements Initializable {
	@FXML
	private Text txtDay1;
	@FXML
	private Text txtDay2;
	@FXML
	private Text txtDay3;
	@FXML
	private Text txtDay4;
	@FXML
	private ComboBox<String> comWeatherCity;
	@FXML
	private ComboBox<String> comWeatherState;
	@FXML
	private ComboBox<String> comWeatherCounty;
	@FXML
	private ComboBox<String> comHour;
	@FXML
	private ComboBox<String> comMin;
	
	Client client = new Client();

	// combobox
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		txtDay1.setText(Integer.toString(month) + "/" + Integer.toString(day));
		cal.add(cal.DATE, 1);
		month = cal.get(Calendar.MONTH) + 1;
		day = cal.get(Calendar.DAY_OF_MONTH);
		txtDay2.setText(Integer.toString(month) + "/" + Integer.toString(day));
		cal.add(cal.DATE, 1);
		month = cal.get(Calendar.MONTH) + 1;
		day = cal.get(Calendar.DAY_OF_MONTH);
		txtDay3.setText(Integer.toString(month) + "/" + Integer.toString(day));
		cal.add(cal.DATE, 1);
		month = cal.get(Calendar.MONTH) + 1;
		day = cal.get(Calendar.DAY_OF_MONTH);
		txtDay4.setText(Integer.toString(month) + "/" + Integer.toString(day));
		btnRefresh.setOnAction(e -> {
			try {
				handleBtnRefresh(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		btnSave.setOnAction(e -> handleBtnSave(e));
		btnDel.setOnAction(e -> handleBtnDel(e));
		comWeatherCity.setItems(city);
		comHour.setItems(alarmHour);
		comMin.setItems(alarmMin);

		Alarm autoAlarm = new Alarm();
		autoAlarm.setDaemon(true);
		autoAlarm.start();
	}

	private ObservableList<String> city = FXCollections.observableArrayList("강원도", "경기도", "경상남도", "경상북도", "광주광역시",
			"대구광역시", "대전광역시", "부산광역시", "서울특별시", "세종특별자치시", "울산광역시", "인천광역시", "전라남도", "전라북도", "제주특별자치도", "충청남도", "충청북도");

	private ObservableList<String> alarmHour = FXCollections.observableArrayList("00", "01", "02", "03", "04", "05",
			"06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
	private ObservableList<String> alarmMin = FXCollections.observableArrayList("00", "01", "02", "03", "04", "05",
			"06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
			"24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41",
			"42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59");
	
	private String selectedCity = null;
	private String selectedState = null;
	private String selectedCounty = null;
		

	@FXML
	public void handleWeatGroup(ActionEvent e) throws IOException {
		comWeatherState.getItems().clear();
		comWeatherCounty.getItems().clear();
		String weatherCity = comWeatherCity.getValue().toString();
		selectedCity = weatherCity;
		comWeatherState.getItems().addAll(client.setCityAndGetState(weatherCity));
		btnRefresh.setDisable(true);
	}

	@FXML
	public void handleWeatCounty(ActionEvent e) throws IOException {
		if(!Objects.equals(comWeatherState.getValue(), null)){
			String weatherCity = comWeatherCity.getValue().toString();
			String weatherState = comWeatherState.getValue().toString();
			comWeatherCounty.getItems().clear();
			selectedState = weatherState;
			comWeatherCounty.getItems().addAll(client.setStateAndGetCounty(weatherCity,weatherState));
			btnRefresh.setDisable(true);
		}
	}

	// weatherInfo
	@FXML
	private Text currentTemperature;
	@FXML
	private Text time1;
	@FXML
	private Text time2;
	@FXML
	private Text time3;
	@FXML
	private Text time4;
	@FXML
	private Text time5;
	@FXML
	private Text temper1;
	@FXML
	private Text temper2;
	@FXML
	private Text temper3;
	@FXML
	private Text temper4;
	@FXML
	private Text temper5;
	@FXML
	private Text txtRain1;
	@FXML
	private Text txtRain2;
	@FXML
	private Text txtRain3;
	@FXML
	private Text txtRain4;
	@FXML
	private Text txtRain5;
	@FXML
	private ImageView weatherImg1;
	@FXML
	private ImageView weatherImg2;
	@FXML
	private ImageView weatherImg3;
	@FXML
	private ImageView weatherImg4;
	@FXML
	private ImageView weatherImg5;
	@FXML
	private Text txtFineDust;
	@FXML
	private Text txtUltrafineDust;
	@FXML
	private Text txtOzone;
	@FXML
	private Text txtFineDustDegree;
	@FXML
	private Text txtUltrafineDustDegree;
	@FXML
	private Text txtOzoneDegree;
	@FXML
	private LineChart<String, Number> temperChart;

	public static String curTemp;
	public static String curProb;
	public static String curDust;
	public static String curDustDegree;

	@FXML
	public void handleWeatherInfo(ActionEvent e) throws IOException {
		btnRefresh.setVisible(true);
		btnRefresh.setDisable(false);

		temperChart.getData().clear();
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

		String[] time = new String[5]; // 시간정보
		String[] temperature = new String[5]; // 기온
		String[] probPreci = new String[5]; // 강수확률
		String[] rainAmount = new String[5]; // 강수량
		String[] cloud = new String[5]; // 구름 1 3 4

		String[] airInfo = new String[6];//대기오염
        client.getWeatherAndAir(selectedCity, selectedState, selectedCounty, time, temperature, probPreci, cloud, airInfo);

		for (int i = 0; i < time.length; i++) {
			time[i] = Integer.toString(i); // 시간
			temperature[i] = "18"; // 기온
			probPreci[i] = "0"; // 강수확률
			rainAmount[i] = "111"; // 강수량
			cloud[i] = "3"; // 구름
			series.getData().add(new XYChart.Data<String, Number>(time[i] + "시", Integer.parseInt(temperature[i]) + i));
		}
		curTemp = temperature[0];
		curProb = probPreci[0];
		series.setName("온도");
		temperChart.getData().add(series);

		// 주간날씨

		currentTemperature.setText(temperature[0] + "°C");
		time1.setText(time[0] + "시");
		time2.setText(time[1] + "시");
		time3.setText(time[2] + "시");
		time4.setText(time[3] + "시");
		time5.setText(time[4] + "시");
		temper1.setText(temperature[0] + "°C");
		temper2.setText(temperature[1] + "°C");
		temper3.setText(temperature[2] + "°C");
		temper4.setText(temperature[3] + "°C");
		temper5.setText(temperature[4] + "°C");
		if(Integer.parseInt(rainAmount[0]) != 0)
			txtRain1.setText(rainAmount[0] + "mm");
		if(Integer.parseInt(rainAmount[1]) != 0)
			txtRain2.setText(rainAmount[1] + "mm");
		if(Integer.parseInt(rainAmount[2]) != 0)
			txtRain3.setText(rainAmount[2] + "mm");
		if(Integer.parseInt(rainAmount[3]) != 0)
			txtRain4.setText(rainAmount[3] + "mm");
		if(Integer.parseInt(rainAmount[4]) != 0)
			txtRain5.setText(rainAmount[4] + "mm");

		Image cloudImg = new Image("file:WeatherPlanner/src/WPimg/cloud.png");
		Image moonImg = new Image("file:WeatherPlanner/src/WPimg/moon.png");
		Image rainImg = new Image("file:WeatherPlanner/src/WPimg/rain.png");
		Image snowImg = new Image("file:WeatherPlanner/src/WPimg/snow.png");
		Image sunImg = new Image("file:WeatherPlanner/src/WPimg/sun.png");
		Image sunCloudImg = new Image("file:WeatherPlanner/src/WPimg/sunCloud.png");
		Image moonCloudImg = new Image("file:WeatherPlanner/src/WPimg/moonCloud.png");

		for (int i = 0; i < time.length; i++) {
			if (Integer.parseInt(probPreci[i]) >= 50) {
				if (Integer.parseInt(temperature[i]) <= 0) {
					switch (i) {
						case 0:
							weatherImg1.setImage(snowImg);
							break;
						case 1:
							weatherImg2.setImage(snowImg);
							break;
						case 2:
							weatherImg3.setImage(snowImg);
							break;
						case 3:
							weatherImg4.setImage(snowImg);
							break;
						case 4:
							weatherImg5.setImage(snowImg);
							break;
						default:
							break;
					}
					continue;
				} else {
					switch (i) {
						case 0:
							weatherImg1.setImage(rainImg);
							break;
						case 1:
							weatherImg2.setImage(rainImg);
							break;
						case 2:
							weatherImg3.setImage(rainImg);
							break;
						case 3:
							weatherImg4.setImage(rainImg);
							break;
						case 4:
							weatherImg5.setImage(rainImg);
							break;
						default:
							break;
					}
					continue;
				}
			} else {
				if (Integer.parseInt(cloud[i]) == 3) {
					if (Integer.parseInt(time[i]) >= 18) {
						switch (i) {
							case 0:
								weatherImg1.setImage(moonCloudImg);
								break;
							case 1:
								weatherImg2.setImage(moonCloudImg);
								break;
							case 2:
								weatherImg3.setImage(moonCloudImg);
								break;
							case 3:
								weatherImg4.setImage(moonCloudImg);
								break;
							case 4:
								weatherImg5.setImage(moonCloudImg);
								break;
							default:
								break;
						}
						continue;
					} else {
						switch (i) {
							case 0:
								weatherImg1.setImage(sunCloudImg);
								break;
							case 1:
								weatherImg2.setImage(sunCloudImg);
								break;
							case 2:
								weatherImg3.setImage(sunCloudImg);
								break;
							case 3:
								weatherImg4.setImage(sunCloudImg);
								break;
							case 4:
								weatherImg5.setImage(sunCloudImg);
								break;
							default:
								break;
						}
						continue;
					}
				} else if (Integer.parseInt(cloud[i]) == 4) {
					switch (i) {
						case 0:
							weatherImg1.setImage(cloudImg);
							break;
						case 1:
							weatherImg2.setImage(cloudImg);
							break;
						case 2:
							weatherImg3.setImage(cloudImg);
							break;
						case 3:
							weatherImg4.setImage(cloudImg);
							break;
						case 4:
							weatherImg5.setImage(cloudImg);
							break;
						default:
							break;
					}
					continue;
				} else {
					if (Integer.parseInt(time[i]) >= 18) {
						switch (i) {
							case 0:
								weatherImg1.setImage(moonImg);
								break;
							case 1:
								weatherImg2.setImage(moonImg);
								break;
							case 2:
								weatherImg3.setImage(moonImg);
								break;
							case 3:
								weatherImg4.setImage(moonImg);
								break;
							case 4:
								weatherImg5.setImage(moonImg);
								break;
							default:
								break;
						}
						continue;
					} else {
						switch (i) {
							case 0:
								weatherImg1.setImage(sunImg);
								break;
							case 1:
								weatherImg2.setImage(sunImg);
								break;
							case 2:
								weatherImg3.setImage(sunImg);
								break;
							case 3:
								weatherImg4.setImage(sunImg);
								break;
							case 4:
								weatherImg5.setImage(sunImg);
								break;
							default:
								break;
						}
						continue;
					}
				}
			}
		}

		// dust
		String fineDust = airInfo[0]; // 미세먼지
        String ultrafineDust = airInfo[1]; // 초미세먼지
        String ozone = airInfo[2]; // 오존
		curDust = fineDust;

		String fineDustDegree = airInfo[3]; // 미세먼지정도
        String ultrafineDustDegree = airInfo[4]; // 초미세먼지정도
        String ozoneDegree = airInfo[5]; // 오존정도

		curDustDegree = fineDustDegree;

		txtFineDust.setText(fineDust + "㎍/m³");
		txtUltrafineDust.setText(ultrafineDust + "㎍/m³");
		txtOzone.setText(ozone + "ppm");

		switch (fineDustDegree) {
			case "1":
				txtFineDustDegree.setText("(좋음)");
				break;
			case "2":
				txtFineDustDegree.setText("(보통)");
				break;
			case "3":
				txtFineDustDegree.setText("(나쁨)");
				break;
			case "4":
				txtFineDustDegree.setText("(매우나쁨)");
				break;
			default:
				break;
		}

		switch (ultrafineDustDegree) {
			case "1":
				txtUltrafineDustDegree.setText("(좋음)");
				break;
			case "2":
				txtUltrafineDustDegree.setText("(보통)");
				break;
			case "3":
				txtUltrafineDustDegree.setText("(나쁨)");
				break;
			case "4":
				txtUltrafineDustDegree.setText("(매우나쁨)");
				break;
			default:
				break;
		}

		switch (ozoneDegree) {
			case "1":
				txtOzoneDegree.setText("(좋음)");
				break;
			case "2":
				txtOzoneDegree.setText("(보통)");
				break;
			case "3":
				txtOzoneDegree.setText("(나쁨)");
				break;
			case "4":
				txtOzoneDegree.setText("(매우나쁨)");
				break;
			default:
				break;
		}
	}

	// refresh
	@FXML
	private Button btnRefresh;

	public void handleBtnRefresh(ActionEvent e) throws IOException {
		System.out.println(comWeatherCity.getValue().toString());
		System.out.println(comWeatherState.getValue().toString());
		System.out.println(comWeatherCounty.getValue().toString());
		handleWeatherInfo(e);
		System.out.println("새로고침됨");
	}

	// 재난정보

	// 알람
	@FXML
	private Button btnSave;
	@FXML
	private Button btnDel;
	@FXML
	private DatePicker alarmDate;
	@FXML
	private CheckBox checkTem;
	@FXML
	private CheckBox checkRain;
	@FXML
	private CheckBox checkDust;
	@FXML
	VBox alarmList;
	@FXML
	private RadioButton alarmDetail;

	ToggleGroup alarmli = new ToggleGroup();
	String selected = "";
	public static ArrayList<String> aList = new ArrayList<String>();

	public void handleBtnSave(ActionEvent e) {
		if (!Objects.equals(alarmDate.getValue(), null)
				&& (checkTem.isSelected() || checkRain.isSelected() || checkDust.isSelected())
				&& !Objects.equals(comHour.getValue(), null) && !Objects.equals(comMin.getValue(), null)) {
			selected += alarmDate.getValue() + " " + comHour.getValue() + ":" + comMin.getValue() + " (";
			if (checkTem.isSelected())
				selected += "기, ";
			if (checkRain.isSelected())
				selected += "강, ";
			if (checkDust.isSelected())
				selected += "미";
			if (!checkDust.isSelected())
				selected = selected.substring(0, selected.length() - 2);
			selected += ")";

			aList.add(selected);
			alarmDetail = new RadioButton(selected);
			alarmDetail.setToggleGroup(alarmli);
			alarmList.getChildren().add(alarmDetail);
			selected = "";
		}
	}

	public void handleBtnDel(ActionEvent e) {
		if(!Objects.equals(alarmli.getUserData(), null)){
			aList.remove(((RadioButton)alarmli.getSelectedToggle()).getText());
			alarmList.getChildren().remove(alarmli.getSelectedToggle());
		}
	}
}
