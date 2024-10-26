package backend;

public interface DatabaseInterface {
	public final static String USERS_FILE = "database/users.txt";
	public final static String POSTS_FILE = "database/posts.txt";
	public final static String COMMENTS_FILE = "database/comments.txt";
	public final static String FRIENDSHIP_FILE = "database/friendship.txt";
	public final static String BLOCKED_FILE = "database/blocked.txt";
	public final static String POST_VOTE_FILE = "database/post_vote.txt";
	public final static String COMMENT_VOTE_FILE = "database/comment_vote.txt";
	

	public void saveUser(User user);
	public User getUser(int id);
	public User getUser(String username);
	public User tryLogin(String username, String password);

}
