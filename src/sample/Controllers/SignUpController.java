package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Database.DatabaseHandler;
import sample.Database.User;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField userName_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField lastname_field;

    @FXML
    private TextField name_field;

    @FXML
    private TextField country_field;

    @FXML
    private CheckBox signUpMaleCheckBox;

    @FXML
    private CheckBox signUpFemaleCheckBox;

    @FXML
    void initialize() {

        signUpButton.setOnAction(actionEvent -> signUpNewUser());

    }

    private void signUpNewUser() {

        DatabaseHandler databaseHandler = new DatabaseHandler();

        String firstName = name_field.getText();
        String lastName = lastname_field.getText();
        String userName = userName_field.getText();
        String password = password_field.getText();
        String location = country_field.getText();
        String gender;

        if(signUpMaleCheckBox.isSelected())
            gender = "Male";
        else
            gender = "Female";

        User user = new User(firstName, lastName, userName, password, location, gender);
        databaseHandler.signUpUser(user);
    }
}

