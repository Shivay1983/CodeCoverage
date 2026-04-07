package testcode;

import JavaCodeCoverage.JavaCodeCoverageClass;
import org.junit.Test;

public class TestCodeJava {

    @Test
    public void substractint(){
        JavaCodeCoverageClass a= new JavaCodeCoverageClass();
        int c=a.substract(10,20);
        System.out.println(c);
    }

    @Test
    public void multiplyint(){
        JavaCodeCoverageClass a= new JavaCodeCoverageClass();
        int d= a.multiply(10, 20);
        System.out.println(d);
    }
}
