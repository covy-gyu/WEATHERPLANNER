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

	// 재난정보
	@FXML
	private Text txtETime;
	@FXML
	private Text txtEIntens;
	@FXML
	private Text txtELoc;
	@FXML
	private Text txtElong;
	@FXML
	private Text txtElati;
	@FXML
	private Text txtEWarn;
	@FXML
	private ImageView imgEarthq;
	@FXML
	private Text txtTyponName;
	@FXML
	private Text txtTyponRoute;
	@FXML
	private Text txtTyponSpeed;
	@FXML
	private Text txtTyponLati;
	@FXML
	private Text txtTyponLong;
	@FXML
	private Text txtTyponLoc;
	@FXML
	private Text txtTyponCenter;
	@FXML
	private Text txtTyponWind;
	@FXML
	private Text txtTyponStroke;
	@FXML
	private Text txtTyponStrom;
	@FXML
	private Text txtTyponWarn;
	@FXML
	private ImageView imgTypon;

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
		btnSave.setOnAction(e -> {
			try {
				handleBtnSave(e);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		btnDel.setOnAction(e -> {
			try {
				handleBtnDel(e);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		comWeatherCity.setItems(city);
		comHour.setItems(alarmHour);
		comMin.setItems(alarmMin);

		// String[] alarmStrings = null;
		// try {
		// alarmStrings = client.getAlarms();
		// } catch (IOException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// for(int i=0;i<alarmStrings.length;i++)
		// {
		// aList.add(alarmStrings[i]);
		// alarmDetail = new RadioButton(alarmStrings[i]);
		// alarmDetail.setToggleGroup(alarmli);
		// alarmList.getChildren().add(alarmDetail);
		// }

		Alarm autoAlarm = new Alarm();
		autoAlarm.setDaemon(true);
		autoAlarm.start();

		String[] earthqInfo = new String[7];
		try {
			client.getEarthquake(earthqInfo);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (!Objects.equals(earthqInfo, null)) {
			txtETime.setText("지진발생 시각 : " + earthqInfo[0]);
			txtEIntens.setText("진도 : " + earthqInfo[1]);
			txtELoc.setText("위치 : " + earthqInfo[2]);
			txtElati.setText("위도 : " + earthqInfo[3]);
			txtElong.setText("경도 : " + earthqInfo[4]);
			txtEWarn.setText("주의사항 : " + earthqInfo[5]);
			Image earthquake = new Image(earthqInfo[6]);
			imgEarthq.setImage(earthquake);
		}

		String[] typonInfo = new String[12];
		try {
			client.getTyphoon(typonInfo);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (!Objects.equals(typonInfo, null)) {
			txtTyponName.setText("  태풍이름 :" + typonInfo[0]);
			txtTyponRoute.setText("  진행방향 :" + typonInfo[1]);
			txtTyponSpeed.setText("  이동속도 :" + typonInfo[2]);
			txtTyponLati.setText("  위도 :" + typonInfo[3]);
			txtTyponLong.setText("  경도 :" + typonInfo[4]);
			txtTyponLoc.setText("  예상위치 :" + typonInfo[5]);
			txtTyponCenter.setText("  중심기압 :" + typonInfo[6]);
			txtTyponWind.setText("  최대풍속 :" + typonInfo[7]);
			txtTyponStroke.setText("  강풍반경 :" + typonInfo[8]);
			txtTyponStrom.setText("  폭풍반경 :" + typonInfo[9]);
			txtTyponWarn.setText("  주의사항 :" + typonInfo[10]);
			Image typon = new Image(typonInfo[11]);
			imgTypon.setImage(typon);
		}
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
		if (!Objects.equals(comWeatherState.getValue(), null)) {
			String weatherCity = comWeatherCity.getValue().toString();
			String weatherState = comWeatherState.getValue().toString();
			comWeatherCounty.getItems().clear();
			selectedState = weatherState;
			comWeatherCounty.getItems().addAll(client.setStateAndGetCounty(weatherCity, weatherState));
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
	private Text time6;
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
	private Text temper6;
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
	private Text txtRain6;
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
	private ImageView weatherImg6;
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

		String[] time = new String[6]; // 시간정보
		String[] temperature = new String[6]; // 기온
		String[] probPreci = new String[6]; // 강수확률
		String[] rainAmount = new String[6]; // 강수량
		String[] cloud = new String[6]; // 구름 1 3 4

		selectedCounty = comWeatherCounty.getValue();
		String[] airInfo = new String[6];// 대기오염
		client.getWeather(selectedCity, selectedState, selectedCounty, time, temperature, rainAmount, cloud);

		for (int i = 0; i < time.length; i++) {
			if (Objects.equals(time[i], null)) {
				break;
			}
			series.getData().add(new XYChart.Data<String, Number>(time[i] + "시", Integer.parseInt(temperature[i]) + i));
		}
		curTemp = temperature[0];
		curProb = probPreci[0];
		series.setName("온도");
		temperChart.getData().add(series);

		// 주간날씨

		currentTemperature.setText(temperature[0] + "°C");
		if (!Objects.equals(time[0], null))
			time1.setText(time[0] + "시");
		if (!Objects.equals(time[1], null))
			time2.setText(time[1] + "시");
		if (!Objects.equals(time[2], null))
			time3.setText(time[2] + "시");
		if (!Objects.equals(time[3], null))
			time4.setText(time[3] + "시");
		if (!Objects.equals(time[4], null))
			time5.setText(time[4] + "시");
		if (!Objects.equals(time[5], null))
			time6.setText(time[5] + "시");
		if (!Objects.equals(temperature[0], null))
			temper1.setText(temperature[0] + "°C");
		if (!Objects.equals(temperature[1], null))
			temper2.setText(temperature[1] + "°C");
		if (!Objects.equals(temperature[2], null))
			temper3.setText(temperature[2] + "°C");
		if (!Objects.equals(temperature[3], null))
			temper4.setText(temperature[3] + "°C");
		if (!Objects.equals(temperature[4], null))
			temper5.setText(temperature[4] + "°C");
		if (!Objects.equals(temperature[5], null))
			temper6.setText(temperature[5] + "°C");
		if (!Objects.equals(rainAmount[0], null) && Integer.parseInt(rainAmount[0]) != 0)
			txtRain1.setText(rainAmount[0] + "mm");
		if (!Objects.equals(rainAmount[1], null) && Integer.parseInt(rainAmount[1]) != 0)
			txtRain2.setText(rainAmount[1] + "mm");
		if (!Objects.equals(rainAmount[2], null) && Integer.parseInt(rainAmount[2]) != 0)
			txtRain3.setText(rainAmount[2] + "mm");
		if (!Objects.equals(rainAmount[3], null) && Integer.parseInt(rainAmount[3]) != 0)
			txtRain4.setText(rainAmount[3] + "mm");
		if (!Objects.equals(rainAmount[4], null) && Integer.parseInt(rainAmount[4]) != 0)
			txtRain5.setText(rainAmount[4] + "mm");
		if (!Objects.equals(rainAmount[5], null) && Integer.parseInt(rainAmount[5]) != 0)
			txtRain6.setText(rainAmount[5] + "mm");

		Image cloudImg = new Image("/WPimg/cloud.png");
		Image moonImg = new Image("/WPimg/moon.png");
		Image rainImg = new Image("/WPimg/rain.png");
		Image snowImg = new Image("/WPimg/snow.png");
		Image sunImg = new Image("/WPimg/sun.png");
		Image sunCloudImg = new Image("/WPimg/sunCloud.png");
		Image moonCloudImg = new Image("/WPimg/moonCloud.png");
		for (int i = 0; i < 6; i++) {
			if (!Objects.equals(rainAmount[i], null) && Integer.parseInt(rainAmount[i]) > 0) {
				if (!Objects.equals(temperature[i], null) && Integer.parseInt(temperature[i]) < 0) {
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
						case 5:
							weatherImg6.setImage(snowImg);
							break;
						default:
							break;
					}
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
						case 5:
							weatherImg6.setImage(rainImg);
							break;
						default:
							break;
					}
				}
			} else {
				if (!Objects.equals(cloud[i], null) && Integer.parseInt(cloud[i]) == 4) {
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
						case 5:
							weatherImg6.setImage(cloudImg);
							break;
						default:
							break;
					}
				} else {
					if (!Objects.equals(time[i], null)
							&& (5 < Integer.parseInt(time[i]) && Integer.parseInt(time[i]) < 20)) {
						if (!Objects.equals(cloud[i], null) && Integer.parseInt(cloud[i]) == 3) {
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
								case 5:
									weatherImg6.setImage(sunCloudImg);
									break;
								default:
									break;
							}
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
								case 5:
									weatherImg6.setImage(sunImg);
									break;
								default:
									break;
							}
						}
					} else {
						if (!Objects.equals(cloud[i], null) && Integer.parseInt(cloud[i]) == 3) {
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
								case 5:
									weatherImg6.setImage(moonCloudImg);
									break;
								default:
									break;
							}
						} else {
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
								case 5:
									weatherImg6.setImage(moonImg);
									break;
								default:
									break;
							}
						}
					}
				}
			}
		}

		client.getAir(airInfo);
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

	public void handleBtnSave(ActionEvent e) throws IOException {
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
			client.saveAlarm(selected);
			selected = "";
		}
	}

	public void handleBtnDel(ActionEvent e) throws IOException {
		if (!Objects.equals(alarmli.getUserData(), null)) {
			aList.remove(((RadioButton) alarmli.getSelectedToggle()).getText());
			alarmList.getChildren().remove(alarmli.getSelectedToggle());
			client.deleteAlarm(((RadioButton) alarmli.getSelectedToggle()).getText());
		}
	}
}
