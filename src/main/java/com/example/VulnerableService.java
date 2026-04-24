// Vulnerable: hardcoding credentials
String dbUser = "admin";
String dbPassword = "SuperSecret123!";
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", dbUser, dbPassword);
