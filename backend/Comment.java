package backend;

public class Comment implements CommentInterface {
	private int id;
	private int userId;
	private int postId;
	private String text;

	public Comment(int userId, int postId, String text) {
		this.id = -1;
		this.userId = userId;
		this.postId = postId;
		this.text = text;
	}

	public Comment(int id, int userId, int postId, String text) {
		this.id = id;
		this.userId = userId;
		this.postId = postId;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public int getPostId() {
		return postId;
	}

	public String getText() {
		return text;
	}

	public void setId(int id) {
		this.id = id;
	}
}
