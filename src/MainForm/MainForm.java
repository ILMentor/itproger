package MainForm;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;

public class MainForm {

   public MainForm(Stage primaryStage){
       primaryStage.setTitle("Wallet");
       primaryStage.setResizable(false);
       Rectangle2D graphicsDevice = Screen.getPrimary().getBounds();

       primaryStage.setScene(new Scene(new BorderPane(),graphicsDevice.getWidth(),
               graphicsDevice.getHeight()));

       primaryStage.show();
   }
}
