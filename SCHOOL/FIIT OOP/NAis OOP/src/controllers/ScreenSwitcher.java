package controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import users.*;

public interface ScreenSwitcher {
	public default void switchToLoginScreen(Button button) {
		Stage stage = (Stage) button.getScene().getWindow();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Login.fxml"));
			Parent root = loader.load();
			LoginController controller = loader.getController();

			Scene scene = new Scene(root);
			scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
				if(key.getCode()==KeyCode.ENTER) {
					controller.loginButtonAction();
				}
			});
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public default void switchToRegistrationScreen(Button button) {
		
		Stage stage = (Stage) button.getScene().getWindow();
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RegistrationScreen.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			RegistrationController controller = loader.getController();
			scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
				if(key.getCode()==KeyCode.ENTER) {
					controller.registerButtonAction();
				}
			});				
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	public default void switchToForumScreen(Button button, User activeUser) {
		Stage stage = (Stage) button.getScene().getWindow();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ForumScreen.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			ForumController controller = loader.getController();
			scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
				if(key.getCode()==KeyCode.ENTER) {
					controller.sendButtonAction();
				}
				if(key.getCode()==KeyCode.ESCAPE) {
					controller.backButtonAction();
				}
			});
			controller.setActiveUser(activeUser);
			
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
