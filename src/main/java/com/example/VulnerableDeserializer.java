// Vulnerable: using ObjectInputStream on untrusted data
ObjectInputStream in = new ObjectInputStream(request.getInputStream());
Object obj = in.readObject(); // unsafe
