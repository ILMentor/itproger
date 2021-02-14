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
        DatabaseHandler databaseHandler = new DatabaseHandler();

        signUpButton.setOnAction(actionEvent -> {
            databaseHandler.signUpUser(name_field.getText(), lastname_field.getText(), userName_field.getText(),
                    password_field.getText(), country_field.getText(), "Male");
        });
    }
}

