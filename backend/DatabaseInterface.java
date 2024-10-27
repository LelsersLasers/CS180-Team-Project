package backend;

import java.util.ArrayList; 

public interface DatabaseInterface {
	public final static String USERS_FILE = "database/users";
	public final static String POSTS_FILE = "database/posts";
	public final static String COMMENTS_FILE = "database/comments";
	public final static String FRIENDSHIP_FILE = "database/friendship";
	public final static String BLOCKED_FILE = "database/blocked";
	public final static String POST_VOTE_FILE = "database/post_vote";
	public final static String COMMENT_VOTE_FILE = "database/comment_vote";
	

	public void saveUser(User user);
	public User getUser(int id);
	public User getUser(String username);
	public User tryLogin(String username, String password);

	public void savePost(Post post);
	public Post getPost(int id);
	public ArrayList<Post> getPosts(int userId);
	public ArrayList<Post> getFeed(int userId);
	public void deletePost(int id);
}
