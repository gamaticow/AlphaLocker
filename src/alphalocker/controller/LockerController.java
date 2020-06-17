package alphalocker.controller;
/*
 *   Created by Corentin on 17/06/2020 at 16:38
 */

import alphalocker.model.ALFile;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class LockerController {

    @FXML
    public TextField fileField;
    public PasswordField secretField;

    public void search(){
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose file to lock");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("All files", "*.*"));
        fileField.setText(fc.showOpenDialog(null).getAbsolutePath());
    }

    public void lock() throws Exception {
        File file = new File(fileField.getText());
        if(!file.exists())
            return;

        if(secretField.getText().length() < 5)
            return;

        File folder = file.getParentFile();
        ALFile ciphered = new ALFile(file);
        ciphered.lock(secretField.getText());

        long i = 1;
        File out;
        do{
            out = new File(folder.getAbsolutePath() + File.separator + i + ".al");
            i++;
        }while (out.exists());

        FileOutputStream fos = new FileOutputStream(out);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(ciphered);
        oos.close();
        fos.close();

        ((Stage) fileField.getScene().getWindow()).close();
    }

}
