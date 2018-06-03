package LoginDemo;

import java.util.HashMap;
import java.util.Optional;

public class BackendProfileServices {

	HashMap<String, Profile> validCompanyProfiles = new HashMap<>();
	HashMap<String, Profile> validGmailProfiles = new HashMap<>();

	public Optional<Profile> getCompanyProfileByID(String id) {
		// return validCompanyProfiles.get(id); // returns null if ID not present
		
		if(validCompanyProfiles.containsKey(id)) return Optional.of(validCompanyProfiles.get(id));
		return Optional.empty();
	}
	
	public Optional<Profile> getGmailProfileByID(String id) {
		
		if(validGmailProfiles.containsKey(id)) return Optional.of(validGmailProfiles.get(id));
		return Optional.empty();
	}
	
}
