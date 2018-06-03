import io.vavr.control.Try;

import java.net.URL;

public class MonadTest {

    public static void main(String[] args) {

        final String dashboard = "http://dashboard";
        final String loginPage = "http://login";

        final String userid = "E278WZ";
        final String pwd = "1234";
        final long _2FA = 112233;

        final Authenticator authenticator = new Authenticator();

        /*
        * This relaxes us from being concerned with the specifics of what exception is thrown
        * All we worry about is whether or not exceptions are thrown, and our flow changes accordingly
        */
        String target = Try.of(() -> authenticator.login(userid, pwd))
            .recoverWith(e -> Try.of(() -> authenticator.gmailLogin(userid, pwd)))
            .flatMap(user1 -> Try.of(() -> {
                authenticator.twoFactor(user1, _2FA);
                return dashboard;
            }))
            .getOrElse(loginPage);

        System.out.println(target);

    }

}
