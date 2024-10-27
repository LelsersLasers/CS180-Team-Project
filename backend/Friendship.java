package backend;

public class Friendship implements FriendshipInterface {
	private int id;
	private int userId1;
	private int userId2;

	public Friendship(int userId1, int userId2) {
		this.id = -1;
		this.userId1 = userId1;
		this.userId2 = userId2;
	}

	public Friendship(int id, int userId1, int userId2) {
		this.id = id;
		this.userId1 = userId1;
		this.userId2 = userId2;
	}

	public int getId() {
		return id;
	}

	public int getUserId1() {
		return userId1;
	}

	public int getUserId2() {
		return userId2;
	}

	public void setId(int id) {
		this.id = id;
	}
}
