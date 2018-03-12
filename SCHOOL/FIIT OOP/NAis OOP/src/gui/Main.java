package gui;
	
import java.sql.*;

import controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml")); //set fxml loader
			Parent root = loader.load(); //load fxml file
			LoginController controller = loader.getController();
		
			Scene scene = new Scene(root);
			scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
				if(key.getCode()==KeyCode.ENTER) {
					controller.loginButtonAction();
				}
			});
			primaryStage.setScene(scene);
			primaryStage.setTitle("NAis");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		launch(args);
		
	}
}
