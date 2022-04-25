package com.example.rit_library;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static void changeScene(String sceneName) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(HelloApplication.class.getResource(sceneName));
        Scene scene = new Scene(fxmlLoader, 600, 600);
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();
    }

    public static String generateHash(String string) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(StandardCharsets.UTF_8.encode(string));
        return String.format("%032x", new BigInteger(1, md5.digest()));
    }
}
