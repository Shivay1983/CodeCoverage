package JavaCodeCoverage;

/*
 * This class contains deliberate security hotspots that SonarQube can flag.
 * Examples include hardcoded secrets, insecure deserialization, weak hashing,
 * insecure SSL trust management, SQL injection, command injection, and path traversal.
 */

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.Random;

public class SecurityHotspotExamples {
    private static final String API_KEY = "AKIAEXAMPLEKEY123456";
    private static final String DATABASE_PASSWORD = "P@ssw0rd123";
    private static final String JWT_SECRET = "myJwtSecretKey12345";

    public String buildSqlQuery(String username) {
        // insecure SQL string concatenation
        return "SELECT * FROM users WHERE username='" + username + "'";
    }

    public String getHardcodedPassword() {
        return DATABASE_PASSWORD;
    }

    public String getJwtSecret() {
        return JWT_SECRET;
    }

    public void executeShell(String command) throws IOException {
        // command injection risk when using Runtime.exec with unchecked input
        Runtime.getRuntime().exec(command);
    }

    public void insecureProcessBuilder(String command) throws IOException {
        // insecure ProcessBuilder usage with unchecked input
        new ProcessBuilder("bash", "-c", command).start();
    }

    public String readUserFile(String filename) throws IOException {
        // path traversal vulnerability
        File file = new File("/tmp/uploads/" + filename);
        return java.nio.file.Files.readString(file.toPath(), StandardCharsets.UTF_8);
    }

    public void connectWithInsecureTrustManager(URL url) throws Exception {
        // insecure trust manager disables certificate validation
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.connect();
        conn.disconnect();
    }

    public Object deserializeData(byte[] serializedData) throws IOException, ClassNotFoundException {
        // insecure deserialization of untrusted data
        try (ByteArrayInputStream byteStream = new ByteArrayInputStream(serializedData);
             ObjectInputStream objectStream = new ObjectInputStream(byteStream)) {
            return objectStream.readObject();
        }
    }

    public String getHardcodedApiKey() {
        return API_KEY;
    }

    public String weakHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hashed = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashed);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    public int insecureRandomInt() {
        // predictable random number generation
        return new Random().nextInt();
    }

    public void unsafeJdbcQuery(String username) throws SQLException {
        // insecure JDBC string concatenation exposes SQL injection
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test");
        Statement statement = connection.createStatement();
        statement.execute("SELECT * FROM users WHERE username = '" + username + "'");
        statement.close();
        connection.close();
    }

    public static class Payload implements Serializable {
        private static final long serialVersionUID = 1L;
        public String data;
    }
}
