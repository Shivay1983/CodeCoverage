package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class VulnerableService {

    private Connection connection;

    public VulnerableService(Connection connection) {
        this.connection = connection;
    }

    public void run(String userInput) throws Exception {
        // Vulnerable: concatenating user input directly into SQL
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(
            "SELECT * FROM users WHERE id = '" + userInput + "'"
        );

        // process rs...
    }
}
