package LoginDemo;

public class TwoFactorAuth {

	private String id;

	private String _2FA;

	public TwoFactorAuth(String id, String _2FA) {
		this.id = id;
		this._2FA = _2FA;
	}

	public String getId() {
		return id;
	}

	public String get_2FA() {
		return _2FA;
	}

}
