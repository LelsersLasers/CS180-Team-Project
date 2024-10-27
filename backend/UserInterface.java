package backend;

public interface UserInterface {
	public int getId();
	public String getUsername();
	public String getPassword();
	public String getPicture();
	public void setId(int id);
	public void setUsername(String username);
	public void setPicture(String picture);
}
