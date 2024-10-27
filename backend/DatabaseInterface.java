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
	
	/**
	 * Saves user to file. If the user has no id, it will be assigned one.
	 * If the user has an id/already exists in the file, it will be updated.
	 * 
	 * @param user User to save
	public void saveUser(User user);

	/**
	 * Gets user by id
	 * 
	 * @param id Id of user
	 * @return User with the id
	public User getUser(int id);

	/**
	 * Gets user by username
	 * 
	 * @param username Username of user
	 * @return User with the username
	public User getUser(String username);

	/**
	 * Tries to login with the given username and password
	 * 
	 * @param username Username to login with
	 * @param password Password to login with
	 * @return User if login was successful, null otherwise
	public User tryLogin(String username, String password);

	public void savePost(Post post);
	public Post getPost(int id);
	public ArrayList<Post> getPosts(int userId);
	public ArrayList<Post> getFeed(int userId);
	public void deletePost(int id);

	public void saveComment(Comment comment);
	public Comment getComment(int id);
	public ArrayList<Comment> getComments(int postId);
	public void deleteComment(int id);

	public void saveFriendship(Friendship friendship);
	public Friendship getFriendship(int id);
	public ArrayList<User> getFriends(int userId);
	public void deleteFriendship(int id);

	public void saveBlocked(Blocked blocked);
	public Blocked getBlocked(int id);
	public ArrayList<User> getBlockedUsers(int userId);
	public void deleteBlocked(int id);

	// public void savePostVote(PostVote postVote);
	// public PostVote[] getPostVotes(int postId);
	// public int getPostScore(int postId);
	// public void deletePostVote(int postId, int userId);

	// public void saveCommentVote(CommentVote commentVote);
	// public CommentVote[] getCommentVotes(int commentId);
	// public int getCommentScore(int commentId);
	// public void deleteCommentVote(int commentId, int userId);
}
