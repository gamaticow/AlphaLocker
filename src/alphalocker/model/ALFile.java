package alphalocker.model;
/*
 *   Created by Corentin on 17/06/2020 at 16:55
 */

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

public class ALFile extends ALElement {

    private byte[] content;

    public ALFile(File file) throws IOException {
        name = file.getName().getBytes();
        content = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
    }

    public void lock(String secret) throws Exception {
        if(!new String(validator).equals(VALIDATOR))
            return;
        Cipher cipher = getCipher(secret, Cipher.ENCRYPT_MODE);

        name = cipher.doFinal(name);
        content = cipher.doFinal(content);
        validator = cipher.doFinal(validator);

        content = compress(content);
    }

    public boolean unlock(String secret) throws Exception{
        if(new String(validator).equals(VALIDATOR))
            return true;

        Cipher cipher = getCipher(secret, Cipher.DECRYPT_MODE);

        byte[] v = cipher.doFinal(validator);
        if(!new String(v).equals(VALIDATOR))
            return false;

        validator = v;
        content = cipher.doFinal(decompress(content));
        name = cipher.doFinal(name);
        return true;
    }

    public void paste(File file) throws Exception{
        if(!new String(validator).equals(VALIDATOR))
            return;

        File out = new File(file.getAbsolutePath() + File.separator + getName());
        OutputStream os = new FileOutputStream(out.getAbsolutePath());
        os.write(getContent());
        os.close();
    }

    private String getName(){
        if(new String(validator).equals(VALIDATOR))
            return new String(name);
        return null;
    }

    private byte[] getContent(){
        if(new String(validator).equals(VALIDATOR))
            return content;

        return null;
    }

    public static byte[] compress(byte[] in) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            DeflaterOutputStream defl = new DeflaterOutputStream(out);
            defl.write(in);
            defl.flush();
            defl.close();

            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(150);
            return null;
        }
    }

    public static byte[] decompress(byte[] in) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InflaterOutputStream infl = new InflaterOutputStream(out);
            infl.write(in);
            infl.flush();
            infl.close();

            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(150);
            return null;
        }
    }

}
