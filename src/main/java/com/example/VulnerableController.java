// Vulnerable: concatenating user input directly into SQL
String userInput = request.getParameter("id");
Statement stmt = connection.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id = '" + userInput + "'");
