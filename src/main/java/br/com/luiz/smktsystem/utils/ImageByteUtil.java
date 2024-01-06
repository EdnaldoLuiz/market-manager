package br.com.luiz.smktsystem.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageByteUtil {

    public static byte[] encode(String imagePath) throws IOException {
        return Files.readAllBytes(Paths.get(imagePath));
    }

    public static String decode(byte[] imageBytes) {
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
