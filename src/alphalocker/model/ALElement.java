package alphalocker.model;
/*
 *   Created by Corentin on 09/07/2020 at 23:22
 */

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.Serializable;
import java.security.Key;

public abstract class ALElement implements Serializable {

    protected static final String VALIDATOR = "AlphaLocker";

    protected byte[] name;
    protected byte[] validator = VALIDATOR.getBytes();

    public abstract void lock(String secret) throws Exception;

    public abstract boolean unlock(String secret) throws Exception;

    public abstract void paste(File file) throws Exception;

    protected Cipher getCipher(String secret, int encryptMode) throws Exception {
        Key key = new SecretKeySpec(secret.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher;
    }

}
