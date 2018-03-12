package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import users.*;

public class ForumController implements Initializable, ScreenSwitcher {
	@FXML
	private TextArea chatOutput;
	@FXML
	private TextField chatInput;
	@FXML
	private Button sendButton;
	@FXML
	private Button backButton;
	@FXML
	private Label timeLabel;
	
	private User activeUser;
	private int second;
	private int minute;
	private int hour;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e ->  {
			Calendar cal = Calendar.getInstance();
			 second = cal.get(Calendar.SECOND);
			 minute = cal.get(Calendar.MINUTE);
		     hour = cal.get(Calendar.HOUR_OF_DAY);
		    
		    timeLabel.setText(((hour < 10) ? "0" + hour : hour) + ":" + ((minute < 10) ? "0" + minute : minute) + ":" + ((second < 10) ? "0" + second : second));
		}),
			new KeyFrame(Duration.seconds(1))
		);
		
		clock.setCycleCount(Animation.INDEFINITE);
		clock.play();
		
		
		chatOutput.setEditable(false);
		
		sendButton.setOnAction(event -> {
			chatOutput.appendText(timeLabel.getText() + " " + this.getActiveUser().getNickname() + ": " + chatInput.getText() + "\n");
			chatInput.clear();
		});
		
		backButton.setOnAction(event -> {
			switchToLoginScreen(backButton);
			
		});
		
	}
	
	public void sendButtonAction() {
		sendButton.fire();
	}
	public void backButtonAction() {
		backButton.fire();
	}
	
	public void setActiveUser(int user_id, String nickname, String first_name, String last_name, int gender, String date_of_birth, String city, String address) {
		this.activeUser = new Student(user_id, nickname, first_name, last_name, gender, date_of_birth, city, address);
	}
	
	public void setActiveUser(User user) {
		this.activeUser = user;
	}

	public User getActiveUser() {
		return activeUser;
	}



}
