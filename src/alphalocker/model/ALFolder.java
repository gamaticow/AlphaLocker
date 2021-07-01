package alphalocker.model;
/*
 *   Created by Corentin on 09/07/2020 at 23:22
 */

import javax.crypto.Cipher;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ALFolder extends ALElement {

    private List<ALElement> content = new ArrayList<>();

    private ALFolder(File folder){
        name = folder.getName().getBytes();
        try {
            fill(folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lock(String secret) throws Exception {
        if(!new String(validator).equals(VALIDATOR))
            return;

        for (ALElement element : content)
            element.lock(secret);

        Cipher cipher = getCipher(secret, Cipher.ENCRYPT_MODE);

        name = cipher.doFinal(name);
        validator = cipher.doFinal(validator);
    }

    @Override
    public boolean unlock(String secret) throws Exception {
        if(new String(validator).equals(VALIDATOR))
            return true;

        Cipher cipher = getCipher(secret, Cipher.DECRYPT_MODE);

        byte[] v = cipher.doFinal(validator);
        if(!new String(v).equals(VALIDATOR))
            return false;

        for(ALElement element : content)
            element.unlock(secret);

        validator = v;
        name = cipher.doFinal(name);
        return true;
    }

    private void fill(File folder) throws IOException {
        if(!folder.isDirectory())
            return;

        for(File file : folder.listFiles()){
            if(file == null)
                continue;

            if(file.isDirectory())
                content.add(new ALFolder(file));
            else if(file.isFile())
                content.add(new ALFile(file));
        }
    }

    public void paste(File file) throws Exception{
        if(!new String(validator).equals(VALIDATOR))
            return;

        File folder = new File(file.getAbsolutePath() + File.separator + new String(name));
        folder.mkdir();

        for(ALElement element : content)
            element.paste(folder);
    }

    public static ALFolder getFrom(File folder){
        if(!folder.isDirectory())
            return null;

        return new ALFolder(folder);
    }



}
