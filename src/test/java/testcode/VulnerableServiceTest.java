package testcode;

import JavaCodeCoverage.VulnerableService;
import org.junit.Assert;
import org.junit.Test;

public class VulnerableServiceTest {

    @Test
    public void testBuildSqlQuery_containsUsernameInQuery() {
        VulnerableService service = new VulnerableService();
        String query = service.buildSqlQuery("admin");
        Assert.assertEquals("SELECT * FROM users WHERE username = 'admin'", query);
    }

    @Test
    public void testHashWithMd5_returnsDeterministicHash() {
        VulnerableService service = new VulnerableService();
        String hash1 = service.hashWithMd5("password");
        String hash2 = service.hashWithMd5("password");
        Assert.assertEquals(hash1, hash2);
        Assert.assertNotEquals(hash1, service.hashWithMd5("password1"));
    }

    @Test
    public void testGetHardcodedPassword_returnsSecret() {
        VulnerableService service = new VulnerableService();
        Assert.assertEquals("SuperSecret123!", service.getHardcodedPassword());
    }
}
