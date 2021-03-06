package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.Database.User;
import sample.animation.Shake;

public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    FXMLLoader loader = new FXMLLoader();
    Parent root;
    Stage stage = new Stage();

    @FXML
    void initialize() {

        signInButton.setOnAction(actionEvent -> {
            String loginText = login_field.getText().trim();
            String passwordText = password_field.getText().trim();

            if(!loginText.equals("") && !passwordText.equals(""))
                loginUser(loginText, passwordText);
            else
                System.out.println("Login and password are empty.");
        });

        signUpButton.setOnAction(actionEvent -> {
            signUpButton.getScene().getWindow().hide();

            loader.setLocation(getClass().getResource("/sample/Views/signUp.fxml"));

            try {
                loader.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    private void loginUser(String loginText, String passwordText) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(passwordText);
        ResultSet resultSet = databaseHandler.getUser(user);

        int counter = 0;

        while(true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            counter++;
        }

        if(counter >= 1) {

            signUpButton.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("/sample/Views/app.fxml"));
            try {
                loader.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }
        else {
            Shake userLoginAnimation = new Shake(login_field);
            Shake userPasswordAnimation = new Shake(password_field);
            userLoginAnimation.playAnimation();
            userPasswordAnimation.playAnimation();
        }
    }
}