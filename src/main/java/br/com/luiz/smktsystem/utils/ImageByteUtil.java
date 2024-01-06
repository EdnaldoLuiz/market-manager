package br.com.luiz.smktsystem.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageByteUtil {

    public static String encode(String imagePath) throws IOException {
        byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public static byte[] decode(String base64Image) {
        return Base64.getDecoder().decode(base64Image);
    }
}
