package alphalocker.model;
/*
 *   Created by Corentin on 17/06/2020 at 16:55
 */

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;

public class ALFile implements Serializable {

    private static final String VALIDATOR = "AlphaLocker";

    private byte[] name;
    private byte[] content;
    private byte[] validator = VALIDATOR.getBytes();

    public ALFile(File file) throws IOException {
        name = file.getName().getBytes();
        content = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
    }

    public void lock(String secret) throws Exception {
        if(!new String(validator).equals(VALIDATOR))
            return;
        Key key = new SecretKeySpec(secret.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        name = cipher.doFinal(name);
        content = cipher.doFinal(content);
        validator = cipher.doFinal(validator);
    }

    public boolean unlock(String secret) throws Exception{
        if(new String(validator).equals(VALIDATOR))
            return true;

        Key key = new SecretKeySpec(secret.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] v = cipher.doFinal(validator);
        if(!new String(v).equals(VALIDATOR))
            return false;

        validator = v;
        content = cipher.doFinal(content);
        name = cipher.doFinal(name);
        return true;
    }

    public String getName(){
        if(new String(validator).equals(VALIDATOR))
            return new String(name);
        return null;
    }

    public byte[] getContent(){
        if(new String(validator).equals(VALIDATOR))
            return content;

        return null;
    }

}
