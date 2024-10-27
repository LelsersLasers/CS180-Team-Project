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
	 */
	public void saveUser(User user);

	/**
	 * Gets user by id
	 * 
	 * @param id Id of user
	 * @return User with the id
	 */
	public User getUser(int id);

	/**
	 * Gets user by username
	 * 
	 * @param username Username of user
	 * @return User with the username
	 */
	public User getUser(String username);

	/**
	 * Tries to login with the given username and password
	 * 
	 * @param username Username to login with
	 * @param password Password to login with
	 * @return User if login was successful, null otherwise
	 */
	public User tryLogin(String username, String password);

	/**
	 * Saves post to file. If the post has no id, it will be assigned one.
	 * If the post has an id/already exists in the file, it will be updated.
	 * 
	 * @param post Post to save
	 */
	public void savePost(Post post);

	/**
	 * Gets post by id
	 * 
	 * @param id Id of post
	 * @return Post with the id
	 */
	public Post getPost(int id);

	/**
	 * Gets all posts by user
	 * 
	 * @param userId Id of user
	 * @return ArrayList of posts by the user
	 */
	public ArrayList<Post> getPosts(int userId);

	/**
	 * Gets feed for user based on user's friends' posts
	 * 
	 * @param userId Id of user
	 * @return ArrayList of posts in the feed
	 */
	public ArrayList<Post> getFeed(int userId);

	/**
	 * Deletes post by id and all comments and votes associated with it
	 * 
	 * @param id Id of post to delete
	 */
	public void deletePost(int id);

	/**
	 * Saves comment to file. If the comment has no id, it will be assigned one.
	 * If the comment has an id/already exists in the file, it will be updated.
	 * 
	 * @param comment Comment to save
	 */
	public void saveComment(Comment comment);

	/**
	 * Gets comment by id
	 * 
	 * @param id Id of comment
	 * @return Comment with the id
	 */
	public Comment getComment(int id);

	/**
	 * Gets all comments for a post
	 * 
	 * @param postId Id of post
	 * @return ArrayList of comments for the post
	 */
	public ArrayList<Comment> getComments(int postId);

	/**
	 * Deletes comment by id and all votes associated with it
	 * 
	 * @param id Id of comment to delete
	 */
	public void deleteComment(int id);

	/**
	 * Saves friendship to file. If the friendship has no id, it will be assigned one.
	 * If the friendship has an id/already exists in the file, it will be updated.
	 * 
	 * @param friendship Friendship to save
	 */
	public void saveFriendship(Friendship friendship);

	/**
	 * Gets friendship by id
	 * 
	 * @param id Id of friendship
	 * @return Friendship with the id
	 */
	public Friendship getFriendship(int id);

	/**
	 * Gets all friends for a user
	 * 
	 * @param userId Id of user
	 * @return ArrayList of friends for the user
	 */
	public ArrayList<User> getFriends(int userId);

	/**
	 * Deletes friendship by id
	 * 
	 * @param id Id of friendship to delete
	 */
	public void deleteFriendship(int id);

	/**
	 * Saves blocked to file. If the blocked has no id, it will be assigned one.
	 * If the blocked has an id/already exists in the file, it will be updated.
	 * 
	 * @param blocked Blocked to save
	 */
	public void saveBlocked(Blocked blocked);

	/**
	 * Gets blocked by id
	 * 
	 * @param id Id of blocked
	 * @return Blocked with the id
	 */
	public Blocked getBlocked(int id);

	/**
	 * Gets all blocked users for a user
	 * 
	 * @param userId Id of user
	 * @return ArrayList of blocked users for the user
	 */
	public ArrayList<User> getBlockedUsers(int userId);

	/**
	 * Deletes blocked by id
	 * 
	 * @param id Id of blocked to delete
	 */
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
