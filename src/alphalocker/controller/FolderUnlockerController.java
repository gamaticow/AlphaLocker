package alphalocker.controller;
/*
 *   Created by Corentin on 17/06/2020 at 17:26
 */

import alphalocker.model.ALFile;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class FolderUnlockerController {

    @FXML
    public TextField fileField;
    public PasswordField secretField;

    public void search(){
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose file to unlock");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("AlphaLocker files", "*.al"));
        fileField.setText(fc.showOpenDialog(null).getAbsolutePath());
    }

    public void unlock() throws Exception {
        /*File file = new File(fileField.getText());
        if(!file.exists())
            return;

        if(secretField.getText().length() < 5)
            return;

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object o = ois.readObject();
        if(!(o instanceof ALFile))
            return;

        ALFile ciphered = (ALFile) o;
        if(!ciphered.unlock(secretField.getText()))
            return;

        File out = new File(file.getParent() + File.separator + ciphered.getName());
        OutputStream os = new FileOutputStream(out.getAbsolutePath());
        os.write(ciphered.getContent());
        os.close();

        ((Stage) fileField.getScene().getWindow()).close();*/
    }

}
