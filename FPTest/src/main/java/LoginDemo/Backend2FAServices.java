package LoginDemo;

import java.util.HashMap;
import java.util.Optional;

public class Backend2FAServices {

	HashMap<String, TwoFactorAuth> validCompany2FA = new HashMap<>();
	// HashMap<String, LoginDemo.TwoFactorAuth> validGmailProfiles = new HashMap<>();

	public Optional<TwoFactorAuth> getCompanyProfileByID(String id) {

		if (validCompany2FA.containsKey(id)){
			return Optional.of(validCompany2FA.get(id));
		}			
		return Optional.empty();
	}

	/*
	 * public Optional<LoginDemo.TwoFactorAuth> getGmailProfileByID(String id) {
	 * 
	 * if(validGmailProfiles.containsKey(id)) return
	 * Optional.of(validGmailProfiles.get(id)); return Optional.empty(); }
	 */

}
