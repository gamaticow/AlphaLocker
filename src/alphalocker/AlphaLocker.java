package alphalocker;
/*
 *   Created by Corentin on 17/06/2020 at 16:17
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AlphaLocker extends Application {

    public static final String VERSION = "v1.0";

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/alphalocker/view/app.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("AlphaLocker - Gamaticow");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> System.exit(0));
    }

}
