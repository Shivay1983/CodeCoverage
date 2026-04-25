package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;

public class VulnerableController {

    private Connection connection;

    public VulnerableController(Connection connection) {
        this.connection = connection;
    }

    public void handleRequest(HttpServletRequest request) throws Exception {
        // Vulnerable: concatenating user input directly into SQL
        String userInput = request.getParameter("id");
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(
            "SELECT * FROM users WHERE id = '" + userInput + "'"
        );

        // process rs...
    }
}
