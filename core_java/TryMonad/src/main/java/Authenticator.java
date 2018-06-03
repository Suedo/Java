public class Authenticator {

    public User login(String id, String pwd) throws LoginFailedException {

        // throw new Exception("password mismatch");
        return new User(id, "Somjit");

    }

    public User gmailLogin(String id, String pwd) throws Exception {

        // throw new IOException("password mismatch");
        return new User(id, "Somjit");

    }

    public User twoFactor(User user, long pwd) throws TwoFactorFailedException {

        // throw new RuntimeException("two factor incorrect key");
        return user;

    }


}
