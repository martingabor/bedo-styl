package controllers;

import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import users.*;


public class LoginController implements Initializable, ScreenSwitcher {
	@FXML
	private TextField nicknameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button loginButton;
	@FXML
	private Button registerButton;
	
	private static User activeUser;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		loginButton.setOnAction(event -> {
			
			if (checkLogin(nicknameField.getText(),passwordField.getText())) {
				switchToForumScreen(loginButton, getActiveUser());
			} else {
				System.out.println("Wrong username or password!");
			}
			
		});
		
		registerButton.setOnAction(event -> {
			switchToRegistrationScreen(registerButton);
		});
		
			
	
	}
	
	public void loginButtonAction() {
		loginButton.fire();
	}
	
	public static boolean checkLogin(String nickname, String password) {
		if (nickname.isEmpty() || password.isEmpty()) {
			return false;
		}
		try {
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/NAis","postgres","admin");
			Statement stmt = connection.createStatement();
			
			ResultSet rset = stmt.executeQuery("select id, nickname, password, first_name, last_name, id_gender, date_of_birth, city, address from users where nickname = '"+ nickname +"'");  
			rset.next();
	        String currNickname = rset.getString("nickname");
	        String currPassword = rset.getString("password");

	        if (nickname.equals(currNickname) && password.equals(currPassword)) {
	        		activeUser  = new Student(2, currNickname, rset.getString("first_name"), rset.getString("last_name"), rset.getInt("id_gender"), rset.getString("date_of_birth"), rset.getString("city"), rset.getString("address"));
	            	return true;
	        }			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public void setActiveUser(int user_id, String nickname, String first_name, String last_name, int gender, String date_of_birth, String city, String address) {
		this.activeUser = new Student(user_id, nickname, first_name, last_name, gender, date_of_birth, city, address);
	}

	public User getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}


}
