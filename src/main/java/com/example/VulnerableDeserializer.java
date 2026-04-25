package com.example;

import java.io.ObjectInputStream;
import java.io.InputStream;

public class VulnerableDeserializer {

    public Object deserialize(InputStream input) throws Exception {
        // Vulnerable: using ObjectInputStream on untrusted data
        ObjectInputStream in = new ObjectInputStream(input);
        Object obj = in.readObject(); // unsafe
        return obj;
    }
}
