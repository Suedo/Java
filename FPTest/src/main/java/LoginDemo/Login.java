package LoginDemo;

import java.io.IOException;
import java.util.Optional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Login {

	private static String SUCCESS = "login successful, redirecting to https://company.com/";
	private static String FAILURE = "login failed";
	
	private static Optional<Profile> loginWithCompanyDetails(LoginRequest req){
		return Optional.empty();
	}
	
	private static Optional<Profile> validateWith2FA(Profile profile, String _2FA){
		return Optional.empty();
	}
	
	private static Optional<Profile> loginWithPersonalDetails(LoginRequest req){
		return Optional.empty();
	}
	
	public static Optional<Profile> login(LoginRequest req){
		
		// https://confengine.com/functional-conf-2015/proposal/1459/drying-to-monads-in-java8
		// http://www.deadcoderising.com/2015-10-06-java-8-removing-null-checks-with-optional/
		
		return loginWithCompanyDetails(req).flatMap(e -> validateWith2FA(e, req.get_2FA()));


				
	}
	
	public static String redirect(Optional<Profile> profile){
		return profile.map(p -> String.format("%s%s", SUCCESS, p.getId())).orElse(FAILURE);
	}
	
	
	
	

	public static void main(String[] args) throws IOException {

		Path loginsPath = Paths.get("logins.txt");
		
		Files.lines(loginsPath)
                .map(LoginRequest::new)
                .map(Login::login)	// Stream<Optional<LoginDemo.Profile>>
//			.flatMap(e -> e.map(Stream::of).orElseGet(Stream::empty))		// Stream<LoginDemo.Profile>
			.map(Login::redirect)											// Stream<String>
			.forEach(System.out::println);

	}

}
