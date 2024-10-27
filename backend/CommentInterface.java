package backend;

public interface CommentInterface {
	public int getId();
	public int getUserId();
	public int getPostId();
	public String getText();
	public void setId(int id);
}
