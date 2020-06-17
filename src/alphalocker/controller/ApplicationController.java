package alphalocker.controller;
/*
 *   Created by Corentin on 17/06/2020 at 16:21
 */

import alphalocker.AlphaLocker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {

    @FXML
    public Text filigrane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filigrane.setText(filigrane.getText().replace("{version}", AlphaLocker.VERSION));
    }

    public void lock() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/alphalocker/view/lock.fxml"));
        Parent root = loader.load();
        stage.setTitle("AlphaLocker - Locker");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void unlock() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/alphalocker/view/unlock.fxml"));
        Parent root = loader.load();
        stage.setTitle("AlphaLocker - Unlocker");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

}
