package testcode;

import JavaCodeCoverage.SecurityHotspotExamples;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;

public class SecurityHotspotExamplesTest {

    @Test
    public void testBuildSqlQuery_containsUsername() {
        SecurityHotspotExamples examples = new SecurityHotspotExamples();
        String query = examples.buildSqlQuery("admin");
        Assert.assertEquals("SELECT * FROM users WHERE username='admin'", query);
    }

    @Test
    public void testHardcodedApiKey_isReturned() {
        SecurityHotspotExamples examples = new SecurityHotspotExamples();
        Assert.assertTrue(examples.getHardcodedApiKey().contains("AKIA"));
    }

    @Test
    public void testHardcodedPassword_isReturned() {
        SecurityHotspotExamples examples = new SecurityHotspotExamples();
        Assert.assertEquals("P@ssw0rd123", examples.getHardcodedPassword());
    }

    @Test
    public void testJwtSecret_isReturned() {
        SecurityHotspotExamples examples = new SecurityHotspotExamples();
        Assert.assertEquals("myJwtSecretKey12345", examples.getJwtSecret());
    }

    @Test
    public void testWeakHash_isDeterministic() {
        SecurityHotspotExamples examples = new SecurityHotspotExamples();
        String hash1 = examples.weakHash("secret");
        String hash2 = examples.weakHash("secret");
        Assert.assertEquals(hash1, hash2);
    }

    @Test
    public void testDeserializeData_roundTrip() throws IOException, ClassNotFoundException {
        SecurityHotspotExamples examples = new SecurityHotspotExamples();
        SecurityHotspotExamples.Payload payload = new SecurityHotspotExamples.Payload();
        payload.data = "test";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(payload);
        }

        Object deserialized = examples.deserializeData(baos.toByteArray());
        Assert.assertNotNull(deserialized);
        Assert.assertTrue(deserialized instanceof SecurityHotspotExamples.Payload);
    }

    @Test
    public void testInsecureRandomInt_returnsValue() {
        SecurityHotspotExamples examples = new SecurityHotspotExamples();
        int value = examples.insecureRandomInt();
        Assert.assertNotNull(value);
    }
}
