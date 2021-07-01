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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {

    @FXML
    public AnchorPane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(pane == null) {
            System.exit(0);
            return;
        }
        Text filigrane = new Text("AlphaLocker " + AlphaLocker.VERSION + " by Gamaticow");
        Font font = new Font(filigrane.getFont().getFamily(), 10.0);
        filigrane.setFont(font);
        AnchorPane.setBottomAnchor(filigrane, 0.0);
        AnchorPane.setRightAnchor(filigrane, 0.0);
        pane.getChildren().add(filigrane);
    }

    public void lockFile() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/alphalocker/view/lock_file.fxml"));
        Parent root = loader.load();
        stage.setTitle("AlphaLocker - Locker");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void lockFolder() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/alphalocker/view/lock_folder.fxml"));
        Parent root = loader.load();
        stage.setTitle("AlphaLocker - Locker");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void unlockFile() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/alphalocker/view/unlock_file.fxml"));
        Parent root = loader.load();
        stage.setTitle("AlphaLocker - Unlocker");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void unlockFolder() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/alphalocker/view/unlock_folder.fxml"));
        Parent root = loader.load();
        stage.setTitle("AlphaLocker - Unlocker");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

}
