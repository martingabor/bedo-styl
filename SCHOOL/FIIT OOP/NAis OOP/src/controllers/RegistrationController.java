package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class RegistrationController implements Initializable, ScreenSwitcher {
	@FXML
	private PasswordField passwordField;
	@FXML
	private PasswordField password2Field;
	@FXML
	private TextField nicknameField;
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField addressField;
	@FXML
	private DatePicker dateOfBirthPicker;
	@FXML
	private ChoiceBox<String> genderChoiceBox;
	@FXML
	private Button registerButton;
	@FXML
	private Button backButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		genderChoiceBox.setItems(FXCollections.observableArrayList("MALE","FEMALE"));
		
		registerButton.setOnAction(event -> {
			try {
				registerUser(nicknameField.getText(), passwordField.getText(), password2Field.getText(), firstNameField.getText(), lastNameField.getText(), genderChoiceBox.getValue(), dateOfBirthPicker.getValue() , cityField.getText(), addressField.getText());
				switchToLoginScreen(registerButton);

			} catch (EmptyRegistrationFieldsException e) {
				System.err.println(e);
				
			}
		});
		
		backButton.setOnAction(event -> {
			switchToLoginScreen(backButton);
			});
		}
	
	
	public void registerButtonAction() {
		registerButton.fire();
	}
	
	public static void registerUser(String nickname, String password1, String password2, 
			String first_name, String last_name, String gender, LocalDate birthDate,
			String city, String address) throws EmptyRegistrationFieldsException {
		int id_gender;

		
		if(!password1.equals(password2)) throw new EmptyRegistrationFieldsException("Passwords must match!");
		if(nickname.equals("")) throw new EmptyRegistrationFieldsException("nicknameField is empty");
		if(password2.equals("")) throw new EmptyRegistrationFieldsException("passwordField is empty");
		if(first_name.equals("")) throw new EmptyRegistrationFieldsException("firstNameField empty");
		if(last_name.equals("")) throw new EmptyRegistrationFieldsException("lastNameField empty");
		if(gender.equals("")) throw new EmptyRegistrationFieldsException("gender is not chosen");
		if(birthDate.equals("")) throw new EmptyRegistrationFieldsException("birthDate is empty");
		if(city.equals("")) throw new EmptyRegistrationFieldsException("cityField is empty");
		if(address.equals("")) throw new EmptyRegistrationFieldsException("addressField is empty");
		
		if (gender.equals("MALE"))
			id_gender = 1;
		else
			id_gender = 2;

		
		
		
		
		try {
			Connection connection;
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/NAis","postgres","admin");
			Statement stmt = connection.createStatement();
			
			ResultSet rset = stmt.executeQuery("select nickname from users");
			while(rset.next()) {
				String currentNickname = rset.getString("nickname");
				if (nickname.equals(currentNickname)) throw new EmptyRegistrationFieldsException("nickname already exists");
			}
			
			stmt.executeUpdate("insert into users (id_usertype, nickname, password, first_name, last_name, id_gender, date_of_birth, city, address) values (1,'" + nickname + "' , '" + password1 + "' , '" + first_name + "' , '" + last_name + "' , '" + id_gender + "' , '"+ birthDate +"' , '" + city + "' , '"+ address + "');");


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	

}
