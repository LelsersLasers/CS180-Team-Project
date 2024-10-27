package backend;

public class Post implements PostInterface {
	private int id;
	private int userId;
	private String text;
	private String picture;
	private String date;

	public Post(int userId, String text, String picture, String date) {
		this.id = -1;
		this.userId = userId;
		this.text = text;
		this.picture = picture;
		this.date = date;
	}

	public Post(int id, int userId, String text, String picture, String date) {
		this.id = id;
		this.userId = userId;
		this.text = text;
		this.picture = picture;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public String getText() {
		return text;
	}

	public String getPicture() {
		return picture;
	}

	public String getDate() {
		return date;
	}

	public void setId(int id) {
		this.id = id;
	}
}
