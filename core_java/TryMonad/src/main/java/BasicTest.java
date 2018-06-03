import java.io.IOException;
import java.net.URL;

public class BasicTest {

    public static void main(String[] args) throws Exception {

        final URL dashboard = new URL("http://dashboard");  // success
        final URL loginPage = new URL("http://login");      // failure

        final String userid = "E278WZ";
        final String pwd = "1234";
        User user = null;
        URL target;

        long twofactorPassword = 112233;

        Authenticator authenticator = new Authenticator();

     /*
         valid login(company details) ?
           : ( valid 2FA ? success : failure )
           ? ( valid backup creds ? ( valid 2FA ? success : failure ) : failure )

     */
        try {
            user = authenticator.login(userid, pwd);
            authenticator.twoFactor(user, twofactorPassword);
            target = dashboard;
        }
        catch (LoginFailedException e1) {

            try {
                user = authenticator.gmailLogin(userid, pwd);
                authenticator.twoFactor(user, twofactorPassword);
                target = dashboard;

            } catch (BackupLoginFailedException|TwoFactorFailedException e2) {
                target = loginPage;
            }

        }catch (TwoFactorFailedException e3) {
            target = loginPage;
        }


        System.out.println(user.getName() + " being redirecting to: " + target);

    }
}
