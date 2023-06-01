import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.security.*;
import java.util.Arrays;
import javax.crypto.*;

public class Controller {

    @FXML
    private TextArea decMessageTA;

    @FXML
    private TextArea encMessageInTA;

    @FXML
    private TextArea encMessageOutTA;

    @FXML
    private TextArea messageTA;

    @FXML
    private TextField privateKeyGetTF;

    @FXML
    private TextField privateKeyShowTF;

    @FXML
    private TextField publicKeyGetTF;

    @FXML
    private TextField publicKeyShowTF;

    PublicKey publicKey;
    PrivateKey privateKey;
    Cipher cipher;
    byte[] encryptedBytes;

    public void initialize() throws NoSuchAlgorithmException, NoSuchPaddingException{
        cipher = Cipher.getInstance("RSA");
    }

    @FXML
    void decryptBtn(ActionEvent event) {
        // Decryption
        try{
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String decryptedMessage = new String(decryptedBytes);
            decMessageTA.setText(decryptedMessage);
        }catch(Exception e){}

    }

    @FXML
    void encryptBtn(ActionEvent event) {
        String message = messageTA.getText();
        // Encryption
        try{
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encryptedBytes = cipher.doFinal(message.getBytes());
            String encryptedMessage = new String(Arrays.toString(encryptedBytes));
            encMessageOutTA.setText(encryptedMessage);
        }catch(Exception e){}

    }

    @FXML
    void generate(ActionEvent event) throws NoSuchAlgorithmException {
        // Generate public and private keys
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();

        publicKeyShowTF.setText(publicKey.toString());
        privateKeyShowTF.setText(privateKey.toString());
    }

}

