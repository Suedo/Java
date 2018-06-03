package LoginDemo;

public class Profile {

	private String id;

	private String password;

	public Profile(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	public String getId() {
		return id;
	}
}
