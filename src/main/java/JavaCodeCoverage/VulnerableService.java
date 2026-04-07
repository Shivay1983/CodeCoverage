package JavaCodeCoverage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class VulnerableService {
    private static final String DATABASE_PASSWORD = "SuperSecret123!";

    public String buildSqlQuery(String username) {
        // unsafe string concatenation enables SQL injection
        return "SELECT * FROM users WHERE username = '" + username + "'";
    }

    public void executeShellCommand(String userInput) throws IOException {
        // insecure use of Runtime.exec with user-controlled data
        Runtime.getRuntime().exec("ping " + userInput);
    }

    public String readUploadFile(String filename) throws IOException {
        // path traversal risk if filename contains ../ sequences
        File file = new File("uploads/" + filename);
        return Files.readString(file.toPath(), StandardCharsets.UTF_8);
    }

    public String hashWithMd5(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hashed = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5 algorithm unavailable", e);
        }
    }

    public String getHardcodedPassword() {
        return DATABASE_PASSWORD;
    }
}
