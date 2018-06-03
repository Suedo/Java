package LoginDemo;

public class LoginRequest {

	// Optional<T> is optional for this particular use case :)

	private String id;
	private String CompanyPassword;
	private String _2FA;
	private String PersonalEmail;
	private String PersonalEmailPassword;
	private boolean containsCompanyCredentials;
	private boolean containsPersonalCredentials;

	public LoginRequest(String s) {
		String[] a = s.split(",");
		assert a.length == 5 : "LoginDemo.Login request should have 5 params";

		this.id = a[0];
		this.CompanyPassword = a[1];
		this.containsCompanyCredentials = (this.id.length() > 0) && (this.CompanyPassword.length() > 0);
		this._2FA = a[2];
		this.PersonalEmail = a[3];
		this.PersonalEmailPassword = a[4];
		this.containsPersonalCredentials = (this.PersonalEmail.length() > 0) && (this.PersonalEmailPassword.length() > 0);

	}

	public String getId() {
		return id;
	}

	public String getCompanyPassword() {
		return CompanyPassword;
	}

	public String get_2FA() {
		return _2FA;
	}

	public String getPersonalEmail() {
		return PersonalEmail;
	}

	public String getPersonalEmailPassword() {
		return PersonalEmailPassword;
	}

	public boolean containsCompanyCredentials() {
		return containsCompanyCredentials;
	}

	public boolean containsPersonalCredentials() {
		return containsPersonalCredentials;
	}

}
