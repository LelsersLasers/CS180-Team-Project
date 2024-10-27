package backend;

public class User implements UserInterface {
	private int id;
	private String username;
	private String password;
	private String picture;

	public User(String username, String password, String picture) {
		this.id = -1;
		this.username = username;
		this.password = password;
		this.picture = picture;
	}

	public User(int id, String username, String password, String picture) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getPicture() {
		return picture;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}