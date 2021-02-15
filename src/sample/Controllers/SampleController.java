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

    @FXML
    void initialize() {

        signInButton.setOnAction(actionEvent -> {
            String loginText = login_field.getText().trim();
            String passwordText = password_field.getText().trim();

            if(!loginText.equals("") && !passwordText.equals(""))
                loginUser(loginText, passwordText);
            else
                // todo Сделать вывод этого сообщения не в консоль, а на форму.
                System.out.println("Login and password are empty.");
        });

        signUpButton.setOnAction(actionEvent -> {
            signUpButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/Views/signUp.fxml"));

            try {
                loader.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
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

        if(counter >= 1)
            System.out.println("Success.");
    }
}